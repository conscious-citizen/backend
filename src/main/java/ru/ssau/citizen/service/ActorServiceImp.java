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


    public void updateResetPasswordToken(String token, String email) throws Exception {
        Actor customer = actorRepository.findActorByEmail(email);
        if (customer != null) {
            customer.setResetPasswordToken(token);
            actorRepository.save(customer);
        } else {
            throw new Exception("Could not find any customer with the email " + email);
        }
    }

    public Actor getByResetPasswordToken(String token) {
        return actorRepository.findByResetPasswordToken(token);
    }

    public void updatePassword(Actor customer, String newPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(newPassword);
        customer.setPassword(encodedPassword);

        customer.setResetPasswordToken(null);
        actorRepository.save(customer);
    }
}
