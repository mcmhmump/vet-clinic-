package org.example.services;

import org.example.exception.ResourceNotFoundException;
import org.example.model.Owner;
import org.example.model.Pet;
import org.example.repository.OwnerRepo;
import org.example.repository.PetRepo;
import org.springframework.stereotype.Service;

@Service
public class PetService {
    private final PetRepo petRepo;
    private final OwnerRepo ownerRepo;

    public PetService(PetRepo petRepo, OwnerRepo ownerRepo) {
        this.petRepo = petRepo;
        this.ownerRepo = ownerRepo;
    }

    public Pet addPet(String name, String animalType, Long ownerId) {
        Owner owner = ownerRepo.findById(ownerId)
                .orElseThrow(() -> new ResourceNotFoundException("Владелец с id=" + ownerId + " не найден"));

        Pet pet = new Pet(name, animalType, owner);
        return petRepo.save(pet);
    }

    public Iterable<Pet> getAllPets() {
        return petRepo.findAll();
    }

    public Pet getPetById(Long id) {
        return petRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Питомец с id=" + id + " не найден"));
    }

    public void deletePet(Long id) {
        petRepo.deleteById(id);
    }

    public Pet changePetName(Long petId, String newName) {
        Pet pet = petRepo.findById(petId).orElse(null);
        if (pet != null && newName != null) {
            pet.setName(newName);
            return petRepo.save(pet);
        }
        return null;
    }
}