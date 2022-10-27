package ru.ssau.citizen.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String patronymic;

    private long phoneNumber;


    private String email;

    private String city;

    private String street;

    private String house;

    private String apartment;

    private String login;

    private String password;

    private boolean status;

    private boolean isMailing;



    private String resetPasswordToken;

    
    public Actor() {
    }

    public Actor(String email, String city, String street,
                 String house, String apartment, String login, String password) {
        this.email = email;
        this.city = city;
        this.street = street;
        this.house = house;
        this.apartment = apartment;
        this.login = login;
        this.password = password;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JsonManagedReference
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();


    @OneToMany(mappedBy = "actor")
    private List<Event> events;
}
