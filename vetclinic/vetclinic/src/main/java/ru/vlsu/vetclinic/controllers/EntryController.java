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
    @GetMapping("/entries")
    public String entryPage(Model model, Principal principal){

        List<Entry> entries;
        entries = entryRepo.findByClientidUsername(principal.getName());
        model.addAttribute("entries", entries);
        return "entries";
    }

    @GetMapping("/newentry")
    public String createEntry(@PathVariable("id") Integer id, Model model, Principal principal){    //здесь типа передаётся айди врача, но скорее всего неправильно
        List<Pet> pets;
        pets = petRepo.findByClientidUsername(principal.getName()); //выпадающий список животных
        Vet vet = vetRepo.findById(id).get();
        List <Schedule> schedules;
        schedules = scheduleRepo.findByVetid(vet.getId()); //выпадающий список даты и времени
        Entry entry = new Entry();
        model.addAttribute("entry", entry);
        model.addAttribute("schedules", schedules);
        return "newentry";
    }

    @PostMapping("/save")
    public String saveEntry(Entry entry, Integer vetid, Principal principal){ //надо передать vetid
        User user = userRepo.findByUsername(principal.getName()).get();
        Vet vet = vetRepo.findById(vetid).get();  //не знаю как правильнее: передать vetid в этот метод
        entry.setVetid(vet);                      //или поступить также как с pettypes (передать айди из формы в метод save)

        entry.setClientid(user);
        //также надо удалить время которое клиент занял, но мне надо вытащить id schedule который он выбрал в выпадающем списке, хз как
        entryRepo.save(entry);
        return "redirect:/";
    }

}