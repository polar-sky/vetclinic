package ru.vlsu.vetclinic.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.vlsu.vetclinic.persistence.PetRepository;

@Controller
public class PetController {

    private PetRepository rep;

    //autowired - при создании данного класса спринг будет искать есть ли класс, реализующий интерфейс petrepository и передаст этот класс в метод
    @Autowired
    public PetController(PetRepository repository){
        this.rep = repository;
    }

    //метод для возврата главной хтмл странички список животных
    @GetMapping
    public String indexPage(Model model){

        model.addAttribute("pets", rep.findAll());

        return "index";
    }
}
