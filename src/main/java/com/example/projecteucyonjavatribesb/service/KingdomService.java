package com.example.projecteucyonjavatribesb.service;

import com.example.projecteucyonjavatribesb.model.DTO.KingdomDTO;
import com.example.projecteucyonjavatribesb.model.DTO.KingdomDetailsDTO;
import com.example.projecteucyonjavatribesb.model.DTO.ListOfKingdomsDTO;
import com.example.projecteucyonjavatribesb.model.Kingdom;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public interface KingdomService {

    KingdomDetailsDTO getKingdomDetailsDTOById(Long id);

    Kingdom findKingdomById(Long id);

    KingdomDTO getKingdomDTO(Long id);
    Optional<Kingdom> findById(Long id);

    ListOfKingdomsDTO makeListOfKingdomsDTO();
    int countPopulationInKingdom(Kingdom kingdom);
}
