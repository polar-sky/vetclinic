package ru.vlsu.vetclinic.persistence;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "pettype")
public class PetType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String type_name;

    public PetType() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type_name;
    }

    public void setType(String typeName) {
        this.type_name = typeName;
    }
}
