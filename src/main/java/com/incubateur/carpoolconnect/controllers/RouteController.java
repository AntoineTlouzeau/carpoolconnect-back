package com.incubateur.carpoolconnect.controllers;

import com.incubateur.carpoolconnect.dto.RouteDto;
import com.incubateur.carpoolconnect.services.interfaces.RouteService;
import com.incubateur.carpoolconnect.utilities.requests.AddRouteRequest;
import com.incubateur.carpoolconnect.utilities.requests.CityRequest;
import com.incubateur.carpoolconnect.utilities.requests.CoordinatesRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/route")
@RequiredArgsConstructor
public class RouteController {

    private final RouteService routeService;

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RouteDto> proposeRoute(@RequestBody @Valid AddRouteRequest request, @RequestHeader(name = "Authorization") String authHeader) {
        return ResponseEntity.ok(routeService.proposeRoute(request, authHeader));
    }

    @GetMapping(value = "/coord/departure", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RouteDto>> getRouteByDepartureCoordinates(@RequestBody CoordinatesRequest request) {
        return ResponseEntity.ok(routeService.getRouteByDeparture(request));
    }

    @GetMapping(value = "/coord/destination", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RouteDto>> getRouteByDestinationCoordinates(@RequestBody CoordinatesRequest request) {
        return ResponseEntity.ok(routeService.getRouteByDestination(request));
    }

    @GetMapping(value = "/city/departure", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RouteDto>> getRouteByDepartureCity(@RequestBody @NotNull @NotEmpty CityRequest request) {
        return ResponseEntity.ok(routeService.getRouteByDepartureCity(request));
    }

    @GetMapping(value = "/city/destination", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RouteDto>> getRouteByDestinationCity(@RequestBody @NotNull @NotEmpty CityRequest request) {
        return ResponseEntity.ok(routeService.getRouteByDestinationCity(request));
    }

    @GetMapping(value = "/id/{id}")
    public ResponseEntity<RouteDto> getRouteById(@PathVariable(value = "id") long id) {
        return ResponseEntity.ok(routeService.getRouteById(id));
    }

    @GetMapping(value = "/search")
    public ResponseEntity<List<RouteDto>> searchRoutes(
            @RequestParam @NotNull @NotEmpty String departureCity,
            @RequestParam @NotNull @NotEmpty String destinationCity,
            @RequestParam @NotNull @NotEmpty LocalDateTime departureDate,
            @RequestParam float departLat,
            @RequestParam float departLong,
            @RequestParam float destinationLat,
            @RequestParam float destinationLong,
            @RequestParam int departRadius,
            @RequestParam int destinationRadius,
            @RequestParam int seats,
            @RequestParam int baggage
            ) {

        return ResponseEntity.ok(routeService.searchRoutes(
                new CityRequest(departureCity),
                new CityRequest(destinationCity),
                departureDate,
                departLat,
                departLong,
                destinationLat,
                destinationLong,
                departRadius,
                destinationRadius,
                seats,
                baggage
        ));
    }

    @PostMapping(value = "/seats")
    public ResponseEntity<Long> countAvailableSeats(@RequestBody RouteDto routeDto) {
        return ResponseEntity.ok(routeService.countAvailableSeats(routeDto));
    }

}
