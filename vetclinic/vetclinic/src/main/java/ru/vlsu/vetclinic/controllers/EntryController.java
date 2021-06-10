package ru.vlsu.vetclinic.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.vlsu.vetclinic.persistence.*;


import java.security.Principal;
import java.util.List;

@Controller
public class EntryController {

    private EntryRepository entryRepo;
    private VetRepository vetRepo;
    private UserRepository userRepo;
    private ScheduleRepository scheduleRepo;

    //autowired - при создании данного класса спринг будет искать есть ли класс, реализующий интерфейс entryrepository и передаст этот класс в метод
    @Autowired
    public EntryController(EntryRepository entryRepository, VetRepository vetRepository, UserRepository userRepository, ScheduleRepository scheduleRepository){
        this.entryRepo = entryRepository;
        this.vetRepo= vetRepository;
        this.userRepo = userRepository;
        this.scheduleRepo = scheduleRepository;

    }

    //*ЗАЯВКИ*
    //метод для возврата странички списка заявок
    //изменила маппинг, чтобы записи выводились на страничку /entries, а не на index
    @GetMapping("/entries")
    public String entryPage(Model model, Principal principal){

        List<Entry> entries;
        entries = entryRepo.findByClientidUsername(principal.getName());
        model.addAttribute("entries", entries);
        return "entries";
    }


    //добавила принципалы, изменила название метода(смотри SheduleRepository)
    @GetMapping("/newentry")
    public String createEntry(Model model, Principal principal){
        List<Schedule> schedule = scheduleRepo.findByVetidFullName(principal.getName()); // КАК ЭТО РЕАЛИЗОВАТЬ
        Entry entry = new Entry();
        model.addAttribute("entry", entry);
        model.addAttribute("schedule", schedule);
        return "newentry";
    }
// ещё надо как то передавать айди врача на которого нажимаешь на странице, это тоже проблема
}