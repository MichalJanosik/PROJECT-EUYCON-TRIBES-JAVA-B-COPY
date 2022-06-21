package com.example.projecteucyonjavatribesb.repository;

import com.example.projecteucyonjavatribesb.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    Player findByUsername(String username);
}
