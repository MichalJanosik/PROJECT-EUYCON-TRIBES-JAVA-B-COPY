package com.example.projecteucyonjavatribesb.model.DTO;

import com.example.projecteucyonjavatribesb.model.Kingdom;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class KingdomPreviewDTO {

    private String ruler;
    private Long id;
    private String kingdomName;

    public KingdomPreviewDTO(Kingdom kingdom) {
        this.ruler = kingdom.getRuler();
        this.id = kingdom.getId();
        this.kingdomName = kingdom.getPlayer().getKingdomName();
    }
}
