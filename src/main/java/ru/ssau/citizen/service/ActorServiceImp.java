package ru.ssau.citizen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.ssau.citizen.entities.Actor;
import ru.ssau.citizen.entities.Role;
import ru.ssau.citizen.repository.ActorRepository;
import ru.ssau.citizen.repository.RoleRepository;

@Service
public class ActorServiceImp implements ActorService{
    @Autowired
    private ActorRepository actorRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public void save(Actor actor) {
            actor.setPassword(bCryptPasswordEncoder.encode(actor.getPassword()));
            Role roles = roleRepository.findRoleByName("USER");
            actor.setRole(roles);
            actorRepository.save(actor);
    }

    @Override
    public Actor findActorByLogin(String login) {
        return actorRepository.findActorByLogin(login);
    }
}
