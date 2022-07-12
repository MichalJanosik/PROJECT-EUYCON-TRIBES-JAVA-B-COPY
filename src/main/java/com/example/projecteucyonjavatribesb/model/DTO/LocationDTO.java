package com.example.projecteucyonjavatribesb.model.DTO;

import com.example.projecteucyonjavatribesb.model.Kingdom;
import com.example.projecteucyonjavatribesb.model.Location;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class LocationDTO {
    private Integer coordinateX;
    private Integer coordinateY;

    public LocationDTO(Kingdom kingdom) {
        this.coordinateX = kingdom.getLocation().getCoordinateX();
        this.coordinateY = kingdom.getLocation().getCoordinateY();
    }

    public LocationDTO(Location location) {
        this.coordinateX = location.getCoordinateX();
        this.coordinateY = location.getCoordinateY();

    }
}
