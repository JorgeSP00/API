package com.api.LIS.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.LIS.Models.Car;

public interface ICarRepository extends JpaRepository<Car, Integer> {
}
