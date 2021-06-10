package ru.vlsu.vetclinic.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//Репозиторий - класс, ответственный за сохранение сущностей в бд и подтягивания:) их оттудава
//метод заменен на тот, чтобы поиск осуществлялся по имени, а не по id,отому что я не нашла как реализовывать по id
@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    List<Schedule> findByVetidFull_name(String full_name); //И ЭТО КАК РЕАЛИЗОВАТЬ
}