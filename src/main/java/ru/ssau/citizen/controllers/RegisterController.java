package ru.ssau.citizen.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.ssau.citizen.dto.JwtResponseDto;
import ru.ssau.citizen.dto.LoginDto;
import ru.ssau.citizen.dto.MessageResponse;
import ru.ssau.citizen.dto.RegistrationDto;
import ru.ssau.citizen.service.ActorService;
import ru.ssau.citizen.service.ActorServiceImp;

@RestController
@Tag(name = "Регистрация", description = "Все методы для работы с регистрацией, авторизацией")
public class RegisterController {
    @Autowired
    ActorServiceImp actorServiceImp;
    @Autowired
    ActorService actorService;

    @PostMapping("auth/login")
    @Operation(summary = "Авторизация")
    public ResponseEntity<JwtResponseDto> authUser(@RequestBody LoginDto loginDto) {
        return new ResponseEntity<>(actorService.login(loginDto), HttpStatus.OK);
    }

    @PostMapping("auth/registration")
    @Operation(summary = "Регистрация")
    public ResponseEntity<MessageResponse> registerUser(@RequestBody RegistrationDto registrationDto) {
        return new ResponseEntity<>(actorService.registration(registrationDto), HttpStatus.OK);

    }
}