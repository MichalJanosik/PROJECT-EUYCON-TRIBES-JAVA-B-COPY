package com.example.projecteucyonjavatribesb.service;

import com.example.projecteucyonjavatribesb.model.Kingdom;

import java.util.Optional;


import com.example.projecteucyonjavatribesb.model.DTO.KingdomDTO;

public interface KingdomService {
        Kingdom findKingdomById(Long id);
        KingdomDTO getKingdomDTO(Long id);
        Optional<Kingdom> findById(Long id);
    }
