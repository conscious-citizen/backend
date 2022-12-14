package ru.ssau.citizen.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.io.File;
import java.time.LocalDate;


@Data
@Entity
@Table(name = "photos")
@Schema(description = "Информация о фото")
public class Photo {

    @Id
    @GeneratedValue
    private Long id;

    private String photo;

    @ManyToOne(cascade=CascadeType.ALL)
    @JsonIgnore
    private Event event;


}
