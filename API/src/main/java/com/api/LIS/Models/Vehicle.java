package com.api.LIS.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "Vehicle")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)  // Especificar herencia por tabla por clase
public abstract class Vehicle {  // Hacer la clase abstracta, porque no instancias vehículos genéricos
    @Id
    @SequenceGenerator(name = "vehicle_id_seq", sequenceName = "vehicle_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vehicle_id_seq")
    private int id;

    @ManyToOne  // Relación con User (si es una relación en la BD)
    @JoinColumn(name = "user_id")  // Para reflejar la clave foránea
    private User user;
    
    private String color;
    private boolean active;

    // Constructor

    public Vehicle(User user, String color, boolean active) {
        this.user = user;
        this.color = color;
        this.active = active;
    }

    public Vehicle() {}

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
