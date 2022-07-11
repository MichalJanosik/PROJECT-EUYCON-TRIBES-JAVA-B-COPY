package com.example.projecteucyonjavatribesb.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer coordinateX;
    private Integer coordinateY;

    @OneToOne(cascade = {CascadeType.ALL})
    @JsonBackReference
    private Kingdom kingdom;

    public Location(Integer coordinateX, Integer coordinateY) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
    }
}

