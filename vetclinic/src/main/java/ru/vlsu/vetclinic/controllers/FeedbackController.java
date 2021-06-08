package ru.vlsu.vetclinic.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
}
