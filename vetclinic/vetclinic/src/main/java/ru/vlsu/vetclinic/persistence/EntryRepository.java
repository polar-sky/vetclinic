package ru.vlsu.vetclinic.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//Репозиторий - класс, ответственный за сохранение сущностей в бд и подтягивания:) их оттудава
@Repository
public interface EntryRepository extends JpaRepository<Entry, Integer> {
    List<Entry> findByClientidUsername(String username);
    Entry findByDateAndVetid(java.sql.Date date, Vet vetid);
    List<Entry> findByVetid (Vet vetid);
}