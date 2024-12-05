package com.incubateur.carpoolconnect.controllers;

import com.incubateur.carpoolconnect.dto.RouteDto;
import com.incubateur.carpoolconnect.dto.UserDto;
import com.incubateur.carpoolconnect.services.interfaces.UserService;
import com.incubateur.carpoolconnect.utilities.requests.AddBioRequest;
import com.incubateur.carpoolconnect.utilities.requests.PasswordChangeRequest;
import com.incubateur.carpoolconnect.utilities.exceptions.IncorrectOldPasswordException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(value = "/past")
    @SuppressWarnings("unused")
    public ResponseEntity<List<RouteDto>> getPastRoutes(@RequestHeader(name = "Authorization") String authHeader) {
        return ResponseEntity.ok(userService.getPastRoutes(authHeader));
    }

    @GetMapping(value = "/future")
    @SuppressWarnings("unused")
    public ResponseEntity<List<RouteDto>> getFutureRoutes(@RequestHeader(name = "Authorization") String authHeader) {
        return ResponseEntity.ok(userService.getFutureRoutes(authHeader));
    }

    @GetMapping(value = "/email")
    @SuppressWarnings("unused")
    public ResponseEntity<UserDto> getFromEmail(@RequestHeader(name = "Authorization") String authHeader) {
        return ResponseEntity.ok(userService.getFromEmail(authHeader));
    }

    @PostMapping(value = "/password")
    @SuppressWarnings("unused")
    public ResponseEntity<UserDto> changePassword(@RequestBody PasswordChangeRequest request,@RequestHeader(name = "Authorization") String authHeader) throws IncorrectOldPasswordException {
        return ResponseEntity.ok(userService.changePassword(request, authHeader));
    }

    @PostMapping(value = "/bio")
    @SuppressWarnings("unused")
    public ResponseEntity<UserDto> addBio(@RequestBody AddBioRequest request, @RequestHeader(name = "Authorization") String authHeader) {
        return ResponseEntity.ok(userService.addBio(request, authHeader));
    }
}
