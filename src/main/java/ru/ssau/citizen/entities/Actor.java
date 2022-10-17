package ru.ssau.citizen.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String patronymic;

    private int phoneNumber;

    private String eMail;

    private String city;

    private String street;

    private String house;

    private String apartment;

    private String login;

    private String password;

    private boolean status;

    private boolean isMailing;

    @ManyToOne
    private Role role;

    @OneToMany(mappedBy = "actor")
    private List<Event> events;

}
