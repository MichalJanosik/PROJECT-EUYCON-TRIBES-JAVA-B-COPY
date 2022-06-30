package com.example.projecteucyonjavatribesb.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.security.Timestamp;

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

    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp startedAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp finishedAt;

    @ManyToOne
    @JoinColumn(name = "kingdom")
    private Kingdom kingdom;


}
