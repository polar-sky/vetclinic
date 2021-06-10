package ru.vlsu.vetclinic.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.vlsu.vetclinic.persistence.*;


import java.security.Principal;
import java.util.List;

@Controller
public class VetController {

    private EntryRepository entryRepo;
    private VetRepository vetRepo;
    private UserRepository userRepo;
    private ScheduleRepository scheduleRepo;

    //autowired - при создании данного класса спринг будет искать есть ли класс, реализующий интерфейс vetrepository и передаст этот класс в метод
    @Autowired
    public VetController(EntryRepository entryRepository, VetRepository vetRepository, UserRepository userRepository, ScheduleRepository scheduleRepository){
        this.entryRepo = entryRepository;
        this.vetRepo= vetRepository;
        this.userRepo = userRepository;
        this.scheduleRepo = scheduleRepository;

    }

    //*ВРАЧИ*
    //метод для возврата странички списка врачей
    //опять изменила маппинг. врачи по другой ссылке отображаются
    @GetMapping("/vets")
    public String vetPage(Model model, Principal principal){

        List<Vet> vets;
        //вывод всех врачей.
        vets = vetRepo.findAll();
        model.addAttribute("vets", vets);
        return "vets";
    }



}