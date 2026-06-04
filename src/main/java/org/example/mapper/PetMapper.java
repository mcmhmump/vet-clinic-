package org.example.mapper;

import org.example.dto.PetResponse;
import org.example.model.Pet;

public class PetMapper {

    public static PetResponse toResponse(Pet pet) {
        PetResponse response = new PetResponse();
        response.setId(pet.getId());
        response.setName(pet.getName());
        response.setAnimalType(pet.getAnimalType());

        if (pet.getOwner() != null) {
            response.setOwnerId(pet.getOwner().getId());
            response.setOwnerName(pet.getOwner().getName());
            response.setOwnerSurname(pet.getOwner().getSurname());
        }

        return response;
    }
}