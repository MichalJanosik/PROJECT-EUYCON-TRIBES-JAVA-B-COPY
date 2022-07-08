package com.example.projecteucyonjavatribesb.repository;

import com.example.projecteucyonjavatribesb.model.Kingdom;
import com.example.projecteucyonjavatribesb.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KingdomRepository extends JpaRepository<Kingdom, Long> {

//    Optional<Kingdom> findKingdomByRulerAndId(String ruler, Long id);
    Optional<Kingdom> findKingdomByRulerAndId(String playerUsername, Long kingdomId);

    Kingdom findKingdomByRuler(String playerUsername);

    @Override
    public Optional<Kingdom> findById(Long id);

    Kingdom getKingdomById(Long id);

}
