package com.example.projecteucyonjavatribesb.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Buildings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String type;
    private int level;

    private LocalDateTime startedAt;

    private LocalDateTime finishedAt;


    @ManyToOne
    @JoinColumn(name = "kingdom")
    private Kingdom kingdom;


    public Buildings(String type, int level) {
        this.type = type;
        this.level = level;
        this.startedAt = LocalDateTime.now();
        this.finishedAt = startedAt.plusHours(3L * level);
    }
}
