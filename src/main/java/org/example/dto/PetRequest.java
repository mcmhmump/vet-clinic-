package org.example.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PetRequest {
    @NotBlank(message = "Имя питомца обязательно")
    private String name;

    @NotBlank(message = "Тип животного обязателен")
    private String animalType;

    @NotNull(message = "ID владельца обязателен")
    private Long ownerId;

    public PetRequest() {}
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getAnimalType() { return animalType; }
    public void setAnimalType(String animalType) { this.animalType = animalType; }
    public Long getOwnerId() { return ownerId; }
    public void setOwnerId(Long ownerId) { this.ownerId = ownerId; }
}