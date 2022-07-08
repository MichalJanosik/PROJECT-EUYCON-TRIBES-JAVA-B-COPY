package com.example.projecteucyonjavatribesb.model.DTO;

import com.example.projecteucyonjavatribesb.model.Buildings;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class BuildingDTO {
    private Long id;
    private String type;
    private int level;
    private Long startedAt;
    private Long finishedAt;

    public BuildingDTO(Buildings buildings  ) {
        this.id = buildings.getId();
        this.type = buildings.getType();
        this.level = buildings.getLevel();
        this.startedAt = buildings.getStartedAt();
        this.finishedAt = buildings.getFinishedAt();
    }

    public BuildingDTO(long id, String type, int level) {
        this.id = id;
        this.type = type;
        this.level = level;
    }
}
