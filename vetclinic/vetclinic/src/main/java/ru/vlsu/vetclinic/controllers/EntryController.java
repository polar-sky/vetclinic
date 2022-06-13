package ru.vlsu.vetclinic.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.vlsu.vetclinic.persistence.*;
import ru.vlsu.vetclinic.service.EntryService;

import java.util.Date;

import java.security.Principal;
import java.util.List;

@Controller
public class EntryController {

    private EntryRepository entryRepo;
    private VetRepository vetRepo;
    private UserRepository userRepo;
    private ScheduleRepository scheduleRepo;
    private PetRepository petRepo;
    private EntryService entryService;

    //autowired - при создании данного класса спринг будет искать есть ли класс, реализующий интерфейс entryrepository и передаст этот класс в метод
    @Autowired
    public EntryController(EntryRepository entryRepository, PetRepository рetRepository, VetRepository vetRepository, UserRepository userRepository, ScheduleRepository scheduleRepository, EntryService entryService){
        this.entryRepo = entryRepository;
        this.vetRepo= vetRepository;
        this.userRepo = userRepository;
        this.scheduleRepo = scheduleRepository;
        this.petRepo = рetRepository;
        this.entryService = entryService;
    }

    //*ЗАЯВКИ*
    //метод для возврата странички списка заявок
    //изменила маппинг, чтобы записи выводились на страничку /entries, а не на index
    @GetMapping("/entriesclient")
    public String entryPageForClient(Model model, Principal principal) {
        model.addAttribute("entries", entryService.entriesClient(principal));
        return "entriesclient";
    }

    @GetMapping("/entriesvet")
    public String entryPageForVet(Model model, Principal principal) {
        model.addAttribute("entries", entryService.entriesVet(principal));
        return "entriesvet";
    }

    @PostMapping("/saveentry")
    public String saveEntry(Entry entry, Principal principal){
        entryService.saveEntry(entry, principal);
        return "redirect:/entriesclient";

    }

    @GetMapping("/entriesclient/delete/{id}") //удалять сможет только клиент
    public String showDeleteEntry(@PathVariable("id") Integer id){
        entryService.deleteEntry(id);
        return "redirect:/entriesclient";
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
}