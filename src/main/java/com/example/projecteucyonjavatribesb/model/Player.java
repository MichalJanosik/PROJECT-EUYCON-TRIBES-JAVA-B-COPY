package com.example.projecteucyonjavatribesb.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String kingdomName;
    private String password;
    @OneToOne(mappedBy = "player", cascade = CascadeType.ALL)
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

