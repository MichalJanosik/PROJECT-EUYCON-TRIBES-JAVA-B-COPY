package com.example.projecteucyonjavatribesb.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Building {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private Long level;
    private Long startedAt;
    private Long finishedAt;
    @ManyToOne
    @JoinColumn(name = "kingdom")
    private Kingdom kingdom;

    public Building(String type, Long level, Long startedAt, Long finishedAt) {
        this.type = type;
        this.level = level;
        this.startedAt = startedAt;
        this.finishedAt = finishedAt;
    }
}
