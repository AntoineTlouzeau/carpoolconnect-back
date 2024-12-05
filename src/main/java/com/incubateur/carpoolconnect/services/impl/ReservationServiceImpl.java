package com.incubateur.carpoolconnect.services.impl;

import com.incubateur.carpoolconnect.dto.ReservationDto;
import com.incubateur.carpoolconnect.dto.RouteDto;
import com.incubateur.carpoolconnect.entities.Reservation;
import com.incubateur.carpoolconnect.entities.Route;
import com.incubateur.carpoolconnect.entities.User;
import com.incubateur.carpoolconnect.enums.ReservationStatus;
import com.incubateur.carpoolconnect.mapper.ReservationMapper;
import com.incubateur.carpoolconnect.repositories.ReservationRepository;
import com.incubateur.carpoolconnect.repositories.RouteRepository;
import com.incubateur.carpoolconnect.repositories.UserRepository;
import com.incubateur.carpoolconnect.services.interfaces.EmailService;
import com.incubateur.carpoolconnect.services.interfaces.ReservationService;
import com.incubateur.carpoolconnect.utilities.exceptions.NotDriverException;
import com.incubateur.carpoolconnect.utilities.exceptions.NotPassengerException;
import com.incubateur.carpoolconnect.utilities.exceptions.RouteFullException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.thymeleaf.context.Context;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final RouteRepository routeRepository;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final EmailService emailService;

    @Override
    public List<ReservationDto> getFromUser(String authHeader) {
        final String token = authHeader.substring(7);
        final User user = userRepository.findByEmail(jwtService.extractUsername(token)).orElseThrow();
        return reservationRepository.findByUser(user)
                .orElseThrow()
                .stream()
                .map(ReservationMapper.INSTANCE::reservationToReservationDto)
                .toList();
    }

    @Override
    public List<ReservationDto> getPastReservationsFromUser(String authHeader) {
        return getFromUser(authHeader)
                .stream()
                .filter(res -> res.getRoute().getDepartureDate().isBefore(LocalDateTime.now()))
                .toList();
    }

    @Override
    public List<ReservationDto> getFutureReservationsFromUser(String authHeader) {
        return getFromUser(authHeader)
                .stream()
                .filter(res -> res.getRoute().getDepartureDate().isAfter(LocalDateTime.now()))
                .toList();
    }

    @Override
    public List<ReservationDto> getFromRoute(RouteDto routeDto) {
        return routeRepository.getReferenceById(routeDto.getId())
                .getReservations()
                .stream()
                .map(ReservationMapper.INSTANCE::reservationToReservationDto)
                .toList();
    }

    @Override
    public ReservationDto askReservation(RouteDto routeDto, int numberOfPassengers, String authHeader) {
        final User user = userRepository.findByEmail(jwtService.extractUsername(authHeader.substring(7))).orElseThrow();
        final Route route = routeRepository.getReferenceById(routeDto.getId());

        Reservation reservation = Reservation.builder()
                .status(ReservationStatus.WAITING)
                .user(user)
                .route(route)
                .numberOfPassengers(numberOfPassengers)
                .build();

        return ReservationMapper.INSTANCE.reservationToReservationDto(reservationRepository.save(reservation));
    }

    @Override
    public ReservationDto acceptReservation(ReservationDto reservationDto, String authHeader) {
        if (!jwtService.extractUsername(authHeader.substring(7)).equals(reservationDto.getRoute().getDriver().getEmail())) {
            throw new NotDriverException();
        }

        Reservation reservation = reservationRepository.getReferenceById(reservationDto.getId());

        long seatsAvailable = reservation.getRoute().getSeats() - countReservations(reservation.getRoute());

        if (seatsAvailable - reservation.getNumberOfPassengers() < 0) {
            throw new RouteFullException();
        }

        reservation.setStatus(ReservationStatus.RESERVED);
        reservationRepository.saveAndFlush(reservation);

        Thread emailThread = new Thread(() ->
                sendEmail("email-reservation-accepted-template",reservation.getUser().getEmail(), "Confirmation de réservation", reservation)
        );
        emailThread.start();
        return ReservationMapper.INSTANCE.reservationToReservationDto(reservation);
    }

    @Override
    public ReservationDto denyReservation(ReservationDto reservationDto, String authHeader) {
        if (!jwtService.extractUsername(authHeader.substring(7)).equals(reservationDto.getRoute().getDriver().getEmail())) {
            throw new NotDriverException();
        }
        Reservation reservation = reservationRepository.getReferenceById(reservationDto.getId());
        reservation.setStatus(ReservationStatus.DENIED);
        reservationRepository.saveAndFlush(reservation);

        Thread emailThread = new Thread(() ->
                sendEmail("email-reservation-denied-template",reservation.getUser().getEmail(), "Refus de réservation", reservation)
        );
        emailThread.start();
        return ReservationMapper.INSTANCE.reservationToReservationDto(reservation);
    }

    @Override
    public ReservationDto cancelReservation(ReservationDto reservationDto, String authHeader) {
        if (!jwtService.extractUsername(authHeader.substring(7)).equals(reservationDto.getUser().getEmail())) {
            throw new NotPassengerException();
        }
        Reservation reservation = reservationRepository.getReferenceById(reservationDto.getId());
        reservation.setStatus(ReservationStatus.CANCELED);
        reservationRepository.saveAndFlush(reservation);

        Thread emailThread = new Thread(() ->
                sendEmail("email-reservation-canceled-template", reservation.getRoute().getDriver().getEmail(), "Annulation de réservation", reservation)
        );
        emailThread.start();
        return ReservationMapper.INSTANCE.reservationToReservationDto(reservation);
    }

    @Override
    public List<ReservationDto> getByDriver(String authHeader) {
        User user = userRepository.findByEmail(jwtService.extractUsername(authHeader.substring(7))).orElseThrow();
        return reservationRepository.findByRoute_Driver(user)
                .orElseThrow()
                .stream()
                .map(ReservationMapper.INSTANCE::reservationToReservationDto)
                .toList();
    }

    @Override
    public List<ReservationDto> getAwaitingReservation(String authHeader) {
        return getByDriver(authHeader)
                .stream()
                .filter(r -> r.getStatus() == ReservationStatus.WAITING)
                .toList();
    }

    private void sendEmail(String emailTemplate, String to,String subject, Reservation reservation) {
        Context context = new Context();
        context.setVariable("driverFirstName", reservation.getRoute().getDriver().getFirstName());
        context.setVariable("driverLastName", reservation.getRoute().getDriver().getLastName());
        context.setVariable("firstName", reservation.getUser().getFirstName());
        context.setVariable("lastName", reservation.getUser().getLastName());
        context.setVariable("departure", reservation.getRoute().getDeparture().getCity());
        context.setVariable("destination", reservation.getRoute().getDestination().getCity());
        context.setVariable("date", reservation.getRoute().getDepartureDate().toLocalDate());
        context.setVariable("time", reservation.getRoute().getDepartureDate().toLocalTime());
        emailService.sendEmailWithHtmlTemplate(to, subject, emailTemplate, context);
    }

    private long countReservations(Route route) {
        return reservationRepository
                .findByRoute(route)
                .orElse(new ArrayList<>())
                .stream()
                .filter(r -> r.getStatus() == ReservationStatus.RESERVED)
//                        || r.getStatus() == ReservationStatus.WAITING)
                .count();
    }
}

