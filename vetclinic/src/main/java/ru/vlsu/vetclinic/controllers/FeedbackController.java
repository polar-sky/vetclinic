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
public class FeedbackController {

    private RequestRepository reqRepo;
    private StatusRepository statusRepo;
    private UserRepository userRepo;

    @Autowired
    public FeedbackController(RequestRepository requestRepository, StatusRepository statusRepository, UserRepository userRepository){

        this.reqRepo = requestRepository;
        this.statusRepo= statusRepository;
        this.userRepo = userRepository;
    }

    //*ОБРАТНАЯ СВЯЗЬ*
    //метод для возврата странички списка запросов
    @GetMapping("/requests")
    public String requestsPage(Model model, Principal principal){

        List<Request> requests;
        requests = reqRepo.findByClientidUsername(principal.getName());
        model.addAttribute("requests", requests);
        return "requests";

    }

    //Создание вопросика
    @GetMapping("/newrequest")
    public String createRequest(Model model){
        List<Status> statuses = statusRepo.findAll();
        Request request = new Request();
        model.addAttribute("request", request);
        model.addAttribute("statuses", statuses);
        return "newrequest";
    }

    //Метод собственно сохранения вопросика
    @PostMapping("/saverequest")
    public String saveRequest(Request request, Principal principal){
        User user = userRepo.findByUsername(principal.getName()).get();
        request.setClientid(user);
        String currentDate = String.valueOf(java.time.LocalDate.now());
        request.setDate(currentDate);
        reqRepo.save(request);
        return "redirect:/requests";
    }

    /*
    @GetMapping("/pets/edit/{id}")
    public String showEditPet(@PathVariable("id") Integer id, Model model){
        Pet pet = petRepo.findById(id).get();
        List<PetType> types = typeRepo.findAll();
        model.addAttribute("pet", pet);
        model.addAttribute("types", types);
        return "newpet";
    }

    @GetMapping("/pets/delete/{id}")
    public String showDeletePet(@PathVariable("id") Integer id, Model model){
        petRepo.deleteById(id);
        return "redirect:/";
    }*/
}
