package org.example.services;

import org.example.exception.ResourceNotFoundException;
import org.example.model.Appointment;
import org.example.model.Doctor;
import org.example.model.Pet;
import org.example.repository.AppointmentRepo;
import org.example.repository.DoctorRepo;
import org.example.repository.PetRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.stream.Stream;

@Service
public class AppointmentService {
    private final AppointmentRepo appointmentRepo;
    private final PetRepo petRepo;
    private final DoctorRepo doctorRepo;

    public AppointmentService(AppointmentRepo appointmentRepo, PetRepo petRepo, DoctorRepo doctorRepo) {
        this.appointmentRepo = appointmentRepo;
        this.petRepo = petRepo;
        this.doctorRepo = doctorRepo;
    }

    public Appointment addAppointment(LocalDateTime date, String diagnosis, Long petId, Long doctorId) {
        Pet pet = petRepo.findById(petId)
                .orElseThrow(() -> new ResourceNotFoundException("Питомец с id=" + petId + " не найден"));

        Doctor doctor = doctorRepo.findById(doctorId)
                .orElseThrow(() -> new ResourceNotFoundException("Доктор с id=" + doctorId + " не найден"));

        Appointment appointment = new Appointment(date, diagnosis, pet, doctor);
        return appointmentRepo.save(appointment);
    }

    public Iterable<Appointment> getAllAppointments() {
        return appointmentRepo.findAll();
    }

    public Appointment getAppointmentById(Long id) {
        return appointmentRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Прием с id=" + id + " не найден"));
    }

    public void deleteAppointment(Long id) {
        if (!appointmentRepo.existsById(id)) {
            throw new ResourceNotFoundException("Прием с id=" + id + " не найден");
        }
        appointmentRepo.deleteById(id);
    }

    public Appointment changeAppointmentDiagnosis(Long appointmentId, String newDiagnosis) {
        Appointment appointment = getAppointmentById(appointmentId);

        if (newDiagnosis == null || newDiagnosis.trim().isEmpty()) {
            throw new IllegalArgumentException("Новый диагноз не может быть пустым");
        }

        appointment.setDiagnosis(newDiagnosis);
        return appointmentRepo.save(appointment);
    }

    public Stream<Appointment> getAppointmentsByDoctorId(Long doctorId) {
        return appointmentRepo.findByDoctorId(doctorId);
    }

    public Stream<Appointment> getAppointmentsByPetId(Long petId) {
        return appointmentRepo.findByPetId(petId);
    }
}