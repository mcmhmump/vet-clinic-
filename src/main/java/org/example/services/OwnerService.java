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

    public void addOwner(String name, String phoneNumber, String surname) {
        ownerRepo.save(new Owner(name, phoneNumber, surname));
    }

    public Iterable<Owner> getAllOwners() {
        return ownerRepo.findAll();
    }

    public void deleteOwner(Long id) {
        ownerRepo.deleteById(id);
    }

    public void changeOwnerPhoneNumber(Long ownerId, String newPhoneNumber) {
        Owner owner = ownerRepo.findById(ownerId).orElse(null);
        if (owner != null && newPhoneNumber != null) {
            owner.setPhoneNumber(newPhoneNumber);
            ownerRepo.save(owner);
        }
    }
}
