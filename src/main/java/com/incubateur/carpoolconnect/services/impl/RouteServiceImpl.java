package com.incubateur.carpoolconnect.services.impl;

import com.incubateur.carpoolconnect.dto.CarDto;
import com.incubateur.carpoolconnect.dto.RouteDto;
import com.incubateur.carpoolconnect.entities.Route;
import com.incubateur.carpoolconnect.enums.ReservationStatus;
import com.incubateur.carpoolconnect.mapper.AddressMapper;
import com.incubateur.carpoolconnect.mapper.RouteMapper;
import com.incubateur.carpoolconnect.repositories.ReservationRepository;
import com.incubateur.carpoolconnect.repositories.RouteRepository;
import com.incubateur.carpoolconnect.repositories.UserRepository;
import com.incubateur.carpoolconnect.services.interfaces.RouteService;
import com.incubateur.carpoolconnect.utilities.ObjectsValidator;
import com.incubateur.carpoolconnect.utilities.requests.AddRouteRequest;
import com.incubateur.carpoolconnect.utilities.requests.CityRequest;
import com.incubateur.carpoolconnect.utilities.requests.CoordinatesRequest;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class RouteServiceImpl implements RouteService {

    private final RouteRepository routeRepository;
    private final UserRepository userRepository;
    private final ReservationRepository reservationRepository;
    private final JwtService jwtService;
    private final ObjectsValidator<AddRouteRequest> addRequestValidator;
    private final ObjectsValidator<CityRequest> cityRequestValidator;

    @Override
    public RouteDto proposeRoute(AddRouteRequest request,@NotNull String authHeader) {
        addRequestValidator.validate(request);
        final String token = jwtService.getTokenFromRequest(authHeader);
        Route route = Route.builder()
                .departureDate(request.getDepartureDate())
                .seats(request.getSeats())
                .smallBaggage(request.getSmallBaggage())
                .largeBaggage(request.getLargeBaggage())
                .silence(request.isSilence())
                .driver(userRepository.findByEmail(jwtService.extractUsername(token))
                        .orElseThrow())
                .car(null)
                .musicGenre(request.getMusicGenre())
                .departure(AddressMapper.INSTANCE.addressDtoToAddress(request.getDeparture()))
                .destination(AddressMapper.INSTANCE.addressDtoToAddress(request.getDestination()))
                .intermediateAddresses(new ArrayList<>())
                .ratings(new ArrayList<>())
                .reservations(new ArrayList<>())
                .build();
        routeRepository.save(route);
        return RouteMapper.INSTANCE.routeToRouteDto(route);
    }

    @Override
    public RouteDto getRouteById(long id) {
        return RouteMapper.INSTANCE.routeToRouteDto(routeRepository.getReferenceById(id));
    }

    @Override
    public List<RouteDto> getRouteByDeparture(@NotNull CoordinatesRequest request) {
        List<Route> listLatitude = routeRepository.findByDeparture_Latitude(request.getLatitude()).orElseThrow();
        List<Route> result = new ArrayList<>();
        for (Route route : listLatitude) {
            if (route.getDeparture().getLongitude() == request.getLongitude()) {
                result.add(route);
            }
        }
        return result.stream().map(RouteMapper.INSTANCE::routeToRouteDto).toList();
    }

    @Override
    public List<RouteDto> getRouteByDestination(@NotNull CoordinatesRequest request) {
        List<Route> listLatitude = routeRepository.findByDestination_Latitude(request.getLatitude()).orElseThrow();
        List<Route> result = new ArrayList<>();
        for (Route route : listLatitude) {
            if (route.getDestination().getLongitude() == request.getLongitude()) {
                result.add(route);
            }
        }
        return result.stream().map(RouteMapper.INSTANCE::routeToRouteDto).toList();
    }

    @Override
    public List<RouteDto> getRouteByDepartureCity(@NotNull @NotEmpty CityRequest request) {
        cityRequestValidator.validate(request);
        return routeRepository.findByDeparture_City(request.getCity())
                .orElseThrow()
                .stream()
                .map(RouteMapper.INSTANCE::routeToRouteDto)
                .toList();
    }

    @Override
    public List<RouteDto> getRouteByDestinationCity(@NotNull @NotEmpty CityRequest request) {
        cityRequestValidator.validate(request);
        return routeRepository.findByDestination_City(request.getCity())
                .orElseThrow()
                .stream()
                .map(RouteMapper.INSTANCE::routeToRouteDto)
                .toList();
    }

    @Override
    public RouteDto addCarToRoute(CarDto car) {
        return null;
    }

    @Override
    public List<RouteDto> searchRoutes(@NotNull @NotEmpty CityRequest departureCity,
                                       @NotNull @NotEmpty CityRequest destinationCity,
                                       @NotNull @NotEmpty LocalDateTime departureDate,
                                       float departLat,
                                       float departLong,
                                       float destinationLat,
                                       float destinationLong,
                                       int departRadius,
                                       int destinationRadius,
                                       int seats,
                                       int baggage) {
        cityRequestValidator.validate(departureCity);
        cityRequestValidator.validate(destinationCity);

        LocalDateTime startInterval = departureDate.minusHours(5);
        LocalDateTime endInterval = departureDate.plusHours(5);

        departRadius = departRadius * 1000;
        destinationRadius = destinationRadius * 1000;

//        if (departRadius == 1 && destinationRadius == 1) {
//            return routeRepository.findByDeparture_CityAndDestination_CityAndDepartureDateBetween(
//                            departureCity.getCity(),
//                            destinationCity.getCity(),
//                            startInterval,
//                            endInterval,
//                            seats,
//                            baggage)
//                    .orElseThrow()
//                    .stream()
//                    .map(RouteMapper.INSTANCE::routeToRouteDto)
//                    .toList();
//        }
//        else {
            return routeRepository.findByLocationAndDateWithinRadius(
                            departLat,
                            departLong,
                            destinationLat,
                            destinationLong,
                            departRadius,
                            destinationRadius,
                            startInterval,
                            endInterval,
                            seats,
                            baggage)
                    .orElseThrow()
                    .stream()
                    .map(RouteMapper.INSTANCE::routeToRouteDto)
                    .toList();


    }

    @Override
    public long countAvailableSeats(RouteDto routeDto) {
        Route route = routeRepository.getReferenceById(routeDto.getId());
        return route.getSeats() - reservationRepository
                .findByRoute(route)
                .orElse(new ArrayList<>())
                .stream()
                .filter(r -> r.getStatus() == ReservationStatus.RESERVED)
                .map(r -> r.getNumberOfPassengers())
                .reduce(0, Integer::sum);
    }
}
