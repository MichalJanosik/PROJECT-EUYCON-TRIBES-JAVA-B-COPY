package com.example.projecteucyonjavatribesb.repository;

import com.example.projecteucyonjavatribesb.model.Kingdom;
import com.example.projecteucyonjavatribesb.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KingdomRepository extends JpaRepository<Kingdom, Long> {
    Optional<Kingdom> findKingdomByRulerAndId(Long kingdomId, String playerUsername);

    Kingdom findKingdomByRuler(String playerUsername);
}
