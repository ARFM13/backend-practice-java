package com.devmanager.dev_service.repository;

import com.devmanager.dev_service.model.Developer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeveloperRepository extends JpaRepository<Developer, Long> {
    // No necesitas escribir nada aquí, JpaRepository ya tiene todo el CRUD
}