package ru.vlsu.vetclinic.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.vlsu.vetclinic.persistence.*;


import java.security.Principal;
import java.util.List;

@Controller
public class PetController {

    private PetRepository petRepo;
    private PetTypeRepository typeRepo;
    private UserRepository userRepo;

    //autowired - при создании данного класса спринг будет искать есть ли класс, реализующий интерфейс petrepository и передаст этот класс в метод
    @Autowired
    public PetController(PetRepository petRepository, PetTypeRepository petTypeRepository, UserRepository userRepository){
        this.petRepo = petRepository;
        this.typeRepo= petTypeRepository;
        this.userRepo = userRepository;

    }

    //*ЖИВОТНЫЕ*
    //метод для возврата странички списка животных
    @GetMapping("/pet")
    public String petPage(Model model, Principal principal){

        List<Pet> pets;
        pets = petRepo.findByClientidUsername(principal.getName());
        model.addAttribute("pets", pets);
        return "pets";
    }

    @GetMapping("/newpet")
    public String createPet(Model model){
        List<PetType> types = typeRepo.findAll();
        Pet pet = new Pet();
        model.addAttribute("pet", pet);
        model.addAttribute("types", types);
        return "newpet";
    }

    @PostMapping("/save")
    public String savePet(Pet pet, Principal principal){
        User user = userRepo.findByUsername(principal.getName()).get();
        pet.setClientid(user);
        petRepo.save(pet);
        return "redirect:/pet";
    }

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
        return "redirect:/pet";
    }
}