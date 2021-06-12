package ru.vlsu.vetclinic.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
    private ReplyRepository replyRepo;

    @Autowired
    public FeedbackController(RequestRepository requestRepository, StatusRepository statusRepository, UserRepository userRepository, ReplyRepository replyRepository){

        this.reqRepo = requestRepository;
        this.statusRepo= statusRepository;
        this.userRepo = userRepository;
        this.replyRepo = replyRepository;

    }

    //*ВОПРОСЫ*
    //метод для возврата странички списка вопросов для клиента
    @GetMapping("/requests")
    public String requestsPage(Model model, Principal principal){

        List<Request> requests;
        requests = reqRepo.findByClientidUsername(principal.getName());
        model.addAttribute("requests", requests);
        return "requests";

    }

    //метод для возврата странички списка вопросов для ветврача со статусом "Ожидает ответа"
    @GetMapping("/vetrequests")
    public String requestsVetPage(Model model){
        List<Request> requests;
        Status status = statusRepo.getById(1);
        requests = reqRepo.findByStatus(status);
        model.addAttribute("requests", requests);
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

    //Метод собственно сохранения вопросика
    @PostMapping("/saverequest")
    public String saveRequest(Request request, Principal principal){
        User user = userRepo.findByUsername(principal.getName()).get();
        request.setClientid(user);
        request.setDate(java.sql.Date.valueOf(java.time.LocalDate.now()));
        request.setStatus(statusRepo.getById(1));
        reqRepo.save(request);
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
        reqRepo.deleteById(id);
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
        Request request = reqRepo.findById(id).get();
        reply.setDate(java.sql.Date.valueOf(java.time.LocalDate.now()));
        request.addReply(reply);
        request.setStatus(statusRepo.getById(2));
        reqRepo.save(request);
        return "redirect:/vetrequests";
    }


}
