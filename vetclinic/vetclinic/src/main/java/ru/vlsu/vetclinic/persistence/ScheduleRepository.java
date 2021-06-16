package ru.vlsu.vetclinic.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

//Репозиторий - класс, ответственный за сохранение сущностей в бд и подтягивания:) их оттудава
//метод заменен на тот, чтобы поиск осуществлялся по имени, а не по id,отому что я не нашла как реализовывать по id
@Repository
@Component
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    //TODO передаем Vet vet так как поле vetid у нас типа vet а не integer
    List<Schedule> findByVetid(Vet vet);

    Schedule findByDateAndVetid(Date date, Vet vetid);
}
