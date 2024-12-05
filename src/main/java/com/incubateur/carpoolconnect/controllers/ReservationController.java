package com.incubateur.carpoolconnect.controllers;

import com.incubateur.carpoolconnect.dto.ReservationDto;
import com.incubateur.carpoolconnect.dto.RouteDto;
import com.incubateur.carpoolconnect.services.interfaces.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservation")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @SuppressWarnings("unused")
    @PostMapping(value = "/ask")
    public ResponseEntity<ReservationDto> askReservation(@RequestBody RouteDto route, @RequestParam int numberOfPassengers, @RequestHeader(name = "Authorization") String authHeader) {
        return ResponseEntity.ok(reservationService.askReservation(route, numberOfPassengers, authHeader));
    }

    @SuppressWarnings("unused")
    @PostMapping(value = "/accept")
    public ResponseEntity<ReservationDto> acceptReservation(@RequestBody ReservationDto reservationDto, @RequestHeader(name = "Authorization") String authHeader) {
        return ResponseEntity.ok(reservationService.acceptReservation(reservationDto, authHeader));
    }

    @SuppressWarnings("unused")
    @PostMapping(value = "/deny")
    public ResponseEntity<ReservationDto> denyReservation(@RequestBody ReservationDto reservationDto, @RequestHeader(name = "Authorization") String authHeader) {
        return ResponseEntity.ok(reservationService.denyReservation(reservationDto, authHeader));
    }

    @SuppressWarnings("unused")
    @PostMapping(value = "/cancel")
    public ResponseEntity<ReservationDto> cancelReservation(@RequestBody ReservationDto reservationDto, @RequestHeader(name = "Authorization") String authHeader) {
        return ResponseEntity.ok(reservationService.cancelReservation(reservationDto, authHeader));
    }

    @SuppressWarnings("unused")
    @GetMapping(value = "/user")
    public ResponseEntity<List<ReservationDto>> getReservationFromUser(@RequestHeader(name = "Authorization") String authHeader) {
        return ResponseEntity.ok(reservationService.getFromUser(authHeader));
    }

    @SuppressWarnings("unused")
    @GetMapping(value = "/past")
    public ResponseEntity<List<ReservationDto>> getPastReservationFromUser(@RequestHeader(name = "Authorization") String authHeader) {
        return ResponseEntity.ok(reservationService.getPastReservationsFromUser(authHeader));
    }

    @SuppressWarnings("unused")
    @GetMapping(value = "/future")
    public ResponseEntity<List<ReservationDto>> getFutureReservationFromUser(@RequestHeader(name = "Authorization") String authHeader) {
        return ResponseEntity.ok(reservationService.getFutureReservationsFromUser(authHeader));
    }

    @SuppressWarnings("unused")
    @GetMapping(value = "/route")
    public ResponseEntity<List<ReservationDto>> getReservationFromRoute(@RequestBody RouteDto route) {
        return ResponseEntity.ok(reservationService.getFromRoute(route));
    }

    @SuppressWarnings("unused")
    @GetMapping(value = "/driver")
    public ResponseEntity<List<ReservationDto>> getReservationFromDriver(@RequestHeader(name = "Authorization") String authHeader) {
        return ResponseEntity.ok(reservationService.getByDriver(authHeader));
    }

    @SuppressWarnings("unused")
    @GetMapping(value = "/awaiting")
    public ResponseEntity<List<ReservationDto>> getAwaitingReservations(@RequestHeader(name = "Authorization") String authHeader) {
        return ResponseEntity.ok(reservationService.getAwaitingReservation(authHeader));
    }

}
