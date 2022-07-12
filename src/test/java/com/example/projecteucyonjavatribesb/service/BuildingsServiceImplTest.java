package com.example.projecteucyonjavatribesb.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.projecteucyonjavatribesb.model.Buildings;
import com.example.projecteucyonjavatribesb.model.DTO.BuildingDTO;
import com.example.projecteucyonjavatribesb.model.DTO.BuildingRequestDTO;
import com.example.projecteucyonjavatribesb.model.DTO.ErrorDTO;
import com.example.projecteucyonjavatribesb.model.Kingdom;
import com.example.projecteucyonjavatribesb.model.Location;
import com.example.projecteucyonjavatribesb.model.Player;
import com.example.projecteucyonjavatribesb.repository.BuildingsRepository;
import com.example.projecteucyonjavatribesb.repository.KingdomRepository;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {BuildingsServiceImpl.class})
@SpringBootTest
@ExtendWith(SpringExtension.class)
class BuildingsServiceImplTest {
    @MockBean
    private BuildingsRepository buildingsRepository;

    @Autowired
    private BuildingsServiceImpl buildingsServiceImpl;

    @MockBean
    private KingdomRepository kingdomRepository;

    @MockBean
    private ResourcesServiceImpl resourcesServiceImpl;
    Buildings buildings = new Buildings();
    Location location = new Location();
    Player player = new Player();
    Kingdom kingdom = new Kingdom();




    Optional<Kingdom> ofResult = Optional.of(kingdom);


    /**
     * Method under test: {@link BuildingsServiceImpl#setBuilding(String, Long)}
     */
    @BeforeEach
    public void setup(){
        location.setCoordinateX(1);
        location.setCoordinateY(1);
        location.setId(123L);
        location.setKingdom(new Kingdom());

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

        buildings.setId(123L);
        buildings.setKingdom(kingdom);
        buildings.setLevel(1);
        buildings.setType("Type");

    }
    @Test
    void testSetBuilding() {

        when(buildingsRepository.save((Buildings) any())).thenReturn(buildings);
        when(kingdomRepository.save((Kingdom) any())).thenReturn(kingdom);
        when(kingdomRepository.findById((Long) any())).thenReturn(ofResult);
        BuildingDTO actualSetBuildingResult = buildingsServiceImpl.setBuilding("Type", 123L);
        assertEquals(0L, actualSetBuildingResult.getId());
        assertEquals("Type", actualSetBuildingResult.getType());
        assertEquals(0, actualSetBuildingResult.getLevel());
        verify(buildingsRepository).save((Buildings) any());
        verify(kingdomRepository).save((Kingdom) any());
        verify(kingdomRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link BuildingsServiceImpl#addBuildingMethod(Long, BuildingRequestDTO)}
     */
    @Test
    void testAddBuildingMethod() {
        when(kingdomRepository.findById((Long) any())).thenReturn(ofResult);
        ResponseEntity<Object> actualAddBuildingMethodResult = buildingsServiceImpl.addBuildingMethod(123L,
                new BuildingRequestDTO("Type"));
        assertTrue(actualAddBuildingMethodResult.hasBody());
        assertEquals(400, actualAddBuildingMethodResult.getStatusCodeValue());
        assertTrue(actualAddBuildingMethodResult.getHeaders().isEmpty());
        assertEquals("There is no Townhall in kingdom", ((ErrorDTO) actualAddBuildingMethodResult.getBody()).getError());
        verify(kingdomRepository, atLeast(1)).findById((Long) any());
    }

    /**
     * Method under test: {@link BuildingsServiceImpl#addBuildingMethod(Long, BuildingRequestDTO)}
     */
    @Test
    void testAddBuildingMethod2() {
        when(kingdomRepository.findById((Long) any())).thenReturn(ofResult);
        ResponseEntity<Object> actualAddBuildingMethodResult = buildingsServiceImpl.addBuildingMethod(123L,
                new BuildingRequestDTO("Type"));
        assertTrue(actualAddBuildingMethodResult.hasBody());
        assertEquals(400, actualAddBuildingMethodResult.getStatusCodeValue());
        assertTrue(actualAddBuildingMethodResult.getHeaders().isEmpty());
        assertEquals("There is no Townhall in kingdom", ((ErrorDTO) actualAddBuildingMethodResult.getBody()).getError());
        verify(kingdomRepository, atLeast(1)).findById((Long) any());
    }

    /**
     * Method under test: {@link BuildingsServiceImpl#addBuildingMethod(Long, BuildingRequestDTO)}
     */
    @Test
    void testAddBuildingMethod3() {
        when(kingdomRepository.findById((Long) any())).thenReturn(ofResult);
        ResponseEntity<Object> actualAddBuildingMethodResult = buildingsServiceImpl.addBuildingMethod(123L,
                new BuildingRequestDTO("Type"));
        assertTrue(actualAddBuildingMethodResult.hasBody());
        assertEquals(400, actualAddBuildingMethodResult.getStatusCodeValue());
        assertTrue(actualAddBuildingMethodResult.getHeaders().isEmpty());
        assertEquals("error , too much buildings of this type",
                ((ErrorDTO) actualAddBuildingMethodResult.getBody()).getError());
        verify(kingdomRepository, atLeast(1)).findById((Long) any());
    }
}

