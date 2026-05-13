package org.example.services;

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

    public void addPet(String name, String animalType, Long ownerId) {
        Owner owner = ownerRepo.findById(ownerId).orElse(null);

        if (owner != null) {
            petRepo.save(new Pet(name, animalType, owner));
        }
    }

    public Iterable<Pet> getAllPets() {
        return petRepo.findAll();
    }

    public void deletePet(Long id) {
        petRepo.deleteById(id);
    }

    public void changePetName(Long petId, String newName) {
        Pet pet = petRepo.findById(petId).orElse(null);
        if (pet != null && newName != null) {
            pet.setName(newName);
            petRepo.save(pet);
        }
    }
}
