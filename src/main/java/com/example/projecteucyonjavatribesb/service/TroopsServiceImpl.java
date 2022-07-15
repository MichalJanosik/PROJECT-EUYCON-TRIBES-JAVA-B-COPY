package com.example.projecteucyonjavatribesb.service;

import com.example.projecteucyonjavatribesb.model.DTO.KingdomDTO;
import com.example.projecteucyonjavatribesb.model.DTO.KingdomDetailsDTO;
import com.example.projecteucyonjavatribesb.model.DTO.TroopDTO;
import com.example.projecteucyonjavatribesb.model.Kingdom;
import com.example.projecteucyonjavatribesb.model.Troops.Spearman;
import com.example.projecteucyonjavatribesb.model.Troops.Troops;
import com.example.projecteucyonjavatribesb.repository.KingdomRepository;
import com.example.projecteucyonjavatribesb.repository.TroopsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.projecteucyonjavatribesb.model.Enums.TroopType.SPEARMAN;

@Service
@RequiredArgsConstructor
public class TroopsServiceImpl implements TroopsService{

    private final KingdomRepository kingdomRepository;
    private final TroopsRepository troopsRepository;

    @Override
    public KingdomDetailsDTO getKingdomTroopsDetailsDTOById(Long id) {
        Kingdom kingdom = kingdomRepository.getKingdomById(id);
        return extractKingdomTroopsDetailsFromKingdom(kingdom);
    }

    private KingdomDetailsDTO extractKingdomTroopsDetailsFromKingdom(Kingdom kingdom) {
        return KingdomDetailsDTO
                .builder()
                .kingdom(new KingdomDTO(kingdom))
                .troops(troopsRepository.findAllByKingdom_Id(kingdom.getId())
                        .stream().map(TroopDTO::new)
                        .collect(Collectors.toList()))
                .build();
    }

    @Override
    public List<Troops> getInitialTroops() {
        return new ArrayList<>(List.of(
                new Spearman(SPEARMAN), new Spearman(SPEARMAN)
        ));
    }


}
