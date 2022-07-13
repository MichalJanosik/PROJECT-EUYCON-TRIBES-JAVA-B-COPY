package com.example.projecteucyonjavatribesb.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.projecteucyonjavatribesb.model.DTO.BuildingRequestDTO;
import com.example.projecteucyonjavatribesb.model.DTO.ErrorDTO;
import com.example.projecteucyonjavatribesb.model.Kingdom;
import com.example.projecteucyonjavatribesb.model.Location;
import com.example.projecteucyonjavatribesb.model.Player;
import com.example.projecteucyonjavatribesb.repository.BuildingsRepository;
import com.example.projecteucyonjavatribesb.repository.KingdomRepository;
import com.example.projecteucyonjavatribesb.repository.ResourcesRepository;
import com.example.projecteucyonjavatribesb.service.BuildingsService;
import com.example.projecteucyonjavatribesb.service.BuildingsServiceImpl;
import com.example.projecteucyonjavatribesb.service.KingdomServiceImpl;
import com.example.projecteucyonjavatribesb.service.PlayerAuthorizationServiceImpl;
import com.example.projecteucyonjavatribesb.service.ResourcesServiceImpl;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

class KingdomControllerTest {
    Kingdom kingdom = new Kingdom();


    @BeforeEach
    void setUp(){

        Location location = new Location();
        location.setCoordinateX(1);
        location.setCoordinateY(1);
        location.setId(123L);
        location.setKingdom(new Kingdom());

        Player player = new Player();
        player.setId(123L);
        player.setKingdom(new Kingdom());
        player.setKingdomName("Kingdom Name");
        player.setPassword("iloveyou");
        player.setUsername("janedoe");

        kingdom.setBuildingList(new ArrayList<>());
        kingdom.setId(123L);
        kingdom.setLocation(location);
        kingdom.setPlayer(player);
        kingdom.setPopulation(1);
        kingdom.setResourcesList(new ArrayList<>());
        kingdom.setRuler("Ruler");
    }
    /**
     * Method under test: {@link KingdomController#addBuilding(Long, BuildingRequestDTO, String)}
     */
    @Test
    void testAddBuilding() {

        KingdomRepository kingdomRepository = mock(KingdomRepository.class);
        when(kingdomRepository.findById((Long) any())).thenReturn(Optional.of(kingdom));
        BuildingsRepository buildingsRepository = mock(BuildingsRepository.class);
        BuildingsServiceImpl buildingsServiceimp = new BuildingsServiceImpl(buildingsRepository, kingdomRepository,
                new ResourcesServiceImpl(mock(ResourcesRepository.class)));

        PlayerAuthorizationServiceImpl playerAuthorizationService = new PlayerAuthorizationServiceImpl(
                mock(KingdomRepository.class));
        KingdomServiceImpl kingdomService = new KingdomServiceImpl(mock(KingdomRepository.class));
        KingdomRepository kingdomRepository1 = mock(KingdomRepository.class);
        BuildingsRepository buildingsRepository1 = mock(BuildingsRepository.class);
        BuildingsService buildingsService = mock(BuildingsService.class);
        KingdomController kingdomController = new KingdomController(playerAuthorizationService, kingdomService,
                kingdomRepository1, buildingsRepository1, buildingsService,
                new ResourcesServiceImpl(mock(ResourcesRepository.class)), buildingsServiceimp);
        ResponseEntity<Object> actualAddBuildingResult = kingdomController.addBuilding(1L, new BuildingRequestDTO("Type"),
                "foo");
        assertTrue(actualAddBuildingResult.hasBody());
        assertEquals(400, actualAddBuildingResult.getStatusCodeValue());
        assertTrue(actualAddBuildingResult.getHeaders().isEmpty());
        assertEquals("There is no Townhall in kingdom", ((ErrorDTO) actualAddBuildingResult.getBody()).getError());
        verify(kingdomRepository, atLeast(1)).findById((Long) any());
    }
}

