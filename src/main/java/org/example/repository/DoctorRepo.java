package org.example.repository;

import org.example.model.Doctor;
import org.springframework.data.repository.CrudRepository;

import java.util.stream.Stream;

public interface DoctorRepo extends CrudRepository<Doctor, Long> {
    Stream<Doctor> findBySpecialty(String specialty);
}
