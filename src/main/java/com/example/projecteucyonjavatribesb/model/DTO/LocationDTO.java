package com.example.projecteucyonjavatribesb.model.DTO;

import com.example.projecteucyonjavatribesb.model.Kingdom;

public class LocationDTO {
    private int coordinateX;
    private int coordinateY;

    public LocationDTO(Kingdom kingdom) {
        this.coordinateX = kingdom.getLocation().getCoordinateX();
        this.coordinateY = kingdom.getLocation().getCoordinateY();
    }
}
