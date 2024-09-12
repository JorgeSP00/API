package com.api.LIS.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.LIS.Models.Bike;
import com.api.LIS.Repositories.IBikeRepository;

@Service
public class BikeService {
    private final IBikeRepository bikeRepository;

    @Autowired
    public BikeService(IBikeRepository bikeRepository) {
        this.bikeRepository = bikeRepository;
    }

    // Método para crear una nueva bicicleta
    public ResponseEntity<Object> newBike(Bike bike) {
        try {
            bikeRepository.save(bike);
            return new ResponseEntity<>(bike, HttpStatus.CREATED);
        } catch (DataAccessException e) {
            // Error relacionado con la base de datos
            return new ResponseEntity<>("Error al guardar la bicicleta", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            // Otros errores inesperados
            return new ResponseEntity<>("Error inesperado al crear la bicicleta", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Método para obtener todas las bicicletas
    public ResponseEntity<List<Bike>> getAllBikes() {
        try {
            List<Bike> bikes = bikeRepository.findAll();
            if (bikes.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT); // No hay bicicletas disponibles
            }
            return new ResponseEntity<>(bikes, HttpStatus.OK);
        } catch (DataAccessException e) {
            // Error relacionado con la base de datos
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            // Otros errores inesperados
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Método para obtener una bicicleta por ID usando JPQL
    public ResponseEntity<Bike> getBikeById(int id) {
        try {
            Bike bike = bikeRepository.getBikeById(id);
            if (bike == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Bicicleta no encontrada
            } else {
                return new ResponseEntity<>(bike, HttpStatus.OK);
            }
        } catch (DataAccessException e) {
            // Error relacionado con la base de datos
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            // Otros errores inesperados
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Método para actualizar una bicicleta
    public ResponseEntity<Object> updateBike(int id, Bike bikeDetails) {
        try {
            Optional<Bike> bike = bikeRepository.findById(id);
            if (bike.isPresent()) {
                Bike bikeToUpdate = bike.get();
                bikeToUpdate.setActive(bikeDetails.isActive());
                bikeToUpdate.setBasket(bikeDetails.getBasket());
                bikeToUpdate.setColor(bikeDetails.getColor());
                bikeToUpdate.setType(bikeDetails.getType());
                bikeToUpdate.setUser(bikeDetails.getUser());
                bikeRepository.save(bikeToUpdate); // Guardar los cambios
                return new ResponseEntity<>(bikeToUpdate, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Bicicleta no encontrada", HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            // Error relacionado con la base de datos
            return new ResponseEntity<>("Error al actualizar la bicicleta", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            // Otros errores inesperados
            return new ResponseEntity<>("Error inesperado al actualizar la bicicleta", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Método para eliminar una bicicleta
    public ResponseEntity<Void> deleteBike(int id) {
        try {
            if (bikeRepository.existsById(id)) {
                bikeRepository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Bicicleta eliminada con éxito
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Bicicleta no encontrada
            }
        } catch (DataAccessException e) {
            // Error relacionado con la base de datos
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            // Otros errores inesperados
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}