package com.api.LIS.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.api.LIS.Models.User;

import java.util.List;


public interface IUserRepository extends JpaRepository<User, Integer>{
    
    @Query(value = "(SELECT 'Car' as Vehicle, u.id, u.name, c.id as Vehicle_id, c.color " +
               "FROM Usuario u " +
               "JOIN car c ON c.user_id = u.id " +
               "WHERE u.active = true AND c.active = false) " +
               "UNION " +
               "(SELECT 'Bike' as Vehicle, u.id, u.name, b.id as Vehicle_id, b.color " +
               "FROM Usuario u " +
               "JOIN bike b ON b.user_id = u.id " +
               "WHERE u.active = true AND b.active = false)",
       nativeQuery = true) //Query para mostrar los vehiculos inactivos de los usuarios activos.
    List<Object[]> findActiveUsersWithNoActiveVehicles();

}
