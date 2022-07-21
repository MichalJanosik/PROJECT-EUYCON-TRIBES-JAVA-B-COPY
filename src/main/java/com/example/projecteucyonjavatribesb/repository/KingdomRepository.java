package com.example.projecteucyonjavatribesb.repository;

import com.example.projecteucyonjavatribesb.model.Kingdom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KingdomRepository extends JpaRepository<Kingdom, Long> {

//    Optional<Kingdom> findKingdomByRulerAndId(String ruler, Long id);
    Optional<Kingdom> findKingdomByRulerAndId(String playerUsername, Long kingdomId);

    Kingdom findKingdomByRuler(String playerUsername);
    Kingdom getKingdomById(Long id);

    Kingdom findKingdomById(Long id);
    Optional<Kingdom> findById(Long id);


}
