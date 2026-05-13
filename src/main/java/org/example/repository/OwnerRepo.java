package org.example.repository;

import org.example.model.Owner;
import org.springframework.data.repository.CrudRepository;

public interface OwnerRepo extends CrudRepository<Owner, Long> {
}
