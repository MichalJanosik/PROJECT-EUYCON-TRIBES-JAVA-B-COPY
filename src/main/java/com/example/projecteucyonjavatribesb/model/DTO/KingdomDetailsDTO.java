package com.example.projecteucyonjavatribesb.model.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class KingdomDetailsDTO {

    private KingdomDTO kingdom;
    private List<ResourcesDTO> resources;
    private List<BuildingDTO> buildings;
    private List<TroopDTO> troops;

}
