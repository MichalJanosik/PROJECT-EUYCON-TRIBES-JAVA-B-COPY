package com.example.projecteucyonjavatribesb.repository;

import com.example.projecteucyonjavatribesb.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    List<Player> findAllByUsername(String username);
    Player findByUsername(String username);
    Optional<Player> findPlayerByUsername(String username);
}
