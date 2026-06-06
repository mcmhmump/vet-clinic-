package org.example.controller;

import jakarta.validation.Valid;
import org.example.dto.PetRequest;
import org.example.dto.PetResponse;
import org.example.mapper.PetMapper;
import org.example.model.Pet;
import org.example.services.PetService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/pets")
public class PetController {

    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @PostMapping
    public PetResponse addPet(@Valid @RequestBody PetRequest request) {
        Pet pet = petService.addPet(
                request.getName(),
                request.getAnimalType(),
                request.getOwnerId()
        );
        return pet != null ? PetMapper.toResponse(pet) : null;
    }

    @GetMapping
    public List<PetResponse> getAllPets() {
        List<PetResponse> result = new ArrayList<>();
        for (Pet pet : petService.getAllPets()) {
            result.add(PetMapper.toResponse(pet));
        }
        return result;
    }

    @GetMapping("/{id}")
    public PetResponse getPetById(@PathVariable Long id) {
        Pet pet = petService.getPetById(id);
        return pet != null ? PetMapper.toResponse(pet) : null;
    }

    @PatchMapping("/{id}/name")
    public PetResponse changePetName(@PathVariable Long id, @RequestParam String name) {
        Pet pet = petService.changePetName(id, name);
        return pet != null ? PetMapper.toResponse(pet) : null;
    }

    @DeleteMapping("/{id}")
    public void deletePet(@PathVariable Long id) {
        petService.deletePet(id);
    }
}