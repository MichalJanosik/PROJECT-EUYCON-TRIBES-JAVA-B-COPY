package com.example.projecteucyonjavatribesb.model.DTO;

import com.example.projecteucyonjavatribesb.model.Buildings;
import com.example.projecteucyonjavatribesb.model.Location;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
public class KingdomBuildingsDTO {

    private long kingdomId;
    private String kingdomName;
    private String ruler;
    private int population;
    private Location location;
    private List<BuildingDTO> buildings;


}
