package org.example.mapper;

import org.example.dto.OwnerResponse;
import org.example.model.Owner;

public class OwnerMapper {

    public static OwnerResponse toResponse(Owner owner) {
        OwnerResponse response = new OwnerResponse();
        response.setId(owner.getId());
        response.setName(owner.getName());
        response.setSurname(owner.getSurname());
        response.setPhoneNumber(owner.getPhoneNumber());
        return response;
    }
}