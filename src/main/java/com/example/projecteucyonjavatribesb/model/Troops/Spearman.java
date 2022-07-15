package com.example.projecteucyonjavatribesb.model.Troops;

import com.example.projecteucyonjavatribesb.model.Enums.TroopType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Spearman extends Troops {

    public Spearman(TroopType troopType) {
        super(troopType, 1, 1, 1, 1, System.currentTimeMillis(), System.currentTimeMillis());
    }
}
