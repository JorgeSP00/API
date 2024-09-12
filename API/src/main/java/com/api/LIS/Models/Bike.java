package com.api.LIS.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Bike")
public class Bike extends Vehicle {
    private Boolean basket;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private Type type;

    // Constructor
    public Bike (){
    }
    
    public Bike(User user, String color, boolean active, Boolean basket, Type type) {
        super(user, color, active);
        this.basket = basket;
        this.type = type;
    }

    // Getters y Setters
    public Boolean getBasket() {
        return basket;
    }

    public void setBasket(Boolean basket) {
        this.basket = basket;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}

