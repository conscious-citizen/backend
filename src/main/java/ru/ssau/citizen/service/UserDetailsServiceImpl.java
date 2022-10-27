package ru.ssau.citizen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ru.ssau.citizen.entities.Actor;
import ru.ssau.citizen.repository.ActorRepository;

import javax.transaction.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final ActorRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(ActorRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) {
        Actor user = userRepository.findActorByLogin(username);
        return UserDetailsImpl.build(user);
    }
}
