package ru.vlsu.vetclinic.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.vlsu.vetclinic.persistence.*;

import java.security.Principal;
import java.util.List;

@Controller
public class ApptController {

    private EntryRepository entryRepo;
    private VetRepository vetRepo;
    private UserRepository userRepo;
    private ApptRepository apptRepo;
    private PetRepository petRepo;

    //autowired - при создании данного класса спринг будет искать есть ли класс, реализующий интерфейс entryrepository и передаст этот класс в метод
    @Autowired
    public ApptController(EntryRepository entryRepository, ApptRepository apptRepository, PetRepository рetRepository, VetRepository vetRepository, UserRepository userRepository) {
        this.entryRepo = entryRepository;
        this.vetRepo = vetRepository;
        this.userRepo = userRepository;
        this.apptRepo = apptRepository;
        this.petRepo = рetRepository;
    }

    //*ПОСЕЩЕНИЯ*
    //метод для возврата странички списка приемов
    @GetMapping("/apptspet/{id}") ////////////////////////////вывод приемов конкретного животного
    public String apptPageForClient(@PathVariable("id") Integer petid, Model model) {
        Pet pet = petRepo.getById(petid);
        List<Appt> appts = apptRepo.findByPetid(pet);
        model.addAttribute("apptsl", appts);
        model.addAttribute("pet", pet);
        return "apptspet";
    }

    //общий список приемов клиента
    @GetMapping("/apptsclient") ////////////////////////////для клиента
    public String apptPageForClient(Model model, Principal principal) {
        List<Appt> appts = apptRepo.findByClientidUsername(principal.getName());
        model.addAttribute("apptsl", appts);
        return "apptsclient";
    }

    @GetMapping("/apptsvet") ////////////////////////////для врача
    public String apptPageForVet(Model model, Principal principal) {
        User user =userRepo.findByUsername(principal.getName()).get();
        List<Appt> appts;
       appts = apptRepo.findByVetid(user.getVetid());
       model.addAttribute("appts", appts);
        return "apptsvet";
    }

    @GetMapping("/apptsvet/newappt/{id}") //только для врача//выбирает заявку, передаётся айди и создает посещение
    public String createAppt(@PathVariable("id") Integer id, Model model){
        Entry entry = entryRepo.findById(id).get();
        Appt appt = new Appt();
        model.addAttribute("entry", entry);
        model.addAttribute("appt", appt);
        return "newappt";
    }

    @PostMapping("/saveappt/{id}")
    public String saveAppt(@PathVariable("id") Integer entryid, Appt appt, Principal principal){
        User user = userRepo.findByUsername(principal.getName()).get();
        appt.setVetid(user.getVetid());
        Entry entry = entryRepo.getById(entryid);
        appt.setDate(entry.getDate());
        appt.setPetid(entry.getPetid());
        appt.setClientid(entry.getClientid());
        apptRepo.save(appt);
        //удаляем запись на прием после того как сохранили объект приема
        entryRepo.delete(entry);
        return "redirect:/";
    }
    //видимо два делита надо
    @GetMapping("/apptsvet/deletevet/{id}")
    public String showDeleteApptVet(@PathVariable("id") Integer id){
        apptRepo.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/apptsclient/deleteclient/{id}")
    public String showDeleteApptClient(@PathVariable("id") Integer id){
        apptRepo.deleteById(id);
        return "redirect:/";
    }
}