package ru.vlsu.vetclinic.persistence;

import javax.persistence.*;

@Entity
@Table(name = "pet")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Integer clientid;

    private Integer age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_type")
    private PetType type_name;

    //Конструктор
    public Pet() {
    }

    //Геттеры сеттеры
    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getClientid() {
        return clientid;
    }

    public void setClientid(int clientid) {
        this.clientid = clientid;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public PetType getType() {
        return type_name;
    }

    public void setType(PetType type) {
        this.type_name = type;
    }

}
