package com.example.projecteucyonjavatribesb.service;

import com.example.projecteucyonjavatribesb.model.DTO.KingdomDTO;
import com.example.projecteucyonjavatribesb.model.DTO.KingdomNameDTO;
import com.example.projecteucyonjavatribesb.model.Kingdom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

public interface KingdomService {
    Kingdom findKingdomById(Long id);
    KingdomDTO getKingdomDTO(Long id);
    void renameKingdom(Long kingdomId, KingdomNameDTO kingdomNameDTO);
    KingdomDTO getRenamedKingdomDTO(Long kingdomId);
    List<Kingdom> findAllKingdoms();
}
