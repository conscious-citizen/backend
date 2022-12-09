package ru.ssau.citizen.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Rubric {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @OneToMany(mappedBy = "rubric")
//    List<Event> events;

    private String name;

    private String description;

    private String image;

    @OneToOne
    Institution institution;
}
