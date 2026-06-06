package org.example.controller;

import jakarta.validation.Valid;
import org.example.dto.DoctorRequest;
import org.example.dto.DoctorResponse;
import org.example.mapper.DoctorMapper;
import org.example.model.Doctor;
import org.example.services.DoctorService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping
    public DoctorResponse addDoctor(@Valid @RequestBody DoctorRequest request) {
        Doctor doctor = doctorService.addDoctor(
                request.getName(),
                request.getSurname(),
                request.getSpecialty()
        );
        return DoctorMapper.toResponse(doctor);
    }

    @GetMapping
    public List<DoctorResponse> getAllDoctors() {
        List<DoctorResponse> result = new ArrayList<>();
        for (Doctor doctor : doctorService.getAllDoctors()) {
            result.add(DoctorMapper.toResponse(doctor));
        }
        return result;
    }

    @GetMapping("/{id}")
    public DoctorResponse getDoctorById(@PathVariable Long id) {
        Doctor doctor = doctorService.getDoctorById(id);
        return doctor != null ? DoctorMapper.toResponse(doctor) : null;
    }

    @PatchMapping("/{id}/specialty")
    public DoctorResponse changeDoctorSpecialty(@PathVariable Long id, @RequestParam String specialty) {
        Doctor doctor = doctorService.changeDoctorSpecialty(id, specialty);
        return doctor != null ? DoctorMapper.toResponse(doctor) : null;
    }

    @DeleteMapping("/{id}")
    public void deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
    }
}