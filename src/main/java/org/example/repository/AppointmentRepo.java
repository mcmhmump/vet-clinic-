package org.example.repository;

import org.example.model.Appointment;
import org.springframework.data.repository.CrudRepository;

import java.util.stream.Stream;

public interface AppointmentRepo extends CrudRepository<Appointment, Long> {
    Stream<Appointment> findByDoctorId(Long doctorId);
    Stream<Appointment> findByPetId(Long petId);
}
