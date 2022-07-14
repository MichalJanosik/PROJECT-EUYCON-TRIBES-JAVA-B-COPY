package com.example.projecteucyonjavatribesb.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.security.Timestamp;
import java.util.Calendar;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Buildings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private int level;
    private Long startedAt;
    private Long finishedAt;

    @ManyToOne
    @JoinColumn(name = "kingdom_id")
    private Kingdom kingdom;

    public Buildings(String type, int level) {
        this.type = type;
        this.level = level;
    }

    public Buildings(String type, int level, Long startedAt, Long finishedAt) {
        this.type = type;
        this.level = level;
        this.startedAt = startedAt;
        this.finishedAt = finishedAt;
    }
}

