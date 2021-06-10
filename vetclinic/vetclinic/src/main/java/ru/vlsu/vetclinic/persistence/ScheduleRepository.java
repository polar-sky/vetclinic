package ru.vlsu.vetclinic.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//Репозиторий - класс, ответственный за сохранение сущностей в бд и подтягивания:) их оттудава
@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    List<Schedule> findByVetid(Ineteger vetid); //И ЭТО КАК РЕАЛИЗОВАТЬ
}