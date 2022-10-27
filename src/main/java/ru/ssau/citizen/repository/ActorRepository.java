package ru.ssau.citizen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ssau.citizen.entities.Actor;

public interface ActorRepository extends JpaRepository<Actor,Long> {
    public Actor findActorByLogin(String login);
    public Actor findActorByEmail(String eMail);
    public Actor findActorByCity(String city);
    public Actor findByResetPasswordToken(String token);

    boolean existsByLogin(String username);

}
