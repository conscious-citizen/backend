package ru.ssau.citizen.service;

import ru.ssau.citizen.dto.JwtResponseDto;
import ru.ssau.citizen.dto.LoginDto;
import ru.ssau.citizen.dto.MessageResponse;
import ru.ssau.citizen.dto.RegistrationDto;
import ru.ssau.citizen.entities.Actor;

public interface ActorService {
    JwtResponseDto login(LoginDto loginDto);
    MessageResponse registration(RegistrationDto registrationDto);
    Actor findActorByLogin(String login);

}
