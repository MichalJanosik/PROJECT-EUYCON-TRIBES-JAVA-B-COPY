package com.example.projecteucyonjavatribesb.service;

import com.example.projecteucyonjavatribesb.model.DTO.KingdomDetailsDTO;
import com.example.projecteucyonjavatribesb.model.Resources;

import java.util.List;
import java.util.Map;

public interface ResourcesService {
    KingdomDetailsDTO getKingdomResources(Long id);
    List<Resources> getInitialResources();
}
