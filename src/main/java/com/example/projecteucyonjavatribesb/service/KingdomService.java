package com.example.projecteucyonjavatribesb.service;

import com.example.projecteucyonjavatribesb.model.DTO.KingdomDTO;
import com.example.projecteucyonjavatribesb.model.DTO.KingdomNameDTO;
import com.example.projecteucyonjavatribesb.model.Kingdom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
public interface KingdomService {
    Kingdom findKingdomById(Long id);
    KingdomDTO getKingdomDTO(Long id);
    KingdomDTO renameKingdom(KingdomNameDTO kingdomNameDTO, Long id);
}
