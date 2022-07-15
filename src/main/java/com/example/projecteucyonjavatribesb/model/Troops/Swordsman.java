package com.example.projecteucyonjavatribesb.model.Troops;

import com.example.projecteucyonjavatribesb.model.Enums.TroopType;
import com.example.projecteucyonjavatribesb.model.Troops.Troops;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Swordsman extends Troops {

    public Swordsman(TroopType troopType) {
        super(troopType, 1, 2, 2, 2, System.currentTimeMillis(), System.currentTimeMillis());
    }
}
