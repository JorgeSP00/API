package com.api.LIS.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.LIS.Models.Bike;

@Repository
public interface IBikeRepository extends JpaRepository<Bike, Integer> {

    @Query ("select b from Bike b where b.id = ?1")
    Bike getBikeById(@Param("1") int id);
}
