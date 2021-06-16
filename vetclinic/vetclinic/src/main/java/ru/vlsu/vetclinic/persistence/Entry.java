package ru.vlsu.vetclinic.persistence;

import javax.persistence.*;

//импортировала тип данных date

@Entity
@Table(name = "entry")
public class Entry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //тип данных который подходит для sql'евского date
    //@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    //@Temporal (TemporalType.TIMESTAMP)
    private java.sql.Date date;

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

    public void setId(Integer id) {
        this.id = id;
    }

    public java.sql.Date getDate() {
        return date;
    }

    public void setDate(java.sql.Date date) {
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

    public Pet getPetid() {
        return petid;
    }

    public void setPetid(Pet petid) {
        this.petid = petid;
    }
}
