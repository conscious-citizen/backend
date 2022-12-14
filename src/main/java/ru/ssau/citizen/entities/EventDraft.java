package ru.ssau.citizen.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

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

    @Lob
    private byte[] photo;

    private LocalDate currentDate;

    private boolean result;

}
