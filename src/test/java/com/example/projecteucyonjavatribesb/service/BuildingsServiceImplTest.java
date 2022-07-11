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

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {BuildingsServiceImpl.class})
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

    /**
     * Method under test: {@link BuildingsServiceImpl#setBuilding(String, Long)}
     */
    @Test
    void testSetBuilding() {
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

        Kingdom kingdom = new Kingdom();
        kingdom.setBuildingList(new ArrayList<>());
        kingdom.setId(123L);
        kingdom.setLocation(location);
        kingdom.setPlayer(player);
        kingdom.setPopulation(1);
        kingdom.setResourcesList(new ArrayList<>());
        kingdom.setRuler("Ruler");

        Location location1 = new Location();
        location1.setCoordinateX(1);
        location1.setCoordinateY(1);
        location1.setId(123L);
        location1.setKingdom(kingdom);

        Location location2 = new Location();
        location2.setCoordinateX(1);
        location2.setCoordinateY(1);
        location2.setId(123L);
        location2.setKingdom(new Kingdom());

        Player player1 = new Player();
        player1.setId(123L);
        player1.setKingdom(new Kingdom());
        player1.setKingdomName("Kingdom Name");
        player1.setPassword("iloveyou");
        player1.setUsername("janedoe");

        Kingdom kingdom1 = new Kingdom();
        kingdom1.setBuildingList(new ArrayList<>());
        kingdom1.setId(123L);
        kingdom1.setLocation(location2);
        kingdom1.setPlayer(player1);
        kingdom1.setPopulation(1);
        kingdom1.setResourcesList(new ArrayList<>());
        kingdom1.setRuler("Ruler");

        Player player2 = new Player();
        player2.setId(123L);
        player2.setKingdom(kingdom1);
        player2.setKingdomName("Kingdom Name");
        player2.setPassword("iloveyou");
        player2.setUsername("janedoe");

        Kingdom kingdom2 = new Kingdom();
        kingdom2.setBuildingList(new ArrayList<>());
        kingdom2.setId(123L);
        kingdom2.setLocation(location1);
        kingdom2.setPlayer(player2);
        kingdom2.setPopulation(1);
        kingdom2.setResourcesList(new ArrayList<>());
        kingdom2.setRuler("Ruler");

        Buildings buildings = new Buildings();
        buildings.setId(123L);
        buildings.setKingdom(kingdom2);
        buildings.setLevel(1);
        buildings.setType("Type");
        when(buildingsRepository.save((Buildings) any())).thenReturn(buildings);

        Location location3 = new Location();
        location3.setCoordinateX(1);
        location3.setCoordinateY(1);
        location3.setId(123L);
        location3.setKingdom(new Kingdom());

        Player player3 = new Player();
        player3.setId(123L);
        player3.setKingdom(new Kingdom());
        player3.setKingdomName("Kingdom Name");
        player3.setPassword("iloveyou");
        player3.setUsername("janedoe");

        Kingdom kingdom3 = new Kingdom();
        kingdom3.setBuildingList(new ArrayList<>());
        kingdom3.setId(123L);
        kingdom3.setLocation(location3);
        kingdom3.setPlayer(player3);
        kingdom3.setPopulation(1);
        kingdom3.setResourcesList(new ArrayList<>());
        kingdom3.setRuler("Ruler");

        Location location4 = new Location();
        location4.setCoordinateX(1);
        location4.setCoordinateY(1);
        location4.setId(123L);
        location4.setKingdom(kingdom3);

        Location location5 = new Location();
        location5.setCoordinateX(1);
        location5.setCoordinateY(1);
        location5.setId(123L);
        location5.setKingdom(new Kingdom());

        Player player4 = new Player();
        player4.setId(123L);
        player4.setKingdom(new Kingdom());
        player4.setKingdomName("Kingdom Name");
        player4.setPassword("iloveyou");
        player4.setUsername("janedoe");

        Kingdom kingdom4 = new Kingdom();
        kingdom4.setBuildingList(new ArrayList<>());
        kingdom4.setId(123L);
        kingdom4.setLocation(location5);
        kingdom4.setPlayer(player4);
        kingdom4.setPopulation(1);
        kingdom4.setResourcesList(new ArrayList<>());
        kingdom4.setRuler("Ruler");

        Player player5 = new Player();
        player5.setId(123L);
        player5.setKingdom(kingdom4);
        player5.setKingdomName("Kingdom Name");
        player5.setPassword("iloveyou");
        player5.setUsername("janedoe");

        Kingdom kingdom5 = new Kingdom();
        kingdom5.setBuildingList(new ArrayList<>());
        kingdom5.setId(123L);
        kingdom5.setLocation(location4);
        kingdom5.setPlayer(player5);
        kingdom5.setPopulation(1);
        kingdom5.setResourcesList(new ArrayList<>());
        kingdom5.setRuler("Ruler");
        Optional<Kingdom> ofResult = Optional.of(kingdom5);

        Kingdom kingdom6 = new Kingdom();
        kingdom6.setBuildingList(new ArrayList<>());
        kingdom6.setId(123L);
        kingdom6.setLocation(new Location());
        kingdom6.setPlayer(new Player());
        kingdom6.setPopulation(1);
        kingdom6.setResourcesList(new ArrayList<>());
        kingdom6.setRuler("Ruler");

        Location location6 = new Location();
        location6.setCoordinateX(1);
        location6.setCoordinateY(1);
        location6.setId(123L);
        location6.setKingdom(kingdom6);

        Kingdom kingdom7 = new Kingdom();
        kingdom7.setBuildingList(new ArrayList<>());
        kingdom7.setId(123L);
        kingdom7.setLocation(new Location());
        kingdom7.setPlayer(new Player());
        kingdom7.setPopulation(1);
        kingdom7.setResourcesList(new ArrayList<>());
        kingdom7.setRuler("Ruler");

        Player player6 = new Player();
        player6.setId(123L);
        player6.setKingdom(kingdom7);
        player6.setKingdomName("Kingdom Name");
        player6.setPassword("iloveyou");
        player6.setUsername("janedoe");

        Kingdom kingdom8 = new Kingdom();
        kingdom8.setBuildingList(new ArrayList<>());
        kingdom8.setId(123L);
        kingdom8.setLocation(location6);
        kingdom8.setPlayer(player6);
        kingdom8.setPopulation(1);
        kingdom8.setResourcesList(new ArrayList<>());
        kingdom8.setRuler("Ruler");

        Location location7 = new Location();
        location7.setCoordinateX(1);
        location7.setCoordinateY(1);
        location7.setId(123L);
        location7.setKingdom(kingdom8);

        Kingdom kingdom9 = new Kingdom();
        kingdom9.setBuildingList(new ArrayList<>());
        kingdom9.setId(123L);
        kingdom9.setLocation(new Location());
        kingdom9.setPlayer(new Player());
        kingdom9.setPopulation(1);
        kingdom9.setResourcesList(new ArrayList<>());
        kingdom9.setRuler("Ruler");

        Location location8 = new Location();
        location8.setCoordinateX(1);
        location8.setCoordinateY(1);
        location8.setId(123L);
        location8.setKingdom(kingdom9);

        Kingdom kingdom10 = new Kingdom();
        kingdom10.setBuildingList(new ArrayList<>());
        kingdom10.setId(123L);
        kingdom10.setLocation(new Location());
        kingdom10.setPlayer(new Player());
        kingdom10.setPopulation(1);
        kingdom10.setResourcesList(new ArrayList<>());
        kingdom10.setRuler("Ruler");

        Player player7 = new Player();
        player7.setId(123L);
        player7.setKingdom(kingdom10);
        player7.setKingdomName("Kingdom Name");
        player7.setPassword("iloveyou");
        player7.setUsername("janedoe");

        Kingdom kingdom11 = new Kingdom();
        kingdom11.setBuildingList(new ArrayList<>());
        kingdom11.setId(123L);
        kingdom11.setLocation(location8);
        kingdom11.setPlayer(player7);
        kingdom11.setPopulation(1);
        kingdom11.setResourcesList(new ArrayList<>());
        kingdom11.setRuler("Ruler");

        Player player8 = new Player();
        player8.setId(123L);
        player8.setKingdom(kingdom11);
        player8.setKingdomName("Kingdom Name");
        player8.setPassword("iloveyou");
        player8.setUsername("janedoe");

        Kingdom kingdom12 = new Kingdom();
        kingdom12.setBuildingList(new ArrayList<>());
        kingdom12.setId(123L);
        kingdom12.setLocation(location7);
        kingdom12.setPlayer(player8);
        kingdom12.setPopulation(1);
        kingdom12.setResourcesList(new ArrayList<>());
        kingdom12.setRuler("Ruler");
        when(kingdomRepository.save((Kingdom) any())).thenReturn(kingdom12);
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

        Kingdom kingdom = new Kingdom();
        kingdom.setBuildingList(new ArrayList<>());
        kingdom.setId(123L);
        kingdom.setLocation(location);
        kingdom.setPlayer(player);
        kingdom.setPopulation(1);
        kingdom.setResourcesList(new ArrayList<>());
        kingdom.setRuler("Ruler");

        Location location1 = new Location();
        location1.setCoordinateX(1);
        location1.setCoordinateY(1);
        location1.setId(123L);
        location1.setKingdom(kingdom);

        Location location2 = new Location();
        location2.setCoordinateX(1);
        location2.setCoordinateY(1);
        location2.setId(123L);
        location2.setKingdom(new Kingdom());

        Player player1 = new Player();
        player1.setId(123L);
        player1.setKingdom(new Kingdom());
        player1.setKingdomName("Kingdom Name");
        player1.setPassword("iloveyou");
        player1.setUsername("janedoe");

        Kingdom kingdom1 = new Kingdom();
        kingdom1.setBuildingList(new ArrayList<>());
        kingdom1.setId(123L);
        kingdom1.setLocation(location2);
        kingdom1.setPlayer(player1);
        kingdom1.setPopulation(1);
        kingdom1.setResourcesList(new ArrayList<>());
        kingdom1.setRuler("Ruler");

        Player player2 = new Player();
        player2.setId(123L);
        player2.setKingdom(kingdom1);
        player2.setKingdomName("Kingdom Name");
        player2.setPassword("iloveyou");
        player2.setUsername("janedoe");

        Kingdom kingdom2 = new Kingdom();
        kingdom2.setBuildingList(new ArrayList<>());
        kingdom2.setId(123L);
        kingdom2.setLocation(location1);
        kingdom2.setPlayer(player2);
        kingdom2.setPopulation(1);
        kingdom2.setResourcesList(new ArrayList<>());
        kingdom2.setRuler("Ruler");
        Optional<Kingdom> ofResult = Optional.of(kingdom2);
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
        Kingdom kingdom = new Kingdom();
        kingdom.setBuildingList(new ArrayList<>());
        kingdom.setId(123L);
        kingdom.setLocation(new Location());
        kingdom.setPlayer(new Player());
        kingdom.setPopulation(400);
        kingdom.setResourcesList(new ArrayList<>());
        kingdom.setRuler("There is no Townhall in kingdom");

        Location location = new Location();
        location.setCoordinateX(400);
        location.setCoordinateY(400);
        location.setId(123L);
        location.setKingdom(kingdom);

        Kingdom kingdom1 = new Kingdom();
        kingdom1.setBuildingList(new ArrayList<>());
        kingdom1.setId(123L);
        kingdom1.setLocation(new Location());
        kingdom1.setPlayer(new Player());
        kingdom1.setPopulation(400);
        kingdom1.setResourcesList(new ArrayList<>());
        kingdom1.setRuler("There is no Townhall in kingdom");

        Player player = new Player();
        player.setId(123L);
        player.setKingdom(kingdom1);
        player.setKingdomName("There is no Townhall in kingdom");
        player.setPassword("iloveyou");
        player.setUsername("janedoe");

        Kingdom kingdom2 = new Kingdom();
        kingdom2.setBuildingList(new ArrayList<>());
        kingdom2.setId(123L);
        kingdom2.setLocation(location);
        kingdom2.setPlayer(player);
        kingdom2.setPopulation(400);
        kingdom2.setResourcesList(new ArrayList<>());
        kingdom2.setRuler("There is no Townhall in kingdom");

        Buildings buildings = new Buildings();
        buildings.setId(123L);
        buildings.setKingdom(kingdom2);
        buildings.setLevel(400);
        buildings.setType("There is no Townhall in kingdom");

        ArrayList<Buildings> buildingsList = new ArrayList<>();
        buildingsList.add(buildings);

        Location location1 = new Location();
        location1.setCoordinateX(1);
        location1.setCoordinateY(1);
        location1.setId(123L);
        location1.setKingdom(new Kingdom());

        Player player1 = new Player();
        player1.setId(123L);
        player1.setKingdom(new Kingdom());
        player1.setKingdomName("Kingdom Name");
        player1.setPassword("iloveyou");
        player1.setUsername("janedoe");

        Kingdom kingdom3 = new Kingdom();
        kingdom3.setBuildingList(new ArrayList<>());
        kingdom3.setId(123L);
        kingdom3.setLocation(location1);
        kingdom3.setPlayer(player1);
        kingdom3.setPopulation(1);
        kingdom3.setResourcesList(new ArrayList<>());
        kingdom3.setRuler("Ruler");

        Location location2 = new Location();
        location2.setCoordinateX(1);
        location2.setCoordinateY(1);
        location2.setId(123L);
        location2.setKingdom(kingdom3);

        Location location3 = new Location();
        location3.setCoordinateX(1);
        location3.setCoordinateY(1);
        location3.setId(123L);
        location3.setKingdom(new Kingdom());

        Player player2 = new Player();
        player2.setId(123L);
        player2.setKingdom(new Kingdom());
        player2.setKingdomName("Kingdom Name");
        player2.setPassword("iloveyou");
        player2.setUsername("janedoe");

        Kingdom kingdom4 = new Kingdom();
        kingdom4.setBuildingList(new ArrayList<>());
        kingdom4.setId(123L);
        kingdom4.setLocation(location3);
        kingdom4.setPlayer(player2);
        kingdom4.setPopulation(1);
        kingdom4.setResourcesList(new ArrayList<>());
        kingdom4.setRuler("Ruler");

        Player player3 = new Player();
        player3.setId(123L);
        player3.setKingdom(kingdom4);
        player3.setKingdomName("Kingdom Name");
        player3.setPassword("iloveyou");
        player3.setUsername("janedoe");

        Kingdom kingdom5 = new Kingdom();
        kingdom5.setBuildingList(buildingsList);
        kingdom5.setId(123L);
        kingdom5.setLocation(location2);
        kingdom5.setPlayer(player3);
        kingdom5.setPopulation(1);
        kingdom5.setResourcesList(new ArrayList<>());
        kingdom5.setRuler("Ruler");
        Optional<Kingdom> ofResult = Optional.of(kingdom5);
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
        Kingdom kingdom = new Kingdom();
        kingdom.setBuildingList(new ArrayList<>());
        kingdom.setId(123L);
        kingdom.setLocation(new Location());
        kingdom.setPlayer(new Player());
        kingdom.setPopulation(400);
        kingdom.setResourcesList(new ArrayList<>());
        kingdom.setRuler("There is no Townhall in kingdom");

        Location location = new Location();
        location.setCoordinateX(400);
        location.setCoordinateY(400);
        location.setId(123L);
        location.setKingdom(kingdom);

        Kingdom kingdom1 = new Kingdom();
        kingdom1.setBuildingList(new ArrayList<>());
        kingdom1.setId(123L);
        kingdom1.setLocation(new Location());
        kingdom1.setPlayer(new Player());
        kingdom1.setPopulation(400);
        kingdom1.setResourcesList(new ArrayList<>());
        kingdom1.setRuler("There is no Townhall in kingdom");

        Player player = new Player();
        player.setId(123L);
        player.setKingdom(kingdom1);
        player.setKingdomName("There is no Townhall in kingdom");
        player.setPassword("iloveyou");
        player.setUsername("janedoe");

        Kingdom kingdom2 = new Kingdom();
        kingdom2.setBuildingList(new ArrayList<>());
        kingdom2.setId(123L);
        kingdom2.setLocation(location);
        kingdom2.setPlayer(player);
        kingdom2.setPopulation(400);
        kingdom2.setResourcesList(new ArrayList<>());
        kingdom2.setRuler("There is no Townhall in kingdom");

        Buildings buildings = new Buildings();
        buildings.setId(123L);
        buildings.setKingdom(kingdom2);
        buildings.setLevel(400);
        buildings.setType("There is no Townhall in kingdom");

        Kingdom kingdom3 = new Kingdom();
        kingdom3.setBuildingList(new ArrayList<>());
        kingdom3.setId(123L);
        kingdom3.setLocation(new Location());
        kingdom3.setPlayer(new Player());
        kingdom3.setPopulation(6);
        kingdom3.setResourcesList(new ArrayList<>());
        kingdom3.setRuler("townhall");

        Location location1 = new Location();
        location1.setCoordinateX(6);
        location1.setCoordinateY(6);
        location1.setId(123L);
        location1.setKingdom(kingdom3);

        Kingdom kingdom4 = new Kingdom();
        kingdom4.setBuildingList(new ArrayList<>());
        kingdom4.setId(123L);
        kingdom4.setLocation(new Location());
        kingdom4.setPlayer(new Player());
        kingdom4.setPopulation(6);
        kingdom4.setResourcesList(new ArrayList<>());
        kingdom4.setRuler("townhall");

        Player player1 = new Player();
        player1.setId(123L);
        player1.setKingdom(kingdom4);
        player1.setKingdomName("townhall");
        player1.setPassword("iloveyou");
        player1.setUsername("janedoe");

        Kingdom kingdom5 = new Kingdom();
        kingdom5.setBuildingList(new ArrayList<>());
        kingdom5.setId(123L);
        kingdom5.setLocation(location1);
        kingdom5.setPlayer(player1);
        kingdom5.setPopulation(6);
        kingdom5.setResourcesList(new ArrayList<>());
        kingdom5.setRuler("townhall");

        Buildings buildings1 = new Buildings();
        buildings1.setId(123L);
        buildings1.setKingdom(kingdom5);
        buildings1.setLevel(6);
        buildings1.setType("townhall");

        ArrayList<Buildings> buildingsList = new ArrayList<>();
        buildingsList.add(buildings1);
        buildingsList.add(buildings);

        Location location2 = new Location();
        location2.setCoordinateX(1);
        location2.setCoordinateY(1);
        location2.setId(123L);
        location2.setKingdom(new Kingdom());

        Player player2 = new Player();
        player2.setId(123L);
        player2.setKingdom(new Kingdom());
        player2.setKingdomName("Kingdom Name");
        player2.setPassword("iloveyou");
        player2.setUsername("janedoe");

        Kingdom kingdom6 = new Kingdom();
        kingdom6.setBuildingList(new ArrayList<>());
        kingdom6.setId(123L);
        kingdom6.setLocation(location2);
        kingdom6.setPlayer(player2);
        kingdom6.setPopulation(1);
        kingdom6.setResourcesList(new ArrayList<>());
        kingdom6.setRuler("Ruler");

        Location location3 = new Location();
        location3.setCoordinateX(1);
        location3.setCoordinateY(1);
        location3.setId(123L);
        location3.setKingdom(kingdom6);

        Location location4 = new Location();
        location4.setCoordinateX(1);
        location4.setCoordinateY(1);
        location4.setId(123L);
        location4.setKingdom(new Kingdom());

        Player player3 = new Player();
        player3.setId(123L);
        player3.setKingdom(new Kingdom());
        player3.setKingdomName("Kingdom Name");
        player3.setPassword("iloveyou");
        player3.setUsername("janedoe");

        Kingdom kingdom7 = new Kingdom();
        kingdom7.setBuildingList(new ArrayList<>());
        kingdom7.setId(123L);
        kingdom7.setLocation(location4);
        kingdom7.setPlayer(player3);
        kingdom7.setPopulation(1);
        kingdom7.setResourcesList(new ArrayList<>());
        kingdom7.setRuler("Ruler");

        Player player4 = new Player();
        player4.setId(123L);
        player4.setKingdom(kingdom7);
        player4.setKingdomName("Kingdom Name");
        player4.setPassword("iloveyou");
        player4.setUsername("janedoe");

        Kingdom kingdom8 = new Kingdom();
        kingdom8.setBuildingList(buildingsList);
        kingdom8.setId(123L);
        kingdom8.setLocation(location3);
        kingdom8.setPlayer(player4);
        kingdom8.setPopulation(1);
        kingdom8.setResourcesList(new ArrayList<>());
        kingdom8.setRuler("Ruler");
        Optional<Kingdom> ofResult = Optional.of(kingdom8);
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

