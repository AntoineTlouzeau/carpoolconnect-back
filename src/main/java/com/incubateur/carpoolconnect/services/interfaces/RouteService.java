package com.incubateur.carpoolconnect.services.interfaces;

import com.incubateur.carpoolconnect.dto.CarDto;
import com.incubateur.carpoolconnect.dto.RouteDto;
import com.incubateur.carpoolconnect.utilities.requests.AddRouteRequest;
import com.incubateur.carpoolconnect.utilities.requests.CityRequest;
import com.incubateur.carpoolconnect.utilities.requests.CoordinatesRequest;

import java.time.LocalDateTime;
import java.util.List;

public interface RouteService {

    RouteDto proposeRoute(AddRouteRequest request, String authHeader);

    RouteDto getRouteById(long id);

    List<RouteDto> getRouteByDeparture(CoordinatesRequest request);

    List<RouteDto> getRouteByDestination(CoordinatesRequest request);

    List<RouteDto> getRouteByDepartureCity(CityRequest request);

    List<RouteDto> getRouteByDestinationCity(CityRequest request);

    RouteDto addCarToRoute(CarDto car);

    List<RouteDto> searchRoutes(CityRequest departureCity,
                                CityRequest destinationCity,
                                LocalDateTime departureDate,
                                float departLat,
                                float departLong,
                                float destinationLat,
                                float destinationLong,
                                int departRadius,
                                int destinationRadius,
                                int seats,
                                int baggage);

    long countAvailableSeats(RouteDto routeDto);
}
