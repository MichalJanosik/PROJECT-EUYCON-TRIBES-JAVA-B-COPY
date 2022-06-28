package com.example.projecteucyonjavatribesb.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String kingdomName;
    @OneToOne(cascade = CascadeType.ALL)
    private Kingdom kingdom;

    public Player(String password, String username) {
        this.password = password;
        this.username = username;
    }

    public Player(String password, String username, String kingdomName) {
        this.password = password;
        this.username = username;
        this.kingdomName = kingdomName;
    }
}
