package com.example.projecteucyonjavatribesb.model.DTO;

import com.example.projecteucyonjavatribesb.model.Location;
import com.example.projecteucyonjavatribesb.model.Resources;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class KingdomDTO {
    private Long kingdomId;
    private String kingdomName;
    private String ruler;
    private Integer population;
    private LocationDTO location;
}
