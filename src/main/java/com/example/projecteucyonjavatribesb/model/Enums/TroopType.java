package com.example.projecteucyonjavatribesb.model.Enums;

import lombok.Getter;

@Getter
public enum TroopType {

    SPEARMAN("spearman"),
    SWORDSMAN("swordsman"),
    KNIGHT("knight");

    private String name;

    TroopType(String name) {
        this.name = name;
    }
}
