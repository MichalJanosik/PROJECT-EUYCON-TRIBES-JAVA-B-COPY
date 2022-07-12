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
    private Long startedAt;
    private Long finishedAt;


    @ManyToOne
    @JoinColumn(name = "kingdom")
    private Kingdom kingdom;

    public Buildings(String type, int level) {
        this.type = type;
        this.level = level;
        this.startedAt = System.currentTimeMillis();
        this.finishedAt = startedAt + 3600000; // = 1h
//        this.finishedAt = startedAt + 30000; // = 15s
    }
}
