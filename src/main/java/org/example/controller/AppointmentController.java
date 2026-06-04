package org.example.controller;

import org.example.dto.AppointmentRequest;
import org.example.dto.AppointmentResponse;
import org.example.mapper.AppointmentMapper;
import org.example.model.Appointment;
import org.example.services.AppointmentService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping
    public AppointmentResponse addAppointment(@RequestBody AppointmentRequest request) {
        Appointment appointment = appointmentService.addAppointment(
                request.getAppointmentDate(),
                request.getDiagnosis(),
                request.getPetId(),
                request.getDoctorId()
        );
        return appointment != null ? AppointmentMapper.toResponse(appointment) : null;
    }

    @GetMapping
    public List<AppointmentResponse> getAllAppointments() {
        List<AppointmentResponse> result = new ArrayList<>();
        for (Appointment appointment : appointmentService.getAllAppointments()) {
            result.add(AppointmentMapper.toResponse(appointment));
        }
        return result;
    }

    @GetMapping("/{id}")
    public AppointmentResponse getAppointmentById(@PathVariable Long id) {
        Appointment appointment = appointmentService.getAppointmentById(id);
        return appointment != null ? AppointmentMapper.toResponse(appointment) : null;
    }

    @GetMapping("/doctor/{doctorId}")
    public List<AppointmentResponse> getAppointmentsByDoctorId(@PathVariable Long doctorId) {
        return appointmentService.getAppointmentsByDoctorId(doctorId)
                .map(AppointmentMapper::toResponse)
                .toList();
    }

    @GetMapping("/pet/{petId}")
    public List<AppointmentResponse> getAppointmentsByPetId(@PathVariable Long petId) {
        return appointmentService.getAppointmentsByPetId(petId)
                .map(AppointmentMapper::toResponse)
                .toList();
    }

    @PatchMapping("/{id}/diagnosis")
    public AppointmentResponse changeDiagnosis(@PathVariable Long id, @RequestParam String diagnosis) {
        Appointment appointment = appointmentService.changeAppointmentDiagnosis(id, diagnosis);
        return appointment != null ? AppointmentMapper.toResponse(appointment) : null;
    }

    @DeleteMapping("/{id}")
    public void deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
    }
}