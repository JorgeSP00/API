package com.api.LIS.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Car")
public class Car extends Vehicle {
    private String plate;
    private Integer capacity;
    private Boolean electrical;

    // Constructor
    public Car (){
    }

    public Car(User user, String color, boolean active, String plate, Integer capacity, Boolean electrical) {
        super(user, color, active);
        this.plate = plate;
        this.capacity = capacity;
        this.electrical = electrical;
    }

    // Getters y Setters
    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Boolean getElectrical() {
        return electrical;
    }

    public void setElectrical(Boolean electrical) {
        this.electrical = electrical;
    }
}

