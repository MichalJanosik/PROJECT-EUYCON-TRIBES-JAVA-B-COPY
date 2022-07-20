package com.example.projecteucyonjavatribesb.model.Troops;

import com.example.projecteucyonjavatribesb.model.Enums.TroopType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class Spearman extends Troops {

    public Spearman() {
        super(TroopType.SPEARMAN.getName(), 1, 2, 1, 3, System.currentTimeMillis(), System.currentTimeMillis());
    }
}