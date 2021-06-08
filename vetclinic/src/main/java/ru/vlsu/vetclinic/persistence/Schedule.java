package ru.vlsu.vetclinic.persistence;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="schedule")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer vetid;

    private Date date;

    public Schedule() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVetid() {
        return vetid;
    }

    public void setVetid(Integer vetid) {
        this.vetid = vetid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
