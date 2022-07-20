package com.example.projecteucyonjavatribesb.model.Troops;

import com.example.projecteucyonjavatribesb.model.Enums.TroopType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class Knight extends Troops{

    public Knight() {
        super(TroopType.KNIGHT.getName(), 1, 8, 4, 3, System.currentTimeMillis(), System.currentTimeMillis());
    }
}