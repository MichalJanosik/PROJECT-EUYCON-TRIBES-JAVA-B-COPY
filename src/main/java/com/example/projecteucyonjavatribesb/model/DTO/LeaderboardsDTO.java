package com.example.projecteucyonjavatribesb.model.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
public class LeaderboardsDTO {
    private String ruler;
    private String kingdom;
    private Integer buildings;
    private Integer points;
}
