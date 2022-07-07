package com.example.projecteucyonjavatribesb.model.DTO;

import com.example.projecteucyonjavatribesb.model.Location;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class KingdomDTO {
    private long kingdomId;
    private String kingdomName;
    private String ruler;
    private int population;
    private Location location;
}
