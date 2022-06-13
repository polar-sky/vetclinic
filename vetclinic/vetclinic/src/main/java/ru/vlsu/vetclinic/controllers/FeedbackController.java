package ru.vlsu.vetclinic.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.vlsu.vetclinic.persistence.*;
import ru.vlsu.vetclinic.service.FeedbackService;

import java.security.Principal;
import java.util.List;

@Controller
public class FeedbackController {

    private RequestRepository reqRepo;
    private StatusRepository statusRepo;
    private UserRepository userRepo;
    private FeedbackService feedbackService;

    @Autowired
    public FeedbackController(RequestRepository requestRepository, StatusRepository statusRepository, UserRepository userRepository,FeedbackService feedbackService){

        this.reqRepo = requestRepository;
        this.statusRepo= statusRepository;
        this.userRepo = userRepository;
        this.feedbackService = feedbackService;

    }

    //*ВОПРОСЫ*
    //метод для возврата странички списка вопросов для клиента
    @GetMapping("/requests")
    public String requestsPage(Model model, Principal principal){
        model.addAttribute("requests", feedbackService.requestsClient(principal));
        return "requests";

    }

    //метод для возврата странички списка вопросов для ветврача со статусом "Ожидает ответа"
    @GetMapping("/vetrequests")
    public String requestsVetPage(Model model){
        model.addAttribute("requests", feedbackService.requestsVet());
        return "vetrequests";

    }

    //метод для возврата странички часто задаваемых вопросов
    @GetMapping("/faq")
    public String faqPage(Model model, Principal principal){

        List<Request> requests;
        requests = reqRepo.findByClientidUsername("admin");
        model.addAttribute("requests", requests);
        return "faq";

    }

    //Создание вопросика
    @GetMapping("/newrequest")
    public String createRequest(Model model){
        Request request = new Request();
        model.addAttribute("request", request);
        return "newrequest";
    }

    @PostMapping("/saverequest")
    public String saveRequest(Request request, Principal principal){
        feedbackService.saveRequest(request, principal);
        return "redirect:/requests";
    }

    //показ странички с деталями вопроса
    @GetMapping("/requests/details/{id}")
    public String showDetailsRequest(@PathVariable("id") Integer id, Model model){
        Request request = reqRepo.findById(id).get();
        model.addAttribute("request", request);
        return "requestdetails";
    }

    //удаление вопроса
    @GetMapping("/requests/delete/{id}")
    public String showDeleteRequest(@PathVariable("id") Integer id, Model model){
        feedbackService.deleteRequest(id);
        return "redirect:/requests";
    }

    //*ОТВЕТЫ*
    //Создание ответа
    @GetMapping("/newreply/{id}")
    public String createReply(@PathVariable("id") Integer id, Model model){
        Reply reply = new Reply();
        Request request = reqRepo.findById(id).get();
        model.addAttribute("reply", reply);
        model.addAttribute("request", request);
        return "newreply";
    }

    //Метод собственно сохранения ответа
    @PostMapping("/savereply/{id}")
    public String saveReply(@PathVariable("id") Integer id, Reply reply){
        feedbackService.saveReply(id,reply);
        return "redirect:/vetrequests";
    }


}
