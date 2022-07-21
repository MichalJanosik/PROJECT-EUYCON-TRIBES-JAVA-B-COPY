package com.example.projecteucyonjavatribesb.service;

import com.example.projecteucyonjavatribesb.model.DTO.KingdomDTO;
import com.example.projecteucyonjavatribesb.model.DTO.KingdomNameDTO;
import com.example.projecteucyonjavatribesb.model.DTO.KingdomDetailsDTO;
import com.example.projecteucyonjavatribesb.model.Kingdom;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public interface KingdomService {

    KingdomDetailsDTO getKingdomDetailsDTOById(Long id);

    Kingdom findKingdomById(Long id);

    KingdomDTO getKingdomDTO(Long id);
    Optional<Kingdom> findById(Long id);

    void renameKingdom(Long kingdomId, KingdomNameDTO kingdomNameDTO);
    KingdomDTO getRenamedKingdomDTO(Long kingdomId);
    List<Kingdom> findAllKingdoms();
}