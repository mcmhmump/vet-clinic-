package org.example.services;

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
        Pet pet = petRepo.findById(petId).orElse(null);
        Doctor doctor = doctorRepo.findById(doctorId).orElse(null);

        if (pet != null && doctor != null) {
            Appointment appointment = new Appointment(date, diagnosis, pet, doctor);
            return appointmentRepo.save(appointment);
        }

        return null;
    }

    public Iterable<Appointment> getAllAppointments() {
        return appointmentRepo.findAll();
    }

    public Appointment getAppointmentById(Long id) {
        return appointmentRepo.findById(id).orElse(null);
    }

    public void deleteAppointment(Long id) {
        appointmentRepo.deleteById(id);
    }

    public Appointment changeAppointmentDiagnosis(Long appointmentId, String newDiagnosis) {
        Appointment appointment = appointmentRepo.findById(appointmentId).orElse(null);
        if (appointment != null && newDiagnosis != null) {
            appointment.setDiagnosis(newDiagnosis);
            return appointmentRepo.save(appointment);
        }
        return null;
    }

    public Stream<Appointment> getAppointmentsByDoctorId(Long doctorId) {
        return appointmentRepo.findByDoctorId(doctorId);
    }

    public Stream<Appointment> getAppointmentsByPetId(Long petId) {
        return appointmentRepo.findByPetId(petId);
    }
}