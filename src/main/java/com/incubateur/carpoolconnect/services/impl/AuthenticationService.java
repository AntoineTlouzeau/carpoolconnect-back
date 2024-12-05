package com.incubateur.carpoolconnect.services.impl;

import com.incubateur.carpoolconnect.entities.Role;
import com.incubateur.carpoolconnect.entities.User;
import com.incubateur.carpoolconnect.repositories.UserRepository;
import com.incubateur.carpoolconnect.utilities.ObjectsValidator;
import com.incubateur.carpoolconnect.utilities.exceptions.UserNotFoundException;
import com.incubateur.carpoolconnect.utilities.requests.*;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final EmailServiceImpl emailService;
    private final ObjectsValidator<RegisterRequest> registerValidator;
    private final ObjectsValidator<AuthenticationRequest> authenticationValidator;
    private final ObjectsValidator<EmailRequest> emailValidator;


    public AuthenticationResponse register(RegisterRequest request) {
        registerValidator.validate(request);
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .dateOfBirth(LocalDate.parse(request.getDateOfBirth()))
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .activationKey(getKey())
                .isEnabled(false)
                .password(passwordEncoder.encode(request.getPassword()))
                .points(50)
                .role(Role.builder()
                        .name("ROLE_USER")
                        .build())
                .build();
        userRepository.save(user);

        Thread emailThread = getThread(user);
        emailThread.start();

        return null;
    }

    @NotNull
    private Thread getThread(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("auth", user.getRole().getName());

        Context context = new Context();
        context.setVariable("firstName", user.getFirstName());
        context.setVariable("email", user.getEmail());
        context.setVariable("key", user.getActivationKey());

        return new Thread(() ->
                emailService.sendEmailWithHtmlTemplate(user.getEmail(), "Activation de votre compte", "email-account-activation-template", context));
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) throws AuthenticationException {
        authenticationValidator.validate(request);
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();

        Map<String, Object> claims = new HashMap<>();
        claims.put("auth", user.getRole().getName());

        var jwtToken = jwtService.generateToken(claims, user);
        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .build();

    }

    public boolean activateAccount(ActivationRequest request) {
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        if (verifyActivationKey(request.getKey(), user)) {
            user.setEnabled(true);
            user.setActivationKey(null);
            userRepository.saveAndFlush(user);
        }
        return user.isEnabled();
    }

    public String reinitializePasswordEmail(EmailRequest request) {
        emailValidator.validate(request);
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(UserNotFoundException::new);

        String key = getKey();

        user.setPasswordRenewalKey(key);
        userRepository.saveAndFlush(user);

        Context context = new Context();
        context.setVariable("email", request.getEmail());
        context.setVariable("key", key);

        Thread emailThread = new Thread(() ->
                emailService.sendEmailWithHtmlTemplate(user.getEmail(), "Réinitialisation mot de passe", "email-password-reinitialization", context));
        emailThread.start();
        return "";
    }

    public String reinitializePassword(PasswordRenewalRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        if (verifyPasswordKey(request.getKey(), user)) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setPasswordRenewalKey(null);
            userRepository.saveAndFlush(user);
            return "";
        }
        return null;
    }

    public String deleteAccount(String authHeader) {
        final String token = jwtService.getTokenFromRequest(authHeader);
        if (token.isEmpty()) {
            return "";
        }
        User user = userRepository.findByEmail(jwtService.extractUsername(token))
                .orElseThrow();
        user.setEnabled(false);
        userRepository.saveAndFlush(user);
        return "";
    }

    private String getKey() {
        return UUID.randomUUID().toString();
    }

    private boolean verifyActivationKey(String key, User user) {
        return key.equals(user.getActivationKey());
    }

    private boolean verifyPasswordKey(String key, User user) {
        return key.equals(user.getPasswordRenewalKey());
    }

}
