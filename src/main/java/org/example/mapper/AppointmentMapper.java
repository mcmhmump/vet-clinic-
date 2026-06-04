package org.example.mapper;

import org.example.dto.AppointmentResponse;
import org.example.model.Appointment;

public class AppointmentMapper {

    public static AppointmentResponse toResponse(Appointment appointment) {
        AppointmentResponse response = new AppointmentResponse();
        response.setId(appointment.getId());
        response.setAppointmentDate(appointment.getAppointmentDate());
        response.setDiagnosis(appointment.getDiagnosis());

        if (appointment.getPet() != null) {
            response.setPetId(appointment.getPet().getId());
            response.setPetName(appointment.getPet().getName());
        }

        if (appointment.getDoctor() != null) {
            response.setDoctorId(appointment.getDoctor().getId());
            response.setDoctorName(appointment.getDoctor().getName());
            response.setDoctorSurname(appointment.getDoctor().getSurname());
            response.setDoctorSpecialty(appointment.getDoctor().getSpecialty());
        }

        return response;
    }
}