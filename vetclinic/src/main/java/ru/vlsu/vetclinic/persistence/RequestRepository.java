package ru.vlsu.vetclinic.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Integer>{

    List<Request> findByClientidUsername(String username);

    List<Request> findByStatus(Status status);

}
