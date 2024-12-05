package com.incubateur.carpoolconnect.controllers;

import com.incubateur.carpoolconnect.services.impl.AuthenticationService;
import com.incubateur.carpoolconnect.utilities.requests.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping(value = "/authenticate", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PostMapping(value = "/activate")
    public ResponseEntity<Boolean> activateAccount(@RequestBody ActivationRequest request) {
        return ResponseEntity.ok(service.activateAccount(request));
    }

    @PostMapping(value = "/password/email")
    public ResponseEntity<String> reinitializePasswordEmail(@RequestBody EmailRequest request) {
        return ResponseEntity.ok(service.reinitializePasswordEmail(request));
    }

    @PostMapping(value = "/password")
    public ResponseEntity<String> reinitializePassword(@RequestBody PasswordRenewalRequest request) {
        return ResponseEntity.ok(service.reinitializePassword(request));
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<String> deactivateAccount(@RequestHeader(name = "Authorization") String authHeader) {
        return ResponseEntity.ok(service.deleteAccount(authHeader));
    }


}
