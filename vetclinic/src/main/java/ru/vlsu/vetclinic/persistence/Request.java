package ru.vlsu.vetclinic.persistence;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="request")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="clientid")
    private User clientid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="statusid")
    private Status status;

    @OneToOne(mappedBy = "request", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Reply reply;

    private java.sql.Date date;

    private String text;

    public Request() {
    }

    public void addReply(Reply reply){
        reply.setRequest(this);
        this.reply = reply;
    }

    public Reply getReply() {
        return reply;
    }

    public void setReply(Reply reply) {
        this.reply = reply;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getClientid() {
        return clientid;
    }

    public void setClientid(User clientid) {
        this.clientid = clientid;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public java.sql.Date getDate() {
        return date;
    }

    public void setDate(java.sql.Date date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
