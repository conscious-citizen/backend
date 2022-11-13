package ru.ssau.citizen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ssau.citizen.entities.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}
