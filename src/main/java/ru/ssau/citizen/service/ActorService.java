package ru.ssau.citizen.service;

import ru.ssau.citizen.entities.Actor;

public interface ActorService {
    void save(Actor actor);
    Actor findActorByLogin(String login);
}
