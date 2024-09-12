package com.api.LIS.Controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.api.LIS.Models.Bike;
import com.api.LIS.Services.BikeService;

import java.util.List;

@RestController
@RequestMapping("/bikes")
public class BikeController {

    private final BikeService bikeService;

    @Autowired
    public BikeController(BikeService bikeService) {
        this.bikeService = bikeService;
    }

    @Operation(summary = "Create a new bike")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Bike created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/")
    public ResponseEntity<Object> createBike(@RequestBody Bike bike) {
        return bikeService.newBike(bike);
    }

    @Operation(summary = "Get all bikes")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Bikes retrieved successfully"),
        @ApiResponse(responseCode = "204", description = "No found bikes"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/")
    public ResponseEntity<List<Bike>> getAllBikes() {
        return bikeService.getAllBikes();
    }

    @Operation(summary = "Get bike by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Bike retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "Bike not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Bike> getBikeById(@PathVariable int id) {
        return bikeService.getBikeById(id);
    }

    @Operation(summary = "Update bike by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Bike updated successfully"),
        @ApiResponse(responseCode = "404", description = "Bike not found"),
        @ApiResponse(responseCode = "400", description = "Invalid input"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateBike(@PathVariable int id, @RequestBody Bike bike) {
        return bikeService.updateBike(id, bike);
    }

    @Operation(summary = "Delete bike by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Bike deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Bike not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBike(@PathVariable int id) {
        return bikeService.deleteBike(id);
    }
}