package org.example.services;

import org.example.model.Doctor;
import org.example.repository.DoctorRepo;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {
    private final DoctorRepo doctorRepo;

    public DoctorService(DoctorRepo doctorRepo) {
        this.doctorRepo = doctorRepo;
    }

    public void addDoctor(String name, String surname, String specialty) {
        doctorRepo.save(new Doctor(name, surname, specialty));
    }

    public Iterable<Doctor> getAllDoctors() {
        return doctorRepo.findAll();
    }

    public void deleteDoctor(Long id) {
        doctorRepo.deleteById(id);
    }

    public void changeDoctorSpecialty(Long doctorId, String newSpecialty) {
        Doctor doctor = doctorRepo.findById(doctorId).orElse(null);
        if (doctor != null && newSpecialty != null) {
            doctor.setSpecialty(newSpecialty);
            doctorRepo.save(doctor);
        }
    }
}