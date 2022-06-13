package ru.vlsu.vetclinic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.vlsu.vetclinic.persistence.*;
import java.util.Date;

import java.security.Principal;
import java.util.List;

@Service
public class FeedbackService {

    private RequestRepository reqRepo;
    private StatusRepository statusRepo;
    private UserRepository userRepo;
    private ReplyRepository replyRepo;


    //список вопросов клиента
    public List requestsClient(Principal principal) {

        List<Request> requests;
        requests = reqRepo.findByClientidUsername(principal.getName());
        return requests;
    }

    //список вопросов врача
    public List requestsVet(){
        List<Request> requests;
        Status status = statusRepo.getById(1);
        requests = reqRepo.findByStatus(status);
        return requests;
    }

    //создание вопроса
    public void createRequest(){
        Request request = new Request();
    }

    //создание ответа
    public void createReply(Integer id){
        Reply reply = new Reply();
    }

    //сохранение вопроса
    public void saveRequest(Request request, Principal principal){
        User user = userRepo.findByUsername(principal.getName()).get();
        request.setClientid(user);
        request.setDate(java.sql.Date.valueOf(java.time.LocalDate.now()));
        request.setStatus(statusRepo.getById(1));
        reqRepo.save(request);
    }

    //сохраение ответа
    public void saveReply(Integer id, Reply reply){
        Request request = reqRepo.findById(id).get();
        reply.setDate(java.sql.Date.valueOf(java.time.LocalDate.now()));
        request.addReply(reply);
        request.setStatus(statusRepo.getById(2));
        reqRepo.save(request);
    }

    //удаление вопроса
    public void deleteRequest(Integer id){
        reqRepo.deleteById(id);
    }

    //удаление ответа
    public void deleteReply(Integer id){
        replyRepo.deleteById(id);
    }
}
