package org.example.repository;

import org.example.model.Pet;
import org.springframework.data.repository.CrudRepository;

import java.util.stream.Stream;

public interface PetRepo extends CrudRepository<Pet, Long> {
    Stream<Pet> findByOwnerId(Long ownerId);
}
