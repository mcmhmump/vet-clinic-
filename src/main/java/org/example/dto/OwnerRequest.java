package org.example.dto;

import jakarta.validation.constraints.NotBlank;

public class OwnerRequest {
    @NotBlank(message = "Имя не может быть пустым")
    private String name;

    @NotBlank(message = "Фамилия не может быть пустой")
    private String surname;

    @NotBlank(message = "Номер телефона обязателен")
    private String phoneNumber;

    public OwnerRequest() {}
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
}