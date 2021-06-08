package ru.vlsu.vetclinic.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<User, Long> {
}
