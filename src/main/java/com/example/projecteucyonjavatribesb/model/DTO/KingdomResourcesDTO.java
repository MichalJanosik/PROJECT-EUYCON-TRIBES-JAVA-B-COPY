package com.example.projecteucyonjavatribesb.model.DTO;

import com.example.projecteucyonjavatribesb.model.Location;

import java.util.List;

public class KingdomResourcesDTO {
    private long kingdomId;
    private String kingdomName;
    private String ruler;
    private int population;
    private Location location;
    private List<BuildingDTO> buildings;
}
