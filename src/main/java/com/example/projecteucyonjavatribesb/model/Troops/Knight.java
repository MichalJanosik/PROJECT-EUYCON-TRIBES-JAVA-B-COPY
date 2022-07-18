package com.example.projecteucyonjavatribesb.model.Troops;

import com.example.projecteucyonjavatribesb.model.Enums.TroopType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Knight extends Troops{

    public Knight(TroopType troopType) {
        super(troopType, 1, 8, 3, 3, System.currentTimeMillis(), System.currentTimeMillis());
    }
}
