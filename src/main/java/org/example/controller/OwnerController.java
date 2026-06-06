package org.example.controller;

import jakarta.validation.Valid;
import org.example.dto.OwnerRequest;
import org.example.dto.OwnerResponse;
import org.example.mapper.OwnerMapper;
import org.example.model.Owner;
import org.example.services.OwnerService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/owners")
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @PostMapping
    public OwnerResponse addOwner(@Valid @RequestBody OwnerRequest request) {
        Owner owner = ownerService.addOwner(
                request.getName(),
                request.getPhoneNumber(),
                request.getSurname()
        );
        return OwnerMapper.toResponse(owner);
    }

    @GetMapping
    public List<OwnerResponse> getAllOwners() {
        List<OwnerResponse> result = new ArrayList<>();
        for (Owner owner : ownerService.getAllOwners()) {
            result.add(OwnerMapper.toResponse(owner));
        }
        return result;
    }

    @GetMapping("/{id}")
    public OwnerResponse getOwnerById(@PathVariable Long id) {
        Owner owner = ownerService.getOwnerById(id);
        return owner != null ? OwnerMapper.toResponse(owner) : null;
    }

    @PatchMapping("/{id}/phone")
    public OwnerResponse changePhoneNumber(@PathVariable Long id, @RequestParam String phoneNumber) {
        Owner owner = ownerService.changeOwnerPhoneNumber(id, phoneNumber);
        return owner != null ? OwnerMapper.toResponse(owner) : null;
    }

    @DeleteMapping("/{id}")
    public void deleteOwner(@PathVariable Long id) {
        ownerService.deleteOwner(id);
    }
}