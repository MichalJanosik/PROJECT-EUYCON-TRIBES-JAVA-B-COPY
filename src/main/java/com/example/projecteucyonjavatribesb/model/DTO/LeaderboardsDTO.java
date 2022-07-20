package com.example.projecteucyonjavatribesb.model.DTO;

import com.example.projecteucyonjavatribesb.model.Buildings;
import com.example.projecteucyonjavatribesb.model.Kingdom;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
@AllArgsConstructor
public class LeaderboardsDTO {
    private String ruler;
    private String kingdom;
    private Integer buildings;
    private Integer points;

    public LeaderboardsDTO(Kingdom kingdom) {
        ruler = kingdom.getRuler();
        this.kingdom = kingdom.getPlayer().getKingdomName();
        buildings = Math.toIntExact(kingdom.getBuildingList().size());
        points = kingdom.getBuildingList().stream()
                .map(Buildings::getLevel)
                .reduce(0, Integer::sum);
    }
}
