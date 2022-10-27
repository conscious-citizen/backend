package ru.ssau.citizen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ssau.citizen.entities.ERole;
import ru.ssau.citizen.entities.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(ERole name);
}
