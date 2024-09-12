package com.api.LIS.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.LIS.Models.Car;
import com.api.LIS.Repositories.ICarRepository;

@Service
public class CarService {
    private final ICarRepository carRepository;

    @Autowired
    public CarService(ICarRepository carRepository) {
        this.carRepository = carRepository;
    }

    // Método para crear un nuevo coche con manejo de errores
    public ResponseEntity<Object> newCar(Car car) {
        try {
            carRepository.save(car);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataAccessException e) {
            // Error relacionado con la base de datos
            return new ResponseEntity<>("Error al guardar el coche", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            // Otros errores inesperados
            return new ResponseEntity<>("Error inesperado al crear el coche", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Método para obtener todos los coches con manejo de errores
    public ResponseEntity<List<Car>> getAllCars() {
        try {
            List<Car> cars = carRepository.findAll();
            if (cars.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT); // No hay coches disponibles
            }
            return new ResponseEntity<>(cars, HttpStatus.OK);
        } catch (DataAccessException e) {
            // Error relacionado con la base de datos
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            // Otros errores inesperados
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Método para obtener un coche por ID con manejo de errores
    public ResponseEntity<Car> getCarById(int id) {
        try {
            Optional<Car> car = carRepository.findById(id);
            if (car.isPresent()) {
                return new ResponseEntity<>(car.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND); // coche no encontrado
            }
        } catch (DataAccessException e) {
            // Error relacionado con la base de datos
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            // Otros errores inesperados
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Método para actualizar un coche con manejo de errores
    public ResponseEntity<Object> updateCar(int id, Car carDetails) {
        try {
            Optional<Car> car = carRepository.findById(id);
            if (car.isPresent()) {
                Car carToUpdate = car.get();
                carToUpdate.setActive(carDetails.isActive());
                carToUpdate.setCapacity(carDetails.getCapacity());
                carToUpdate.setColor(carDetails.getColor());
                carToUpdate.setElectrical(carDetails.getElectrical());
                carToUpdate.setPlate(carDetails.getPlate());
                carToUpdate.setUser(carDetails.getUser());
                carRepository.save(carToUpdate);
                return new ResponseEntity<>(carToUpdate, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("coche no encontrado", HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            // Error relacionado con la base de datos
            return new ResponseEntity<>("Error al actualizar el coche", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            // Otros errores inesperados
            return new ResponseEntity<>("Error inesperado al actualizar el coche", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Método para eliminar un coche con manejo de errores
    public ResponseEntity<Void> deleteCar(int id) {
        try {
            if (carRepository.existsById(id)) {
                carRepository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT); // coche eliminado con éxito
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND); // coche no encontrado
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