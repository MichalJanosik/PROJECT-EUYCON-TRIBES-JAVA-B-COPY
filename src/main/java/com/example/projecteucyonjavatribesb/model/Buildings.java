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
    private long id;
    private String type;
    private int level;

//    @Temporal(TemporalType.TIMESTAMP)
//    private Calendar startedAt;
//
//    @Temporal(TemporalType.TIMESTAMP)
//    private Calendar finishedAt;

    @ManyToOne
    @JoinColumn(name = "kingdom")
    private Kingdom kingdom;


    public Buildings(String type, int level) {
        this.type = type;
        this.level = level;
    }
}
