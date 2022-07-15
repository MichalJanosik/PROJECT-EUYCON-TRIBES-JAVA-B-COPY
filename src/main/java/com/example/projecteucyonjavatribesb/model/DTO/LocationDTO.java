package com.example.projecteucyonjavatribesb.model.DTO;

import com.example.projecteucyonjavatribesb.model.Kingdom;
import com.example.projecteucyonjavatribesb.model.Location;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
