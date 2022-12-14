package ru.ssau.citizen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ssau.citizen.entities.Event;
import ru.ssau.citizen.entities.EventDraft;

@Repository
public interface EventDraftRepository extends JpaRepository<EventDraft, Long> {
}
