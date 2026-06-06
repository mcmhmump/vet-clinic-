package org.example.services;

import org.example.exception.ResourceNotFoundException;
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
        return doctorRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Доктор с id=" + id + " не найден"));
    }

    public void deleteDoctor(Long id) {
        if (!doctorRepo.existsById(id)) {
            throw new ResourceNotFoundException("Доктор с id=" + id + " не найден");
        }
        doctorRepo.deleteById(id);
    }

    public Doctor changeDoctorSpecialty(Long doctorId, String newSpecialty) {
        Doctor doctor = getDoctorById(doctorId);

        if (newSpecialty == null || newSpecialty.trim().isEmpty()) {
            throw new IllegalArgumentException("Специальность не может быть пустой");
        }

        doctor.setSpecialty(newSpecialty);
        return doctorRepo.save(doctor);
    }
}