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

    public Doctor addDoctor(String name, String surname, String specialty) {
        Doctor doctor = new Doctor(name, surname, specialty);
        return doctorRepo.save(doctor);
    }

    public Iterable<Doctor> getAllDoctors() {
        return doctorRepo.findAll();
    }

    public Doctor getDoctorById(Long id) {
        return doctorRepo.findById(id).orElse(null);
    }

    public void deleteDoctor(Long id) {
        doctorRepo.deleteById(id);
    }

    public Doctor changeDoctorSpecialty(Long doctorId, String newSpecialty) {
        Doctor doctor = doctorRepo.findById(doctorId).orElse(null);
        if (doctor != null && newSpecialty != null) {
            doctor.setSpecialty(newSpecialty);
            return doctorRepo.save(doctor);
        }
        return null;
    }
}