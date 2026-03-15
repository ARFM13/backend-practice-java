package com.devmanager.dev_service.controller;

import com.devmanager.dev_service.model.Developer;
import com.devmanager.dev_service.repository.DeveloperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.devmanager.dev_service.model.Team;


import java.util.List;

@RestController
@RequestMapping("/api/developers")
public class DeveloperController {

    @Autowired
    private DeveloperRepository repository;

    @GetMapping
    public List<Developer> getAll() {
        return repository.findAll();
    }
    @PostMapping
    public Developer create(@RequestBody Developer dev) {
        return repository.save(dev);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @PutMapping("/{id}")
    public Developer update(@PathVariable Long id, @RequestBody Developer developerDetails) {
        Developer developer = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Desarrollador no encontrado"));

        // Actualizamos el equipo (y otros datos si quieres)
        developer.setTeam(developerDetails.getTeam());

        return repository.save(developer);
    }

}