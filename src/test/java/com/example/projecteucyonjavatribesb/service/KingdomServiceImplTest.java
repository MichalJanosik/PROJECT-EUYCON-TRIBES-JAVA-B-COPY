package com.example.projecteucyonjavatribesb.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.projecteucyonjavatribesb.model.Buildings;
import com.example.projecteucyonjavatribesb.model.DTO.KingdomDTO;
import com.example.projecteucyonjavatribesb.model.DTO.KingdomDetailsDTO;
import com.example.projecteucyonjavatribesb.model.DTO.LocationDTO;
import com.example.projecteucyonjavatribesb.model.Kingdom;
import com.example.projecteucyonjavatribesb.model.Location;
import com.example.projecteucyonjavatribesb.model.Player;
import com.example.projecteucyonjavatribesb.repository.BuildingsRepository;
import com.example.projecteucyonjavatribesb.repository.KingdomRepository;
import com.example.projecteucyonjavatribesb.repository.ResourcesRepository;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {KingdomServiceImpl.class})
@ExtendWith(SpringExtension.class)
class KingdomServiceImplTest {
    @MockBean
    private BuildingsRepository buildingsRepository;

    @MockBean
    private KingdomRepository kingdomRepository;

    @Autowired
    private KingdomServiceImpl kingdomServiceImpl;

    @MockBean
    private ResourcesRepository resourcesRepository;

    Player player = new Player();
    Kingdom kingdom = new Kingdom();
    Location location = new Location();
    ArrayList<Buildings> buildingsList = new ArrayList<>();

    @BeforeEach
    void SetInitialValues() {
        player.setId(1L);
        player.setKingdom(kingdom);
        player.setKingdomName("Moria");
        player.setPassword("password");
        player.setUsername("Michael");

        location.setCoordinateX(1);
        location.setCoordinateY(1);
        location.setId(1L);
        location.setKingdom(kingdom);

        kingdom.setBuildingList(buildingsList);
        kingdom.setId(1L);
        kingdom.setLocation(location);
        kingdom.setPlayer(player);
        kingdom.setPopulation(1);
        kingdom.setResourcesList(new ArrayList<>());
        kingdom.setRuler("Michael");
    }

    @Test
    void testGetKingdomDetailsDTOById() {
        when(kingdomRepository.getKingdomById((Long) any())).thenReturn(kingdom);
        when(buildingsRepository.findAllByKingdom_Id((Long) any())).thenReturn(new ArrayList<>());
        when(resourcesRepository.findAllByKingdom_Id((Long) any())).thenReturn(new ArrayList<>());
        KingdomDetailsDTO actualKingdomDetailsDTOById = kingdomServiceImpl.getKingdomDetailsDTOById(1L);
        assertEquals(buildingsList, actualKingdomDetailsDTOById.getBuildings());
        assertEquals(buildingsList, actualKingdomDetailsDTOById.getResources());
        KingdomDTO kingdom1 = actualKingdomDetailsDTOById.getKingdom();
        assertEquals(1L, kingdom1.getKingdomId().longValue());
        assertEquals("Michael", kingdom1.getRuler());
        assertEquals(1, kingdom1.getPopulation());
        assertEquals("Moria", kingdom1.getKingdomName());
        LocationDTO location1 = kingdom1.getLocation();
        assertEquals(1, location1.getCoordinateY().intValue());
        assertEquals(1, location1.getCoordinateX().intValue());
        verify(kingdomRepository).getKingdomById((Long) any());
        verify(buildingsRepository).findAllByKingdom_Id((Long) any());
        verify(resourcesRepository).findAllByKingdom_Id((Long) any());
    }

    /**
     * Method under test: {@link KingdomServiceImpl#getKingdomDTO(Long)}
     */
    @Test
    void test_GetKingdomDTO_OK() {
        Optional<Kingdom> ofResult = Optional.of(kingdom);
        when(kingdomRepository.findById((Long) any())).thenReturn(ofResult);

        KingdomDTO actualKingdomDTO = kingdomServiceImpl.getKingdomDTO(1L);

        assertEquals(1L, actualKingdomDTO.getKingdomId().longValue());
        assertEquals("Michael", actualKingdomDTO.getRuler());
        assertEquals(1, actualKingdomDTO.getPopulation());
        assertEquals("Moria", actualKingdomDTO.getKingdomName());

        LocationDTO location3 = actualKingdomDTO.getLocation();

        assertEquals(1, location3.getCoordinateY().intValue());
        assertEquals(1, location3.getCoordinateX().intValue());

        //what this is for exactly?
        verify(kingdomRepository).findById((Long) any());
    }

    @Test
    void test_GetKingdomDTO_NULL() {
        Optional<Kingdom> ofResult = Optional.of(kingdom);
        when(kingdomRepository.findById((Long) any())).thenReturn(ofResult);

        KingdomDTO actualKingdomDTO = kingdomServiceImpl.getKingdomDTO(null);

        assertEquals(1L, actualKingdomDTO.getKingdomId().longValue());
        assertEquals("Michael", actualKingdomDTO.getRuler());
        assertEquals(1, actualKingdomDTO.getPopulation());
        assertEquals("Moria", actualKingdomDTO.getKingdomName());

        LocationDTO location3 = actualKingdomDTO.getLocation();

        assertEquals(1, location3.getCoordinateY().intValue());
        assertEquals(1, location3.getCoordinateX().intValue());

        //what this is for exactly?
        verify(kingdomRepository).findById((Long) any());
    }
}

