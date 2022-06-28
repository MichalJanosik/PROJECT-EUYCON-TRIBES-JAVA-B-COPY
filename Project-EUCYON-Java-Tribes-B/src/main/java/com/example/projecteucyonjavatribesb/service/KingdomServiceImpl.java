package com.example.projecteucyonjavatribesb.service;

import com.example.projecteucyonjavatribesb.model.DTO.KingdomOverviewDTO;
import com.example.projecteucyonjavatribesb.repository.KingdomRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class KingdomServiceImpl implements KingdomService{

    private final KingdomRepository kingdomRepository;

    @Override
    public KingdomOverviewDTO getKingdomOverviewById(Long id) {
        return null;
    }
}
