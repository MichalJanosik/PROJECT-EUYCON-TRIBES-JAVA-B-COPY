package com.example.projecteucyonjavatribesb.service;

import com.example.projecteucyonjavatribesb.model.DTO.KingdomDetailsDTO;

import java.util.Map;

public interface ResourcesService {
    Map<String, Object> getKingdomResources(Long id);
}
