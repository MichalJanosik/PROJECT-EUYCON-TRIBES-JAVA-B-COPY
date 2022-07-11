package com.example.projecteucyonjavatribesb.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
public class KingdomBuildingsDTO {
    private KingdomDTO kingdom;
    private List<BuildingDTO> buildings;


}
