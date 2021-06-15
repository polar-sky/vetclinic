package ru.vlsu.vetclinic.controllers;

import com.sun.xml.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.vlsu.vetclinic.persistence.*;
import java.util.Date;

import java.security.Principal;
//import java.sql.Date;
import java.util.List;

@Controller
public class EntryController {

    private EntryRepository entryRepo;
    private VetRepository vetRepo;
    private UserRepository userRepo;
    private ScheduleRepository scheduleRepo;
    private PetRepository petRepo;

    //autowired - при создании данного класса спринг будет искать есть ли класс, реализующий интерфейс entryrepository и передаст этот класс в метод
    @Autowired
    public EntryController(EntryRepository entryRepository, PetRepository рetRepository, VetRepository vetRepository, UserRepository userRepository, ScheduleRepository scheduleRepository){
        this.entryRepo = entryRepository;
        this.vetRepo= vetRepository;
        this.userRepo = userRepository;
        this.scheduleRepo = scheduleRepository;
        this.petRepo = рetRepository;
    }

    //*ЗАЯВКИ*
    //метод для возврата странички списка заявок
    //изменила маппинг, чтобы записи выводились на страничку /entries, а не на index
    @GetMapping("/entriesclient") ////////////////////////////для клиента
    public String entryPageForClient(Model model, Principal principal) {
        List<Entry> entries;
        entries = entryRepo.findByClientidUsername(principal.getName());
        model.addAttribute("entries", entries);
        return "entriesclient";
    }

    @GetMapping("/entriesvet") ////////////////////////////для врача
    public String entryPageForVet(Model model, Principal principal) {
        User user =userRepo.findByUsername(principal.getName()).get();
        List<Entry> entries;
        entries = entryRepo.findByVetid(user.getVetid().getId()); //здесь ошибка в представлении
        model.addAttribute("entries", entries);
        return "entriesvet";
    }

    @GetMapping("/vets/newentry/{id}")
    public String createEntry(@PathVariable("id") Integer id, Model model, Principal principal){    //здесь типа передаётся айди врача, но скорее всего неправильно
        List<Pet> pets;
        pets = petRepo.findByClientidUsername(principal.getName()); //выпадающий список животных
        Vet vet = vetRepo.findById(id).get();
        List <Schedule> schedules = scheduleRepo.findByVetid(vet); //выпадающий список даты и времени
        Entry entry = new Entry();

        entry.setVetid(vet);
        model.addAttribute("entry", entry);
        model.addAttribute("schedules", schedules);
        model.addAttribute("pets", pets);
        model.addAttribute("vet", vet);
        return "newentry";
    }

    @PostMapping("/saveentry")
    public String saveEntry(Entry entry, Principal principal){
        User user = userRepo.findByUsername(principal.getName()).get();
        entry.setClientid(user);
        entryRepo.save(entry);
        Schedule schedule = scheduleRepo.findByDateAndVetid(entry.getDate(), entry.getVetid()) ;
        scheduleRepo.delete(schedule);
        return "redirect:/entriesclient";

    }

    @GetMapping("/entriesclient/delete/{id}") //удалять сможет только клиент
    public String showDeleteEntry(@PathVariable("id") Integer id){
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
        return "redirect:/entriesclient";
    }
}