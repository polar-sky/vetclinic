package ru.vlsu.vetclinic.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.vlsu.vetclinic.persistence.Pet;
import ru.vlsu.vetclinic.persistence.PetRepository;
import ru.vlsu.vetclinic.persistence.PetType;
import ru.vlsu.vetclinic.persistence.PetTypeRepository;

import java.util.List;

@Controller
public class PetController {

    private final PetRepository petRepo;
    private final PetTypeRepository typeRepo;

    //autowired - при создании данного класса спринг будет искать есть ли класс, реализующий интерфейс petrepository и передаст этот класс в метод
    @Autowired
    public PetController(PetRepository petRepository, PetTypeRepository petTypeRepository){
        this.petRepo = petRepository;
        this.typeRepo= petTypeRepository;
    }

    //метод для возврата главной хтмл странички список животных
    @GetMapping("/")
    public String petPage(Model model){

        List<Pet> pets;
        pets = petRepo.findAll();
        model.addAttribute("pets", pets);
        return "pets";
    }

    //метод для возврата странички видов животных *по приколу чтобы понять в чем сучья проблема
    @GetMapping("/types")
    public String typePage(Model model){

        List<PetType> types;
        types = typeRepo.findAll();
        model.addAttribute("types", types);
        return "types";
    }

    @GetMapping("/newpet")
    public String createPet(Model model){
        List<PetType> types = typeRepo.findAll();
        Pet pet = new Pet();
        model.addAttribute("pet", pet);
        model.addAttribute("types", types);
        return "newpet";
    }
}