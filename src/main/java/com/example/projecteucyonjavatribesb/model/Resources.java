package com.example.projecteucyonjavatribesb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Resources {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private Integer amount;
    private Integer generation;
    private Long updatedAt;
    @ManyToOne
    @JoinColumn(name = "kingdom_id")
    private Kingdom kingdom;

    public Resources(String type, Integer amount, Integer generation) {
        this.type = type;
        this.amount = amount;
        this.generation = generation;
        updatedAt = System.currentTimeMillis();
    }
}
