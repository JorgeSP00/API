package com.api.LIS.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Usuario") //Tabla "Usuario", ya que User está reservado en PostgreSQL
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "Mandatory field")
    @Size(max = 50, message = "Name can not exceed 50 characters")
    private String name;
    @NotBlank(message = "Mandatory field")
    @Email(message = "Email should be valid") //Validación de que es un email
    @Size(max = 200, message = "Email can not exceed 200 characters")
    private String email;
    @NotBlank(message = "Mandatory field")
    @Pattern(regexp = "^\\+?[0-9. ()-]{9,15}$", message = "Phone number is not valid") //Validación de que hay entre 9 y 15 caracteres, sólo números o el símbolo +
    private String phone;
    private Integer age;
    @NotBlank(message = "Mandatory field")
    private boolean active;

    // Constructor
    public User (){
    }

    public User(String name, String email, String phone, Integer age, boolean active) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.age = age;
        this.active = active;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
