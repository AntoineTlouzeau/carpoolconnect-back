package com.incubateur.carpoolconnect.services.interfaces;

import com.incubateur.carpoolconnect.dto.ReservationDto;
import com.incubateur.carpoolconnect.dto.RouteDto;

import java.util.List;

public interface ReservationService {

    List<ReservationDto> getFromUser(String authHeader);

    List<ReservationDto> getPastReservationsFromUser(String authHeader);

    List<ReservationDto> getFutureReservationsFromUser(String authHeader);

    List<ReservationDto> getFromRoute(RouteDto routeDto);
    
    ReservationDto askReservation(RouteDto routeDto, int numberOfPassengers, String authHeader);
    
    ReservationDto acceptReservation(ReservationDto reservation, String authHeader);

    ReservationDto denyReservation(ReservationDto reservation, String authHeader);

    ReservationDto cancelReservation(ReservationDto reservation, String authHeader);

    List<ReservationDto> getByDriver(String authHeader);

    List<ReservationDto> getAwaitingReservation(String authHeader);

}
