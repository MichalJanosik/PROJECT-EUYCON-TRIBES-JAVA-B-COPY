package com.example.projecteucyonjavatribesb.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import lombok.Builder;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class KingdomDTO {
    private long kingdomId;
    private String kingdomName;
    private String ruler;
    private int population;
    private LocationDTO location;

}
