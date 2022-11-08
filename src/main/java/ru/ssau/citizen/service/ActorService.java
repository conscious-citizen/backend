package ru.ssau.citizen.service;

import ru.ssau.citizen.DTO.JwtResponseDto;
import ru.ssau.citizen.DTO.LoginDto;
import ru.ssau.citizen.DTO.MessageResponse;
import ru.ssau.citizen.DTO.RegistrationDto;
import ru.ssau.citizen.entities.Actor;

public interface ActorService {
    JwtResponseDto login(LoginDto loginDto);
    MessageResponse registration(RegistrationDto registrationDto);
    Actor findActorByLogin(String login);

}
