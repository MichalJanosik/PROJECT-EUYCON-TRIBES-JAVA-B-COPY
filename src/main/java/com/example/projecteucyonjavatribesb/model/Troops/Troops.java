package com.example.projecteucyonjavatribesb.model.Troops;

import com.example.projecteucyonjavatribesb.model.Enums.TroopType;
import com.example.projecteucyonjavatribesb.model.Kingdom;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Troops {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TroopType troopType;

    private Integer level;
    private Integer hp;
    private Integer attack;
    private Integer defense;
    private Long startedAt;
    private Long finishedAt;

    @ManyToOne
    @JoinColumn(name = "kingdom_id")
    private Kingdom kingdom;


    public Troops(TroopType troopType) {
        this.troopType = troopType;
    }


    public Troops(TroopType troopType, Integer level, Integer hp, Integer attack, Integer defense, Long startedAt, Long finishedAt) {
        this.troopType = troopType;
        this.level = level;
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.startedAt = startedAt;
        this.finishedAt = finishedAt;
    }
}
