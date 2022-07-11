package com.example.projecteucyonjavatribesb.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.projecteucyonjavatribesb.model.Buildings;
import com.example.projecteucyonjavatribesb.model.DTO.KingdomBuildingsDTO;
import com.example.projecteucyonjavatribesb.model.DTO.KingdomDTO;
import com.example.projecteucyonjavatribesb.model.DTO.LocationDTO;
import com.example.projecteucyonjavatribesb.model.Kingdom;
import com.example.projecteucyonjavatribesb.model.Location;
import com.example.projecteucyonjavatribesb.model.Player;
import com.example.projecteucyonjavatribesb.repository.KingdomRepository;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {BuildingsServiceImpl.class})
@ExtendWith(SpringExtension.class)
class BuildingsServiceImplTest {
    @Autowired
    private BuildingsServiceImpl buildingsServiceImpl;

    @MockBean
    private KingdomRepository kingdomRepository;

    /**
     * Method under test: {@link BuildingsServiceImpl#makeKingdomBuildingsDTO(Long)}
     */
    @Test
    void testMakeKingdomBuildingsDTO() {
        Kingdom kingdom = new Kingdom();
        kingdom.setBuildingList(new ArrayList<>());
        kingdom.setId(123L);
        kingdom.setLocation(new Location());
        kingdom.setPlayer(new Player());
        kingdom.setPopulation(1);
        kingdom.setResourcesList(new ArrayList<>());
        kingdom.setRuler("Ruler");

        Location location = new Location();
        location.setCoordinateX(1);
        location.setCoordinateY(1);
        location.setId(123L);
        location.setKingdom(kingdom);

        Kingdom kingdom1 = new Kingdom();
        kingdom1.setBuildingList(new ArrayList<>());
        kingdom1.setId(123L);
        kingdom1.setLocation(new Location());
        kingdom1.setPlayer(new Player());
        kingdom1.setPopulation(1);
        kingdom1.setResourcesList(new ArrayList<>());
        kingdom1.setRuler("Ruler");

        Player player = new Player();
        player.setId(123L);
        player.setKingdom(kingdom1);
        player.setKingdomName("Kingdom Name");
        player.setPassword("iloveyou");
        player.setUsername("janedoe");

        Kingdom kingdom2 = new Kingdom();
        kingdom2.setBuildingList(new ArrayList<>());
        kingdom2.setId(123L);
        kingdom2.setLocation(location);
        kingdom2.setPlayer(player);
        kingdom2.setPopulation(1);
        kingdom2.setResourcesList(new ArrayList<>());
        kingdom2.setRuler("Ruler");

        Location location1 = new Location();
        location1.setCoordinateX(1);
        location1.setCoordinateY(1);
        location1.setId(123L);
        location1.setKingdom(kingdom2);

        Kingdom kingdom3 = new Kingdom();
        kingdom3.setBuildingList(new ArrayList<>());
        kingdom3.setId(123L);
        kingdom3.setLocation(new Location());
        kingdom3.setPlayer(new Player());
        kingdom3.setPopulation(1);
        kingdom3.setResourcesList(new ArrayList<>());
        kingdom3.setRuler("Ruler");

        Location location2 = new Location();
        location2.setCoordinateX(1);
        location2.setCoordinateY(1);
        location2.setId(123L);
        location2.setKingdom(kingdom3);

        Kingdom kingdom4 = new Kingdom();
        kingdom4.setBuildingList(new ArrayList<>());
        kingdom4.setId(123L);
        kingdom4.setLocation(new Location());
        kingdom4.setPlayer(new Player());
        kingdom4.setPopulation(1);
        kingdom4.setResourcesList(new ArrayList<>());
        kingdom4.setRuler("Ruler");

        Player player1 = new Player();
        player1.setId(123L);
        player1.setKingdom(kingdom4);
        player1.setKingdomName("Kingdom Name");
        player1.setPassword("iloveyou");
        player1.setUsername("janedoe");

        Kingdom kingdom5 = new Kingdom();
        kingdom5.setBuildingList(new ArrayList<>());
        kingdom5.setId(123L);
        kingdom5.setLocation(location2);
        kingdom5.setPlayer(player1);
        kingdom5.setPopulation(1);
        kingdom5.setResourcesList(new ArrayList<>());
        kingdom5.setRuler("Ruler");

        Player player2 = new Player();
        player2.setId(123L);
        player2.setKingdom(kingdom5);
        player2.setKingdomName("Kingdom Name");
        player2.setPassword("iloveyou");
        player2.setUsername("janedoe");

        Kingdom kingdom6 = new Kingdom();
        ArrayList<Buildings> buildingsList = new ArrayList<>();
        kingdom6.setBuildingList(buildingsList);
        kingdom6.setId(123L);
        kingdom6.setLocation(location1);
        kingdom6.setPlayer(player2);
        kingdom6.setPopulation(1);
        kingdom6.setResourcesList(new ArrayList<>());
        kingdom6.setRuler("Ruler");

        when(kingdomRepository.getKingdomById((Long) any())).thenReturn(kingdom6);
        KingdomBuildingsDTO actualMakeKingdomBuildingsDTOResult = buildingsServiceImpl.makeKingdomBuildingsDTO(123L);
        assertEquals(buildingsList, actualMakeKingdomBuildingsDTOResult.getBuildings());
        KingdomDTO kingdom7 = actualMakeKingdomBuildingsDTOResult.getKingdom();
        assertEquals(123L, kingdom7.getKingdomId());
        assertEquals("Ruler", kingdom7.getRuler());
        assertEquals(1, kingdom7.getPopulation());
        assertSame(location1, kingdom7.getLocation());
        assertEquals("Kingdom Name", kingdom7.getKingdomName());
        verify(kingdomRepository).getKingdomById((Long) any());
    }

}

