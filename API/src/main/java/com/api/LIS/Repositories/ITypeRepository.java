package com.api.LIS.Repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.api.LIS.Models.Type;

public interface ITypeRepository extends JpaRepository<Type, Integer> {
}

