package com.api.LIS.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.LIS.Models.Type;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataAccessException;

import java.util.List;

@Service
public class TypeService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public TypeService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public ResponseEntity<Object> createType(Type type) {
        try {
            entityManager.createNativeQuery("INSERT INTO Type (description) VALUES (?)")
                         .setParameter(1, type.getDescription())
                         .executeUpdate();
            return new ResponseEntity<>(type, HttpStatus.CREATED);
        } catch (DataAccessException e) {
            return new ResponseEntity<>("Error creating type: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>("Unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> getAllTypes() {
        try {
            @SuppressWarnings("unchecked")
            List<Type> types = entityManager.createNativeQuery("SELECT * FROM Type", Type.class)
                                            .getResultList();
            if(types.isEmpty()) {
                return new ResponseEntity<>(types, HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(types, HttpStatus.OK);
        } catch (DataAccessException e) {
            return new ResponseEntity<>("Error fetching types: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>("Unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> getTypeById(int id) {
        try {
            List<Type> result = entityManager.createNativeQuery("SELECT * FROM Type WHERE id = ?", Type.class)
                                            .setParameter(1, id)
                                            .getResultList();
            Type type = result.isEmpty() ? null : result.get(0);
            if (type != null) {
                return new ResponseEntity<>(type, HttpStatus.OK);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Type not found");
            }
        } catch (DataAccessException e) {
            return new ResponseEntity<>("Error fetching type: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>("Unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    public ResponseEntity<Object> updateType(int id, Type typeDetails) {
        try {
            int rowsUpdated = entityManager.createNativeQuery("UPDATE Type SET description = ? WHERE id = ?")
                                           .setParameter(1, typeDetails.getDescription())
                                           .setParameter(2, id)
                                           .executeUpdate();
            if (rowsUpdated > 0) {
                return new ResponseEntity<>(typeDetails, HttpStatus.OK);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Type not found");
            }
        } catch (DataAccessException e) {
            return new ResponseEntity<>("Error updating type: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>("Unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    public ResponseEntity<Object> deleteType(int id) {
        try {
            int rowsDeleted = entityManager.createNativeQuery("DELETE FROM Type WHERE id = ?")
                                           .setParameter(1, id)
                                           .executeUpdate();
            if (rowsDeleted > 0) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Type not found");
            }
        } catch (DataAccessException e) {
            return new ResponseEntity<>("Error deleting type: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>("Unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}