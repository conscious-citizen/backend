package ru.ssau.citizen.controllers;


import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.ssau.citizen.entities.Actor;
import ru.ssau.citizen.repository.ActorRepository;
import ru.ssau.citizen.service.ActorService;

@RestController
@Tag(name = "Актёр", description = "Все методы для работы с пользователем")
public class ActorController {


    @Autowired
    ActorService actorService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    ActorRepository actorRepository;

    @GetMapping("/user")
    public ResponseEntity<Actor> showInfoAboutUser(@AuthenticationPrincipal UserDetails userDetails) {
        Actor actor = actorService.findActorByLogin(userDetails.getUsername());
        return ResponseEntity.ok(actor);
    }

    @PatchMapping("/user/update")
    public ResponseEntity<Actor> updateUser(@RequestBody Actor actor,@AuthenticationPrincipal UserDetails userDetails){
        Actor currentActor = actorService.findActorByLogin(userDetails.getUsername());
        actor.setId(currentActor.getId());
        if (!currentActor.getPassword().equals(actor.getPassword())) {
            actor.setPassword(bCryptPasswordEncoder.encode(actor.getPassword()));
        }
        actorRepository.save(actor);
        return ResponseEntity.ok(actor);
    }




}
