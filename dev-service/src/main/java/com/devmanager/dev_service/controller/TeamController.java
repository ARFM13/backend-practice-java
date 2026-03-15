package com.devmanager.dev_service.controller;

import com.devmanager.dev_service.model.Team;
import com.devmanager.dev_service.repository.TeamRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teams")
public class TeamController {

    private final TeamRepository repository;

    public TeamController(TeamRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Team> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public Team create(@RequestBody Team team) {
        return repository.save(team);
    }
}