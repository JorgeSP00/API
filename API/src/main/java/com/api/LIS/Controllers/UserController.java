package com.api.LIS.Controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.api.LIS.Models.User;
import com.api.LIS.Services.UserService;

import java.util.List;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Create a new user")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "User created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/")
    public ResponseEntity<Object> createUser(@RequestBody @Valid User user) {
        return userService.newUser(user);
    }

    @Operation(summary = "Get all users")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Users retrieved successfully"),
        @ApiResponse(responseCode = "204", description = "No found users"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUsers() {
        return userService.getAllUsers();
    }

    @Operation(summary = "Get user by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "User not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @Operation(summary = "Update user by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User updated successfully"),
        @ApiResponse(responseCode = "404", description = "User not found"),
        @ApiResponse(responseCode = "400", description = "Invalid input"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable int id, @RequestBody @Valid User user) {
        return userService.updateUser(id, user);
    }

    @Operation(summary = "Delete user by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "User deleted successfully"),
        @ApiResponse(responseCode = "404", description = "User not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        return userService.deleteUser(id);
    }

    @Operation(summary = "Find active users with no active vehicles")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Query executed successfully"),
        @ApiResponse(responseCode = "204", description = "No content found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/findActiveUsersWithNoActiveVehicles")
    public ResponseEntity<List<Object[]>> findActiveUsersWithNoActiveVehicles() {
        return userService.findActiveUsersWithNoActiveVehicles();
    }
}
