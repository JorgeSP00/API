package com.api.LIS.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.LIS.Models.User;
import com.api.LIS.Repositories.IUserRepository;

import jakarta.validation.Valid;

@Service
public class UserService {
    private final IUserRepository userRepository;

    @Autowired
    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Método para crear un nuevo usuario con manejo de errores
    public ResponseEntity<Object> newUser(@Valid User user) {
        try {
            userRepository.save(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (DataAccessException e) {
            return new ResponseEntity<>("Error al guardar el usuario", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>("Error inesperado al crear el usuario", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Método para obtener todos los usuarios con manejo de errores
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            List<User> users = userRepository.findAll();
            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Método para obtener un usuario por ID con manejo de errores
    public ResponseEntity<User> getUserById(int id) {
        try {
            Optional<User> user = userRepository.findById(id);
            if (user.isPresent()) {
                return new ResponseEntity<>(user.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Método para actualizar un usuario con manejo de errores
    public ResponseEntity<Object> updateUser(int id, @Valid User userDetails) {
        try {
            Optional<User> user = userRepository.findById(id);
            if (user.isPresent()) {
                User userToUpdate = user.get();
                userToUpdate.setName(userDetails.getName());
                userToUpdate.setEmail(userDetails.getEmail());
                userToUpdate.setPhone(userDetails.getPhone());
                userToUpdate.setAge(userDetails.getAge());
                userToUpdate.setActive(userDetails.isActive());
                userRepository.save(userToUpdate);
                return new ResponseEntity<>(userToUpdate, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Usuario no encontrado", HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            return new ResponseEntity<>("Error al actualizar el usuario", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>("Error inesperado al actualizar el usuario", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Método para eliminar un usuario con manejo de errores
    public ResponseEntity<Void> deleteUser(int id) {
        try {
            if (userRepository.existsById(id)) {
                userRepository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Object[]>> findActiveUsersWithNoActiveVehicles () {
        try {
            List<Object[]> result = userRepository.findActiveUsersWithNoActiveVehicles();
            if (result == null || result.isEmpty()) {
                return new ResponseEntity<>(result, HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
