package org.example.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime appointmentDate;
    private String diagnosis;
    public Appointment(){}
    public Appointment(LocalDateTime appointmentDate, String diagnosis, Pet pet, Doctor doctor){
        this.appointmentDate=appointmentDate;
        this.diagnosis=diagnosis;
        this.pet=pet;
        this.doctor=doctor;
    }
    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDateTime appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }
    public void setPet(Pet pet){
        this.pet=pet;
    }

    public Pet getPet() {
        return pet;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    @Override
    public String toString() {
        return "Appointment{id=" + id + ", date='" + appointmentDate + "', diagnos="+diagnosis+ "}";
    }
}
