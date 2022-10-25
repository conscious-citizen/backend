package ru.ssau.citizen.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.ssau.citizen.entities.Actor;
import ru.ssau.citizen.service.ActorServiceImp;
import ru.ssau.citizen.util.JWTUtil;

import java.util.Map;

@RestController
public class RegisterController {

    private final ActorServiceImp actorServiceImp;
    private final JWTUtil jwtUtil;
    private final AuthenticationManager authenticationManager;


    @Autowired
    public RegisterController(ActorServiceImp actorServiceImp, JWTUtil jwtUtil, AuthenticationManager authenticationManager) {
        this.actorServiceImp = actorServiceImp;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/registration")
    public Map<String, String> register(@RequestParam String login, @RequestParam String password, @RequestParam String email,
                                        @RequestParam String city, @RequestParam String house, @RequestParam String street,
                                        @RequestParam String apartment) {
        if (actorServiceImp.findActorByLogin(login) == null) {
            Actor actor = new Actor();
            actor.setEMail(email);
            actor.setCity(city);
            actor.setStreet(street);
            actor.setHouse(house);
            actor.setApartment(apartment);
            actor.setLogin(login);
            actor.setPassword(password);
            actorServiceImp.save(actor);
            String token = jwtUtil.generateToken(actor.getLogin());
            return Map.of("jwt-token", token);
        } else return Map.of("User exists", "User exists");
    }

    @PostMapping("/login")
    public Map<String, String> performLogin(@RequestParam(value = "login") String login, @RequestParam(value = "password") String password) {
        UsernamePasswordAuthenticationToken authInputToken =
                new UsernamePasswordAuthenticationToken(login,
                        password);

        try {
            authenticationManager.authenticate(authInputToken);
        } catch (BadCredentialsException e) {
            return Map.of("message", "Incorrect credentials!");
        }

        String token = jwtUtil.generateToken(login);
        return Map.of("jwt-token", token);
    }
}
