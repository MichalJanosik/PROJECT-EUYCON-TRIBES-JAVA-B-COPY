package com.example.projecteucyonjavatribesb.model.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class KingdomDetailsDTO {

    private KingdomDTO kingdom;
    private List<ResourcesDTO> resources;
    private List<BuildingDTO> buildings;
    private List<TroopDTO> troops;

}
