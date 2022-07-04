//<<<<<<< HEAD
package com.example.projecteucyonjavatribesb.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Kingdom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Location location;
    private String ruler;
    private Integer population;
    @Transient //This annotation means that this field will NOT be stored in DB
    private  HashMap<String,Integer> buildingsMap=new HashMap<>();
    @OneToOne
    private Player player;
    @OneToMany(mappedBy = "kingdom")
    private List<Resources> resourcesList = new ArrayList<>();
    @OneToMany(mappedBy = "kingdom")
    private List<Buildings> buildingsList = new ArrayList<>();
    public Kingdom(String ruler, Integer population, Location location) {
        this.ruler = ruler;
        this.population = population;
        this.location = location;
    }

    public Kingdom(String ruler) {
        this.ruler = ruler;
        this.population = 0;
    }

}
