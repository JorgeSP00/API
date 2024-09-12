package com.api.LIS.Controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.api.LIS.Models.Car;
import com.api.LIS.Services.CarService;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @Operation(summary = "Create a new car")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Car created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/")
    public ResponseEntity<Object> createCar(@RequestBody Car car) {
        return carService.newCar(car);
    }

    @Operation(summary = "Get all cars")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cars retrieved successfully"),
        @ApiResponse(responseCode = "204", description = "No found cars"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/")
    public ResponseEntity<List<Car>> getAllCars() {
        return carService.getAllCars();
    }

    @Operation(summary = "Get car by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Car retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "Car not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable int id) {
        return carService.getCarById(id);
    }

    @Operation(summary = "Update car by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Car updated successfully"),
        @ApiResponse(responseCode = "404", description = "Car not found"),
        @ApiResponse(responseCode = "400", description = "Invalid input"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCar(@PathVariable int id, @RequestBody Car car) {
        return carService.updateCar(id, car);
    }

    @Operation(summary = "Delete car by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Car deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Car not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable int id) {
        return carService.deleteCar(id);
    }
}
