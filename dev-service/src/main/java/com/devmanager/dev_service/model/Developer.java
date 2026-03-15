package com.devmanager.dev_service.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "developers")
@Data // Esto de Lombok te ahorra escribir getters y setters
public class Developer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    private String name;
    private String language;
    private String experience;
}