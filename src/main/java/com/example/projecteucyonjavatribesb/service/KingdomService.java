package com.example.projecteucyonjavatribesb.service;

import com.example.projecteucyonjavatribesb.model.DTO.KingdomDTO;
import com.example.projecteucyonjavatribesb.model.DTO.KingdomDetailsDTO;
import com.example.projecteucyonjavatribesb.model.Kingdom;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface KingdomService {

    public KingdomDetailsDTO getKingdomDetailsDTOById(Long id);

    Kingdom findKingdomById(Long id);

    KingdomDTO getKingdomDTO(Long id);

}
