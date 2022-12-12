package ru.ssau.citizen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ssau.citizen.entities.Photo;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {

}
