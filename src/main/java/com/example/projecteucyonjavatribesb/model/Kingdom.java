package com.example.projecteucyonjavatribesb.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Kingdom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @OneToOne
    private Location location;
    private String ruler;
    private Integer population;
    @OneToOne
    private Player player;
    @OneToMany(mappedBy = "kingdom")
    private List<Resources> resourcesList = new ArrayList<>();

    public Kingdom(Long id, String name, String ruler, Integer population, Location location) {
        this.id = id;
        this.ruler = ruler;
        this.population = population;
        this.location = location;
        this.name = name;
    }
}
