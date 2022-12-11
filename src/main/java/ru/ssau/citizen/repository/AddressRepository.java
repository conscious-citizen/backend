package ru.ssau.citizen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ssau.citizen.entities.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}