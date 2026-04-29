package org.example.model;

import jakarta.persistence.*;

@Entity
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String animalType;
    public Pet(){}
    public Pet(String name, String animalType){
        this.name=name;
        this.animalType=animalType;
    }
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getAnimalType() {
        return animalType;
    }

    public void setAnimalType(String animalType) {
        this.animalType = animalType;
    }
    @Override
    public String toString() {
        return "Owner{id=" + id + ", name='" + name + "', animal="+animalType+ "}";
    }
}
