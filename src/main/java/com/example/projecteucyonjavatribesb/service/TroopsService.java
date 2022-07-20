package com.example.projecteucyonjavatribesb.service;

import com.example.projecteucyonjavatribesb.model.DTO.KingdomDetailsDTO;
import com.example.projecteucyonjavatribesb.model.Troops.Troops;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TroopsService {

    KingdomDetailsDTO getKingdomTroopsDetailsDTOById(Long id);

    List<Troops> getInitialTroops();
}