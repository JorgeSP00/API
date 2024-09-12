package com.api.LIS.Controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.api.LIS.Models.Type;
import com.api.LIS.Services.TypeService;

@RestController
@RequestMapping("/types")
public class TypeController {

    private final TypeService typeService;

    @Autowired
    public TypeController(TypeService typeService) {
        this.typeService = typeService;
    }

    @Operation(summary = "Create a new type")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Type created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/")
    public ResponseEntity<Object> createType(@RequestBody Type type) {
        return typeService.createType(type);
    }

    @Operation(summary = "Get all types")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Types retrieved successfully"),
        @ApiResponse(responseCode = "204", description = "No found types"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/")
    public ResponseEntity<Object> getAllTypes() {
        return typeService.getAllTypes();
    }

    @Operation(summary = "Get type by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Type retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "Type not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Object> getTypeById(@PathVariable int id) {
        return typeService.getTypeById(id);
    }

    @Operation(summary = "Update type by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Type updated successfully"),
        @ApiResponse(responseCode = "404", description = "Type not found"),
        @ApiResponse(responseCode = "400", description = "Invalid input"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateType(@PathVariable int id, @RequestBody Type type) {
        return typeService.updateType(id, type);
    }

    @Operation(summary = "Delete type by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Type deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Type not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteType(@PathVariable int id) {
        return typeService.deleteType(id);
    }
}
