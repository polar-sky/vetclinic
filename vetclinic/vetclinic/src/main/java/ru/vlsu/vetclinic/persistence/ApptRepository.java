package ru.vlsu.vetclinic.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ApptRepository extends JpaRepository<Appt, Integer> {

    List<Appt> findByClientidUsername(String username);
    List<Appt> findByPetid(Pet pet);
    List<Appt> findByVetid (Vet vetid);
}
