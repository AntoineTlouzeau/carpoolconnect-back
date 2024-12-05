package com.incubateur.carpoolconnect.services.impl;

import com.incubateur.carpoolconnect.dto.RouteDto;
import com.incubateur.carpoolconnect.dto.UserDto;
import com.incubateur.carpoolconnect.entities.User;
import com.incubateur.carpoolconnect.mapper.RouteMapper;
import com.incubateur.carpoolconnect.mapper.UserMapper;
import com.incubateur.carpoolconnect.repositories.RouteRepository;
import com.incubateur.carpoolconnect.repositories.UserRepository;
import com.incubateur.carpoolconnect.services.interfaces.UserService;
import com.incubateur.carpoolconnect.utilities.requests.AddBioRequest;
import com.incubateur.carpoolconnect.utilities.requests.PasswordChangeRequest;
import com.incubateur.carpoolconnect.utilities.exceptions.IncorrectOldPasswordException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final RouteRepository routeRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<RouteDto> getPastRoutes(String authHeader) {
        List<RouteDto> pastRoutes = new ArrayList<>();
        final String token = jwtService.getTokenFromRequest(authHeader);
        if (token.isEmpty()) {
            return new ArrayList<>();
        }
        User user = userRepository.findByEmail(jwtService.extractUsername(token)).orElseThrow();
        for (RouteDto route : getAllRoutes(user)) {
            if (route.getDepartureDate().isBefore(LocalDateTime.now())) {
                pastRoutes.add(route);
            }
        }
        return pastRoutes;
    }

    @Override
    public List<RouteDto> getFutureRoutes(String authHeader) {
        List<RouteDto> futureRoutes = new ArrayList<>();
        final String token = jwtService.getTokenFromRequest(authHeader);
        if (token.isEmpty()) {
            return new ArrayList<>();
        }
        User user = userRepository.findByEmail(jwtService.extractUsername(token)).orElseThrow();
        for (RouteDto route : getAllRoutes(user)) {
            if (route.getDepartureDate().isAfter(LocalDateTime.now())) {
                futureRoutes.add(route);
            }
        }
        return futureRoutes;
    }

    @Override
    public UserDto changePassword(PasswordChangeRequest request, String authHeader) throws IncorrectOldPasswordException {
        User user = userRepository.findByEmail(jwtService.extractUsername(authHeader.substring(7))).orElseThrow();
        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            throw new IncorrectOldPasswordException("Ancien mot de passe incorrect");
        }
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        return UserMapper.INSTANCE.userToUserDto(userRepository.saveAndFlush(user));
    }


    @Override
    public UserDto getFromEmail(String authHeader) {
        User user = userRepository.findByEmail(jwtService.extractUsername(authHeader.substring(7))).orElseThrow();
        return UserMapper.INSTANCE.userToUserDto(user);
    }

    @Override
    public UserDto addBio(AddBioRequest request, String authHeader) {
        User user = userRepository.findByEmail(jwtService.extractUsername(authHeader.substring(7))).orElseThrow();
        user.setBio(request.getBio());
        userRepository.saveAndFlush(user);
        return UserMapper.INSTANCE.userToUserDto(user);
    }

    private List<RouteDto> getAllRoutes(User user) {
        return routeRepository.findByDriver(user)
                .orElse(new ArrayList<>())
                .stream()
                .map(RouteMapper.INSTANCE::routeToRouteDto)
                .toList();
    }

}
