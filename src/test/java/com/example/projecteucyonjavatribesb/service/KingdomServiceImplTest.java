package com.example.projecteucyonjavatribesb.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.projecteucyonjavatribesb.model.DTO.KingdomDTO;
import com.example.projecteucyonjavatribesb.model.DTO.KingdomNameDTO;
import com.example.projecteucyonjavatribesb.model.Kingdom;
import com.example.projecteucyonjavatribesb.model.Location;
import com.example.projecteucyonjavatribesb.model.Player;
import com.example.projecteucyonjavatribesb.repository.KingdomRepository;

import java.util.ArrayList;
import java.util.Optional;

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
    private KingdomRepository kingdomRepository;

    @Autowired
    private KingdomServiceImpl kingdomServiceImpl;

    /**
     * Method under test: {@link KingdomServiceImpl#renameKingdom(Long, KingdomNameDTO)}
     */
    @Test
    void testRenameKingdom() {
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

        Kingdom kingdom3 = new Kingdom();
        kingdom3.setBuildingList(new ArrayList<>());
        kingdom3.setId(123L);
        kingdom3.setLocation(new Location());
        kingdom3.setPlayer(new Player());
        kingdom3.setPopulation(1);
        kingdom3.setResourcesList(new ArrayList<>());
        kingdom3.setRuler("Ruler");

        Location location3 = new Location();
        location3.setCoordinateX(1);
        location3.setCoordinateY(1);
        location3.setId(123L);
        location3.setKingdom(kingdom3);

        Kingdom kingdom4 = new Kingdom();
        kingdom4.setBuildingList(new ArrayList<>());
        kingdom4.setId(123L);
        kingdom4.setLocation(new Location());
        kingdom4.setPlayer(new Player());
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
        kingdom5.setBuildingList(new ArrayList<>());
        kingdom5.setId(123L);
        kingdom5.setLocation(location3);
        kingdom5.setPlayer(player3);
        kingdom5.setPopulation(1);
        kingdom5.setResourcesList(new ArrayList<>());
        kingdom5.setRuler("Ruler");

        Location location4 = new Location();
        location4.setCoordinateX(1);
        location4.setCoordinateY(1);
        location4.setId(123L);
        location4.setKingdom(kingdom5);

        Kingdom kingdom6 = new Kingdom();
        kingdom6.setBuildingList(new ArrayList<>());
        kingdom6.setId(123L);
        kingdom6.setLocation(new Location());
        kingdom6.setPlayer(new Player());
        kingdom6.setPopulation(1);
        kingdom6.setResourcesList(new ArrayList<>());
        kingdom6.setRuler("Ruler");

        Location location5 = new Location();
        location5.setCoordinateX(1);
        location5.setCoordinateY(1);
        location5.setId(123L);
        location5.setKingdom(kingdom6);

        Kingdom kingdom7 = new Kingdom();
        kingdom7.setBuildingList(new ArrayList<>());
        kingdom7.setId(123L);
        kingdom7.setLocation(new Location());
        kingdom7.setPlayer(new Player());
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
        kingdom8.setBuildingList(new ArrayList<>());
        kingdom8.setId(123L);
        kingdom8.setLocation(location5);
        kingdom8.setPlayer(player4);
        kingdom8.setPopulation(1);
        kingdom8.setResourcesList(new ArrayList<>());
        kingdom8.setRuler("Ruler");

        Player player5 = new Player();
        player5.setId(123L);
        player5.setKingdom(kingdom8);
        player5.setKingdomName("Kingdom Name");
        player5.setPassword("iloveyou");
        player5.setUsername("janedoe");

        Kingdom kingdom9 = new Kingdom();
        kingdom9.setBuildingList(new ArrayList<>());
        kingdom9.setId(123L);
        kingdom9.setLocation(location4);
        kingdom9.setPlayer(player5);
        kingdom9.setPopulation(1);
        kingdom9.setResourcesList(new ArrayList<>());
        kingdom9.setRuler("Ruler");
        when(kingdomRepository.save((Kingdom) any())).thenReturn(kingdom9);
        when(kingdomRepository.findById((Long) any())).thenReturn(ofResult);

        KingdomNameDTO kingdomNameDTO = new KingdomNameDTO();
        kingdomNameDTO.setKingdomName("Kingdom Name");
        kingdomServiceImpl.renameKingdom(123L, kingdomNameDTO);
        verify(kingdomRepository).save((Kingdom) any());
        verify(kingdomRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link KingdomServiceImpl#getRenamedKingdomDTO(Long)}
     */
    @Test
    void testGetRenamedKingdomDTO() {
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
        KingdomDTO actualRenamedKingdomDTO = kingdomServiceImpl.getRenamedKingdomDTO(123L);
        assertEquals(123L, actualRenamedKingdomDTO.getKingdomId());
        assertNull(actualRenamedKingdomDTO.getRuler());
        assertEquals(0, actualRenamedKingdomDTO.getPopulation());
        assertNull(actualRenamedKingdomDTO.getLocation());
        assertEquals("Kingdom Name", actualRenamedKingdomDTO.getKingdomName());
        verify(kingdomRepository).findById((Long) any());
    }
}

