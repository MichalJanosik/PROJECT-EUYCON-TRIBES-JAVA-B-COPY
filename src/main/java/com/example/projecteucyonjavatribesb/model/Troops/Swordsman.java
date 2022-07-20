package com.example.projecteucyonjavatribesb.model.Troops;

import com.example.projecteucyonjavatribesb.model.Enums.TroopType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Swordsman extends Troops {

    public Swordsman() {
        super(TroopType.SWORDSMAN.getName(), 1, 5, 3, 2, System.currentTimeMillis(), System.currentTimeMillis());
    }
}