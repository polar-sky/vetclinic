package ru.vlsu.vetclinic.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.vlsu.vetclinic.VetclinicApplication;
import ru.vlsu.vetclinic.service.UserRepr;
import ru.vlsu.vetclinic.service.UserService;

import javax.validation.Valid;

@Controller
public class AuthorizationController {

    private final UserService userService;
    private final static Logger log =
            Logger.getLogger(VetclinicApplication.class);


    @Autowired
    public AuthorizationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login(){

        log.info("saleh sasat");
        log.warn("saleh sasat");
        log.error("saleh sasat");

        return "login";
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("user", new UserRepr());
        return "register";
    }

    //коммит
    @PostMapping("/register")
    public String registerNewUser(@Valid UserRepr userRepr, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "register";
        }
        if (!userRepr.getPassword().equals(userRepr.getRepeatPassword())){
            bindingResult.rejectValue("password", "", "Пароли не совпадают");
            return "register";
        }

        userService.create(userRepr);
        return "redirect:/login";
    }
}
