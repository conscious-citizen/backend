package ru.ssau.citizen.controllers;

import org.aspectj.lang.annotation.SuppressAjWarnings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.ssau.citizen.entities.Actor;
import ru.ssau.citizen.repository.ActorRepository;
import ru.ssau.citizen.service.ActorServiceImp;

@RestController
public class RegisterController {
    @Autowired
    ActorServiceImp actorServiceImp;

    @PostMapping("/registration")
    public String register(@RequestParam String login, @RequestParam String password, @RequestParam String email,
                           @RequestParam String city,@RequestParam String house,@RequestParam String street,
                           @RequestParam String apartment){
        if(actorServiceImp.findActorByLogin(login)== null){
        Actor actor = new Actor();
        actor.setEMail(email);
        actor.setCity(city);
        actor.setStreet(street);
        actor.setHouse(house);
        actor.setApartment(apartment);
        actor.setLogin(login);
        actor.setPassword(password);
        actorServiceImp.save(actor);
        return "ok";}
        else return "User exists";
    }
}
