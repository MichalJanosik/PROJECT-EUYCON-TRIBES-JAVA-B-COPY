package com.example.projecteucyonjavatribesb.service;

//this service is gonna be used in general for reading/updating resources values

import com.example.projecteucyonjavatribesb.model.DTO.KingdomDTO;
import com.example.projecteucyonjavatribesb.model.DTO.KingdomDetailsDTO;
import com.example.projecteucyonjavatribesb.model.DTO.ResourcesDTO;
import com.example.projecteucyonjavatribesb.model.Kingdom;
import com.example.projecteucyonjavatribesb.model.Resources;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class ResourcesServiceImpl implements ResourcesService {

    private final KingdomService kingdomService;

    @Override
    public Map<String, Object> getKingdomResources(Long id) {
//        Kingdom kingdom = kingdomService.findKingdomById(id);
//
//        return KingdomDetailsDTO.builder()
//                .kingdom(KingdomDTO.builder()
//                        .kingdomId(kingdom.getId())
//                        .kingdomName(kingdom.getPlayer().getKingdomName())
//                        .ruler(kingdom.getRuler())
//                        .population(kingdom.getPopulation())
//                        //should locationDTO be used over here?
//                        .location(kingdom.getLocation())
//                        .build())
//                .resources(kingdom.getResourcesList().stream()
//                                .map(ResourcesServiceImpl::convertToResourcesDTO)
//                                .toList())
//                .build();

        KingdomDTO kingdomDTO = kingdomService.getKingdomDTO(id);
        List<ResourcesDTO> resourcesDTOList =
                kingdomService.findKingdomById(id).getResourcesList().stream()
                        .map(ResourcesServiceImpl::convertToResourcesDTO)
                        .toList();

        SortedMap<String, Object> kingdomResources = new TreeMap<>();

        kingdomResources.put("kingdom", kingdomDTO);
        kingdomResources.put("resources", resourcesDTOList);

        return kingdomResources;
    }

        private static ResourcesDTO convertToResourcesDTO (Resources resources){
            return new ResourcesDTO(
                    resources.getType(),
                    resources.getAmount(),
                    resources.getGeneration(),
                    resources.getUpdatedAt()
            );
        }
}
