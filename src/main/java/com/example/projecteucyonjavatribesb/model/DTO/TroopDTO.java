package com.example.projecteucyonjavatribesb.model.DTO;

import com.example.projecteucyonjavatribesb.model.Troops;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class TroopDTO {
    private Long id;
    private Integer level;
    private Integer hp;
    private Integer attack;
    private Integer defence;
    private Long startedAt;
    private Long finishedAt;

    public TroopDTO(Troops troop) {
        this.id = troop.getId();
        this.level = troop.getLevel();
        this.hp = troop.getHp();
        this.attack = troop.getAttack();
        this.defence = troop.getDefence();
        this.startedAt = troop.getStartedAt();
        this.finishedAt = troop.getFinishedAt();
    }
}
