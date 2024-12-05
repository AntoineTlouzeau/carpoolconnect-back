package com.incubateur.carpoolconnect.controllers;

import com.incubateur.carpoolconnect.dto.CarDto;
import com.incubateur.carpoolconnect.services.interfaces.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/car")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @PostMapping(value = "/add")
    @SuppressWarnings("unused")
    public ResponseEntity<Object> addCar(@RequestBody CarDto car, @RequestHeader(name = "Authorization") String authHeader) {
        return ResponseEntity.ok(carService.addCar(car, authHeader));
    }

    @GetMapping
    @SuppressWarnings("unused")
    public ResponseEntity<List<CarDto>> getCarFromUser(@RequestHeader(name = "Authorization") String authHeader) {
        return ResponseEntity.ok(carService.getFromUser(authHeader));
    }

    @GetMapping("/error")
    @CrossOrigin("*")
    public ResponseEntity<?> throwException() {
        return ResponseEntity.ok(carService.throwException());
    }


}
