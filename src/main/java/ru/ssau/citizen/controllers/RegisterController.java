package ru.ssau.citizen.controllers;


import org.aspectj.lang.annotation.SuppressAjWarnings;
import org.hibernate.MappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.ssau.citizen.dto.JwtResponseDto;
import ru.ssau.citizen.dto.LoginDto;
import ru.ssau.citizen.dto.MessageResponse;
import ru.ssau.citizen.dto.RegistrationDto;
import ru.ssau.citizen.entities.Actor;
import ru.ssau.citizen.repository.ActorRepository;
import ru.ssau.citizen.service.ActorService;
import ru.ssau.citizen.service.ActorServiceImp;
import ru.ssau.citizen.util.JWTUtil;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import static org.hibernate.tool.schema.SchemaToolingLogging.LOGGER;

@RestController
public class RegisterController {

    private final ActorServiceImp actorServiceImp;
    private final JWTUtil jwtUtil;
    private final AuthenticationManager authenticationManager;


    @Autowired

    ActorServiceImp actorServiceImp;
    @Autowired
    ActorService actorService;

    @PostMapping("auth/login")
    public ResponseEntity<JwtResponseDto> authUser(@RequestBody LoginDto loginDto) {
        return new ResponseEntity<>(actorService.login(loginDto), HttpStatus.OK);
    }

    @PostMapping("auth/registration")
    public ResponseEntity<MessageResponse> registerUser(@RequestBody RegistrationDto registrationDto) {
        return new ResponseEntity<>(actorService.registration(registrationDto), HttpStatus.OK);


    }
}
