package ru.vlsu.vetclinic.persistence;

import javax.persistence.*;
//добавла тип данных date
import java.util.Date;

@Entity
@Table(name = "schedule")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="vetid")
    private Vet vetid;

    private java.sql.Date date;

    //Конструктор
    public Schedule() {
    }

    //Геттеры сеттеры
    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Vet getVetid() {
        return vetid;
    }

    public void setVetid(Vet vetid) {
        this.vetid = vetid;
    }

    public java.sql.Date getDate() {
        return date;
    }

    public void setDate(java.sql.Date date) {
        this.date = date;
    }
}
