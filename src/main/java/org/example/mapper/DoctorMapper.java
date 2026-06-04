package org.example.mapper;

import org.example.dto.DoctorResponse;
import org.example.model.Doctor;

public class DoctorMapper {

    public static DoctorResponse toResponse(Doctor doctor) {
        DoctorResponse response = new DoctorResponse();
        response.setId(doctor.getId());
        response.setName(doctor.getName());
        response.setSurname(doctor.getSurname());
        response.setSpecialty(doctor.getSpecialty());
        return response;
    }
}