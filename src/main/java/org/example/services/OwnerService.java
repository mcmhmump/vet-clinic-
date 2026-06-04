package org.example.services;

import org.example.model.Owner;
import org.example.repository.OwnerRepo;
import org.springframework.stereotype.Service;

@Service
public class OwnerService {
    private final OwnerRepo ownerRepo;

    public OwnerService(OwnerRepo ownerRepo) {
        this.ownerRepo = ownerRepo;
    }

    public Owner addOwner(String name, String phoneNumber, String surname) {
        Owner owner = new Owner(name, phoneNumber, surname);
        return ownerRepo.save(owner);
    }

    public Iterable<Owner> getAllOwners() {
        return ownerRepo.findAll();
    }

    public Owner getOwnerById(Long id) {
        return ownerRepo.findById(id).orElse(null);
    }

    public void deleteOwner(Long id) {
        ownerRepo.deleteById(id);
    }

    public Owner changeOwnerPhoneNumber(Long ownerId, String newPhoneNumber) {
        Owner owner = ownerRepo.findById(ownerId).orElse(null);
        if (owner != null && newPhoneNumber != null) {
            owner.setPhoneNumber(newPhoneNumber);
            return ownerRepo.save(owner);
        }
        return null;
    }
}