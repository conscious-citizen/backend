package ru.ssau.citizen.service;

import ru.ssau.citizen.dto.*;
import ru.ssau.citizen.entities.Actor;

public interface ActorService {
    JwtResponseDto login(LoginDto loginDto);
    MessageResponse registration(RegistrationDto registrationDto);
    Actor findActorByLogin(String login);

    Actor update(UpdateDto updateDto, Actor currentActor);

}
