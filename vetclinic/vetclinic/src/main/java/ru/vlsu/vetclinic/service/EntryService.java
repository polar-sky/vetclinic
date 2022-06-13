package ru.vlsu.vetclinic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.vlsu.vetclinic.persistence.*;
import java.util.Date;

import java.security.Principal;
import java.util.List;

@Service
public class EntryService {

    private EntryRepository entryRepo;
    private VetRepository vetRepo;
    private UserRepository userRepo;
    private ScheduleRepository scheduleRepo;
    private PetRepository petRepo;

    //возврат всех заявок клиента
    public List entriesClient(Principal principal) {
        List<Entry> entries;
        entries = entryRepo.findByClientidUsername(principal.getName());
        return entries;
    }

    //возврат всех заявок врача
    public List entriesVet(Principal principal) {
        User user =userRepo.findByUsername(principal.getName()).get();
        List<Entry> entries;
        entries = entryRepo.findByVetid(user.getVetid());
        return entries;
    }

    //сохранение заявки
    public void saveEntry(Entry entry, Principal principal){
        User user = userRepo.findByUsername(principal.getName()).get();
        entry.setClientid(user);
        entryRepo.save(entry);
    }

    //удаление заявки
    public void deleteEntry(Integer id){
        Entry entry = entryRepo.findById(id).get();
        Date date = java.sql.Date.valueOf(java.time.LocalDate.now());
        if (entry.getDate().compareTo(date)==1)      //надо создавать свободную дату только если она свежая
        {
            Schedule schedule = new Schedule();   //теперь восстанавливаем свободную запись, место к врачу освободилось
            schedule.setVetid(entry.getVetid());
            schedule.setDate(entry.getDate());
            scheduleRepo.save(schedule);
        }
        entryRepo.deleteById(id);
    }
}
