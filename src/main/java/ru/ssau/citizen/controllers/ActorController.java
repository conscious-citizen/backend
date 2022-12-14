package ru.ssau.citizen.controllers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.ssau.citizen.dto.UpdateDto;
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

    @Autowired
    ModelMapper modelMapper;

    @GetMapping("/user")
    @Operation(summary = "Показать информацию о пользователе")
    public ResponseEntity<Actor> showInfoAboutUser(@AuthenticationPrincipal UserDetails userDetails) {
        Actor actor = actorService.findActorByLogin(userDetails.getUsername());
        return ResponseEntity.ok(actor);
    }

//    @PatchMapping("/user/update")
//    @Operation(summary = "Обновление пользователя")
//    public ResponseEntity<Actor> updateUser(@RequestBody Actor actor, @AuthenticationPrincipal UserDetails userDetails){
//        Actor currentActor = actorService.findActorByLogin(userDetails.getUsername());
//        actor.setId(currentActor.getId());
//        if (!currentActor.getPassword().equals(actor.getPassword())) {
//            actor.setPassword(bCryptPasswordEncoder.encode(actor.getPassword()));
//        }
//        actorRepository.save(actor);
//        return ResponseEntity.ok(actor);
//    }

    @PatchMapping("/user/update")
    @Operation(summary = "Обновить информацию о пользователе")
    public ResponseEntity<Actor> updateUser(@RequestBody UpdateDto actorDto, @AuthenticationPrincipal UserDetails userDetails){
        Actor currentActor = actorService.findActorByLogin(userDetails.getUsername());
        Actor newActor = actorService.update(actorDto, currentActor);
        return ResponseEntity.ok(newActor);
    }

}
