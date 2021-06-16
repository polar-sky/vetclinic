package ru.vlsu.vetclinic.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApptRepository extends JpaRepository<Appt, Integer> {
    List<Appt> findByClientidUsername(String username);
    List<Appt> findByVetid (Vet vetid);
}
