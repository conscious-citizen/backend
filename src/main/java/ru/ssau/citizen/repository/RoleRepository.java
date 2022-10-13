package ru.ssau.citizen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ssau.citizen.entities.Role;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findRoleByName(String name);
}
