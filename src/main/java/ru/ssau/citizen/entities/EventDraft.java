package ru.ssau.citizen.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "eventsdraft")
public class EventDraft {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Actor actor;

    @ManyToOne
    private Rubric rubric;

    @OneToOne
    private Address address;

    @OneToOne
    private Template template;

    private boolean status;

    private String messageSubject;

    private String messageText;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Photo> photo;
    private LocalDate currentDate;

    private boolean result;

}
