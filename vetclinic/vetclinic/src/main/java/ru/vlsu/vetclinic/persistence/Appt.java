package ru.vlsu.vetclinic.persistence;

import javax.persistence.*;

@Entity
@Table(name = "appt")
public class Appt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private  String typeAppt;

    //тип данных который подходит для sql'евского date
    //@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    //@Temporal (TemporalType.TIMESTAMP)
    private java.sql.Date date;

    private  String description;

    private  String diagnose;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="vetid")
    private Vet vetid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="clientid")
    private User clientid;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="petid")
    private Pet petid;

    //Конструктор
    public Appt() {
    }

    //Геттеры сеттеры
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeAppt() {
        return typeAppt;
    }

    public void setTypeAppt(String typeAppt) {
        this.typeAppt = typeAppt;
    }

    public java.sql.Date getDate() {
        return date;
    }

    public void setDate(java.sql.Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void getDescription(String description) {
        this.description = description;
    }

    public String getDiagnose() {
        return diagnose;
    }

    public void getDiagnose(String diagnose) {
        this.diagnose = diagnose;
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
