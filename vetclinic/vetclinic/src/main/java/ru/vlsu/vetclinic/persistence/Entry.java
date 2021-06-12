package ru.vlsu.vetclinic.persistence;

import javax.persistence.*;
//импортировала тип данных date
import java.util.Date;

@Entity
@Table(name = "entry")
public class Entry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="vetid")
    private Vet vetid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="clientid")
    private User clientid;
//добавлено животное
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="petid")
    private Pet petid;

    //Конструктор
    public Entry() {
    }

    //Геттеры сеттеры
    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getClientid() {
        return clientid;
    }

    public void setClientid(User clientid) {
        this.clientid = clientid;
    }

    public Vet getVetid() {
        return vetid;
    }

    public void setVetid(Vet vetid) {
        this.vetid = vetid;
    }

    //изменила byte на int
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
