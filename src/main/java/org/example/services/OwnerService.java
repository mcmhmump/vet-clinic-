package org.example.services;

import org.example.exception.ResourceNotFoundException;
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
        return ownerRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Владелец с id=" + id + " не найден"));
    }

    public void deleteOwner(Long id) {
        if (!ownerRepo.existsById(id)) {
            throw new ResourceNotFoundException("Владелец с id=" + id + " не найден");
        }
        ownerRepo.deleteById(id);
    }

    public Owner changeOwnerPhoneNumber(Long ownerId, String newPhoneNumber) {
        Owner owner = getOwnerById(ownerId);

        if (newPhoneNumber == null || newPhoneNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Номер телефона не может быть пустым");
        }

        owner.setPhoneNumber(newPhoneNumber);
        return ownerRepo.save(owner);
    }
}