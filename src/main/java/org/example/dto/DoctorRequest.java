package org.example.dto;

import jakarta.validation.constraints.NotBlank;

public class DoctorRequest {
    @NotBlank(message = "Имя доктора обязательно")
    private String name;

    @NotBlank(message = "Фамилия доктора обязательна")
    private String surname;

    @NotBlank(message = "Специальность не может быть пустой")
    private String specialty;

    public DoctorRequest() {}
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }
    public String getSpecialty() { return specialty; }
    public void setSpecialty(String specialty) { this.specialty = specialty; }
}