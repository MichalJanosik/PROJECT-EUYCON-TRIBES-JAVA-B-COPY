package com.example.projecteucyonjavatribesb.model.DTO;

import com.example.projecteucyonjavatribesb.model.Building;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BuildingDTO {
    private Long id;
    private String type;
    private Long level;
    private Long startedAt;
    private Long finishedAt;

    public BuildingDTO(Building building) {
        this.id = building.getId();
        this.type = building.getType();
        this.level = building.getLevel();
        this.startedAt = building.getStartedAt();
        this.finishedAt = building.getFinishedAt();
    }
}
