package com.example.projecteucyonjavatribesb.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.projecteucyonjavatribesb.model.Buildings;
import com.example.projecteucyonjavatribesb.model.DTO.KingdomDTO;
import com.example.projecteucyonjavatribesb.model.DTO.KingdomDetailsDTO;
import com.example.projecteucyonjavatribesb.model.DTO.LocationDTO;
import com.example.projecteucyonjavatribesb.model.Kingdom;
import com.example.projecteucyonjavatribesb.model.Location;
import com.example.projecteucyonjavatribesb.model.Player;
import com.example.projecteucyonjavatribesb.model.Resources;
import com.example.projecteucyonjavatribesb.repository.ResourcesRepository;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ResourcesServiceImpl.class})
@ExtendWith(SpringExtension.class)
class ResourcesServiceImplTest {
    @MockBean
    private ResourcesRepository resourcesRepository;

    @Autowired
    private ResourcesServiceImpl resourcesServiceImpl;

    /**
     * Method under test: {@link ResourcesServiceImpl#getKingdomResources(Long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetKingdomResources() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.util.NoSuchElementException
        //       at java.util.ArrayList$Itr.next(ArrayList.java:970)
        //       at com.example.projecteucyonjavatribesb.service.ResourcesServiceImpl.getKingdomResources(ResourcesServiceImpl.java:34)
        //   In order to prevent getKingdomResources(Long)
        //   from throwing NoSuchElementException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   getKingdomResources(Long).
        //   See https://diff.blue/R013 to resolve this issue.

        when(resourcesRepository.findAllByKingdom_Id((Long) any())).thenReturn(new ArrayList<>());
        resourcesServiceImpl.getKingdomResources(123L);
    }

    /**
     * Method under test: {@link ResourcesServiceImpl#getKingdomResources(Long)}
     */
    @Test
    void testGetKingdomResources2() {
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

        Resources resources = new Resources();
        resources.setAmount(10);
        resources.setGeneration(1);
        resources.setId(123L);
        resources.setKingdom(kingdom2);
        resources.setType("Type");
        resources.setUpdatedAt(1L);

        ArrayList<Resources> resourcesList = new ArrayList<>();
        resourcesList.add(resources);
        when(resourcesRepository.findAllByKingdom_Id((Long) any())).thenReturn(resourcesList);
        KingdomDetailsDTO actualKingdomResources = resourcesServiceImpl.getKingdomResources(123L);
        assertNull(actualKingdomResources.getBuildings());
        assertNull(actualKingdomResources.getTroops());
        assertTrue(actualKingdomResources.getResources().isEmpty());
        KingdomDTO kingdom3 = actualKingdomResources.getKingdom();
        assertEquals(123L, kingdom3.getKingdomId());
        assertEquals("Ruler", kingdom3.getRuler());
        assertEquals(1, kingdom3.getPopulation());
        assertEquals("Kingdom Name", kingdom3.getKingdomName());
        LocationDTO location1 = kingdom3.getLocation();
        assertEquals(1, location1.getCoordinateY().intValue());
        assertEquals(1, location1.getCoordinateX().intValue());
        verify(resourcesRepository).findAllByKingdom_Id((Long) any());
    }

    /**
     * Method under test: {@link ResourcesServiceImpl#getKingdomResources(Long)}
     */
    @Test
    void testGetKingdomResources3() {
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

        Kingdom kingdom3 = new Kingdom();
        kingdom3.setBuildingList(new ArrayList<>());
        kingdom3.setId(123L);
        kingdom3.setLocation(new Location());
        kingdom3.setPlayer(new Player());
        kingdom3.setPopulation(1);
        kingdom3.setResourcesList(new ArrayList<>());
        kingdom3.setRuler("Ruler");

        Location location1 = new Location();
        location1.setCoordinateX(1);
        location1.setCoordinateY(1);
        location1.setId(123L);
        location1.setKingdom(kingdom3);

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
        kingdom5.setLocation(location1);
        kingdom5.setPlayer(player1);
        kingdom5.setPopulation(1);
        kingdom5.setResourcesList(new ArrayList<>());
        kingdom5.setRuler("Ruler");

        Location location2 = new Location();
        location2.setCoordinateX(1);
        location2.setCoordinateY(1);
        location2.setId(123L);
        location2.setKingdom(kingdom5);

        Kingdom kingdom6 = new Kingdom();
        kingdom6.setBuildingList(new ArrayList<>());
        kingdom6.setId(123L);
        kingdom6.setLocation(new Location());
        kingdom6.setPlayer(new Player());
        kingdom6.setPopulation(1);
        kingdom6.setResourcesList(new ArrayList<>());
        kingdom6.setRuler("Ruler");

        Location location3 = new Location();
        location3.setCoordinateX(1);
        location3.setCoordinateY(1);
        location3.setId(123L);
        location3.setKingdom(kingdom6);

        Kingdom kingdom7 = new Kingdom();
        kingdom7.setBuildingList(new ArrayList<>());
        kingdom7.setId(123L);
        kingdom7.setLocation(new Location());
        kingdom7.setPlayer(new Player());
        kingdom7.setPopulation(1);
        kingdom7.setResourcesList(new ArrayList<>());
        kingdom7.setRuler("Ruler");

        Player player2 = new Player();
        player2.setId(123L);
        player2.setKingdom(kingdom7);
        player2.setKingdomName("Kingdom Name");
        player2.setPassword("iloveyou");
        player2.setUsername("janedoe");

        Kingdom kingdom8 = new Kingdom();
        kingdom8.setBuildingList(new ArrayList<>());
        kingdom8.setId(123L);
        kingdom8.setLocation(location3);
        kingdom8.setPlayer(player2);
        kingdom8.setPopulation(1);
        kingdom8.setResourcesList(new ArrayList<>());
        kingdom8.setRuler("Ruler");

        Player player3 = new Player();
        player3.setId(123L);
        player3.setKingdom(kingdom8);
        player3.setKingdomName("Kingdom Name");
        player3.setPassword("iloveyou");
        player3.setUsername("janedoe");

        Kingdom kingdom9 = new Kingdom();
        kingdom9.setBuildingList(new ArrayList<>());
        kingdom9.setId(123L);
        kingdom9.setLocation(location2);
        kingdom9.setPlayer(player3);
        kingdom9.setPopulation(1);
        kingdom9.setResourcesList(new ArrayList<>());
        kingdom9.setRuler("Ruler");
        Resources resources = mock(Resources.class);
        when(resources.getKingdom()).thenReturn(kingdom9);
        doNothing().when(resources).setAmount((Integer) any());
        doNothing().when(resources).setGeneration((Integer) any());
        doNothing().when(resources).setId((Long) any());
        doNothing().when(resources).setKingdom((Kingdom) any());
        doNothing().when(resources).setType((String) any());
        doNothing().when(resources).setUpdatedAt((Long) any());
        resources.setAmount(10);
        resources.setGeneration(1);
        resources.setId(123L);
        resources.setKingdom(kingdom2);
        resources.setType("Type");
        resources.setUpdatedAt(1L);

        ArrayList<Resources> resourcesList = new ArrayList<>();
        resourcesList.add(resources);
        when(resourcesRepository.findAllByKingdom_Id((Long) any())).thenReturn(resourcesList);
        KingdomDetailsDTO actualKingdomResources = resourcesServiceImpl.getKingdomResources(123L);
        assertNull(actualKingdomResources.getBuildings());
        assertNull(actualKingdomResources.getTroops());
        assertTrue(actualKingdomResources.getResources().isEmpty());
        KingdomDTO kingdom10 = actualKingdomResources.getKingdom();
        assertEquals(123L, kingdom10.getKingdomId());
        assertEquals("Ruler", kingdom10.getRuler());
        assertEquals(1, kingdom10.getPopulation());
        assertEquals("Kingdom Name", kingdom10.getKingdomName());
        LocationDTO location4 = kingdom10.getLocation();
        assertEquals(1, location4.getCoordinateY().intValue());
        assertEquals(1, location4.getCoordinateX().intValue());
        verify(resourcesRepository).findAllByKingdom_Id((Long) any());
        verify(resources).getKingdom();
        verify(resources).setAmount((Integer) any());
        verify(resources).setGeneration((Integer) any());
        verify(resources).setId((Long) any());
        verify(resources).setKingdom((Kingdom) any());
        verify(resources).setType((String) any());
        verify(resources).setUpdatedAt((Long) any());
    }

    /**
     * Method under test: {@link ResourcesServiceImpl#getKingdomResources(Long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetKingdomResources4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.util.NoSuchElementException
        //       at java.util.ArrayList$Itr.next(ArrayList.java:970)
        //       at com.example.projecteucyonjavatribesb.service.ResourcesServiceImpl.getKingdomResources(ResourcesServiceImpl.java:34)
        //   In order to prevent getKingdomResources(Long)
        //   from throwing NoSuchElementException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   getKingdomResources(Long).
        //   See https://diff.blue/R013 to resolve this issue.

        when(resourcesRepository.findAllByKingdom_Id((Long) any())).thenReturn(new ArrayList<>());
        resourcesServiceImpl.getKingdomResources(123L);
    }

    /**
     * Method under test: {@link ResourcesServiceImpl#getKingdomResources(Long)}
     */
    @Test
    void testGetKingdomResources5() {
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

        Resources resources = new Resources();
        resources.setAmount(10);
        resources.setGeneration(1);
        resources.setId(123L);
        resources.setKingdom(kingdom2);
        resources.setType("Type");
        resources.setUpdatedAt(1L);

        ArrayList<Resources> resourcesList = new ArrayList<>();
        resourcesList.add(resources);
        when(resourcesRepository.findAllByKingdom_Id((Long) any())).thenReturn(resourcesList);
        KingdomDetailsDTO actualKingdomResources = resourcesServiceImpl.getKingdomResources(123L);
        assertNull(actualKingdomResources.getBuildings());
        assertNull(actualKingdomResources.getTroops());
        assertTrue(actualKingdomResources.getResources().isEmpty());
        KingdomDTO kingdom3 = actualKingdomResources.getKingdom();
        assertEquals(123L, kingdom3.getKingdomId());
        assertEquals("Ruler", kingdom3.getRuler());
        assertEquals(1, kingdom3.getPopulation());
        assertEquals("Kingdom Name", kingdom3.getKingdomName());
        LocationDTO location1 = kingdom3.getLocation();
        assertEquals(1, location1.getCoordinateY().intValue());
        assertEquals(1, location1.getCoordinateX().intValue());
        verify(resourcesRepository).findAllByKingdom_Id((Long) any());
    }

    /**
     * Method under test: {@link ResourcesServiceImpl#getKingdomResources(Long)}
     */
    @Test
    void testGetKingdomResources6() {
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

        Kingdom kingdom3 = new Kingdom();
        kingdom3.setBuildingList(new ArrayList<>());
        kingdom3.setId(123L);
        kingdom3.setLocation(new Location());
        kingdom3.setPlayer(new Player());
        kingdom3.setPopulation(1);
        kingdom3.setResourcesList(new ArrayList<>());
        kingdom3.setRuler("Ruler");

        Location location1 = new Location();
        location1.setCoordinateX(1);
        location1.setCoordinateY(1);
        location1.setId(123L);
        location1.setKingdom(kingdom3);

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
        kingdom5.setLocation(location1);
        kingdom5.setPlayer(player1);
        kingdom5.setPopulation(1);
        kingdom5.setResourcesList(new ArrayList<>());
        kingdom5.setRuler("Ruler");

        Location location2 = new Location();
        location2.setCoordinateX(1);
        location2.setCoordinateY(1);
        location2.setId(123L);
        location2.setKingdom(kingdom5);

        Kingdom kingdom6 = new Kingdom();
        kingdom6.setBuildingList(new ArrayList<>());
        kingdom6.setId(123L);
        kingdom6.setLocation(new Location());
        kingdom6.setPlayer(new Player());
        kingdom6.setPopulation(1);
        kingdom6.setResourcesList(new ArrayList<>());
        kingdom6.setRuler("Ruler");

        Location location3 = new Location();
        location3.setCoordinateX(1);
        location3.setCoordinateY(1);
        location3.setId(123L);
        location3.setKingdom(kingdom6);

        Kingdom kingdom7 = new Kingdom();
        kingdom7.setBuildingList(new ArrayList<>());
        kingdom7.setId(123L);
        kingdom7.setLocation(new Location());
        kingdom7.setPlayer(new Player());
        kingdom7.setPopulation(1);
        kingdom7.setResourcesList(new ArrayList<>());
        kingdom7.setRuler("Ruler");

        Player player2 = new Player();
        player2.setId(123L);
        player2.setKingdom(kingdom7);
        player2.setKingdomName("Kingdom Name");
        player2.setPassword("iloveyou");
        player2.setUsername("janedoe");

        Kingdom kingdom8 = new Kingdom();
        kingdom8.setBuildingList(new ArrayList<>());
        kingdom8.setId(123L);
        kingdom8.setLocation(location3);
        kingdom8.setPlayer(player2);
        kingdom8.setPopulation(1);
        kingdom8.setResourcesList(new ArrayList<>());
        kingdom8.setRuler("Ruler");

        Player player3 = new Player();
        player3.setId(123L);
        player3.setKingdom(kingdom8);
        player3.setKingdomName("Kingdom Name");
        player3.setPassword("iloveyou");
        player3.setUsername("janedoe");

        Kingdom kingdom9 = new Kingdom();
        kingdom9.setBuildingList(new ArrayList<>());
        kingdom9.setId(123L);
        kingdom9.setLocation(location2);
        kingdom9.setPlayer(player3);
        kingdom9.setPopulation(1);
        kingdom9.setResourcesList(new ArrayList<>());
        kingdom9.setRuler("Ruler");
        Resources resources = mock(Resources.class);
        when(resources.getKingdom()).thenReturn(kingdom9);
        doNothing().when(resources).setAmount((Integer) any());
        doNothing().when(resources).setGeneration((Integer) any());
        doNothing().when(resources).setId((Long) any());
        doNothing().when(resources).setKingdom((Kingdom) any());
        doNothing().when(resources).setType((String) any());
        doNothing().when(resources).setUpdatedAt((Long) any());
        resources.setAmount(10);
        resources.setGeneration(1);
        resources.setId(123L);
        resources.setKingdom(kingdom2);
        resources.setType("Type");
        resources.setUpdatedAt(1L);

        ArrayList<Resources> resourcesList = new ArrayList<>();
        resourcesList.add(resources);
        when(resourcesRepository.findAllByKingdom_Id((Long) any())).thenReturn(resourcesList);
        KingdomDetailsDTO actualKingdomResources = resourcesServiceImpl.getKingdomResources(123L);
        assertNull(actualKingdomResources.getBuildings());
        assertNull(actualKingdomResources.getTroops());
        assertTrue(actualKingdomResources.getResources().isEmpty());
        KingdomDTO kingdom10 = actualKingdomResources.getKingdom();
        assertEquals(123L, kingdom10.getKingdomId());
        assertEquals("Ruler", kingdom10.getRuler());
        assertEquals(1, kingdom10.getPopulation());
        assertEquals("Kingdom Name", kingdom10.getKingdomName());
        LocationDTO location4 = kingdom10.getLocation();
        assertEquals(1, location4.getCoordinateY().intValue());
        assertEquals(1, location4.getCoordinateX().intValue());
        verify(resourcesRepository).findAllByKingdom_Id((Long) any());
        verify(resources).getKingdom();
        verify(resources).setAmount((Integer) any());
        verify(resources).setGeneration((Integer) any());
        verify(resources).setId((Long) any());
        verify(resources).setKingdom((Kingdom) any());
        verify(resources).setType((String) any());
        verify(resources).setUpdatedAt((Long) any());
    }

    /**
     * Method under test: {@link ResourcesServiceImpl#getKingdomResources(Long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetKingdomResources7() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.util.NoSuchElementException
        //       at java.util.ArrayList$Itr.next(ArrayList.java:970)
        //       at com.example.projecteucyonjavatribesb.service.ResourcesServiceImpl.getKingdomResources(ResourcesServiceImpl.java:34)
        //   In order to prevent getKingdomResources(Long)
        //   from throwing NoSuchElementException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   getKingdomResources(Long).
        //   See https://diff.blue/R013 to resolve this issue.

        when(resourcesRepository.findAllByKingdom_Id((Long) any())).thenReturn(new ArrayList<>());
        resourcesServiceImpl.getKingdomResources(123L);
    }

    /**
     * Method under test: {@link ResourcesServiceImpl#getKingdomResources(Long)}
     */
    @Test
    void testGetKingdomResources8() {
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

        Resources resources = new Resources();
        resources.setAmount(10);
        resources.setGeneration(1);
        resources.setId(123L);
        resources.setKingdom(kingdom2);
        resources.setType("Type");
        resources.setUpdatedAt(1L);

        ArrayList<Resources> resourcesList = new ArrayList<>();
        resourcesList.add(resources);
        when(resourcesRepository.findAllByKingdom_Id((Long) any())).thenReturn(resourcesList);
        KingdomDetailsDTO actualKingdomResources = resourcesServiceImpl.getKingdomResources(123L);
        assertNull(actualKingdomResources.getBuildings());
        assertNull(actualKingdomResources.getTroops());
        assertTrue(actualKingdomResources.getResources().isEmpty());
        KingdomDTO kingdom3 = actualKingdomResources.getKingdom();
        assertEquals(123L, kingdom3.getKingdomId());
        assertEquals("Ruler", kingdom3.getRuler());
        assertEquals(1, kingdom3.getPopulation());
        assertEquals("Kingdom Name", kingdom3.getKingdomName());
        LocationDTO location1 = kingdom3.getLocation();
        assertEquals(1, location1.getCoordinateY().intValue());
        assertEquals(1, location1.getCoordinateX().intValue());
        verify(resourcesRepository).findAllByKingdom_Id((Long) any());
    }

    /**
     * Method under test: {@link ResourcesServiceImpl#getKingdomResources(Long)}
     */
    @Test
    void testGetKingdomResources9() {
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

        Kingdom kingdom3 = new Kingdom();
        kingdom3.setBuildingList(new ArrayList<>());
        kingdom3.setId(123L);
        kingdom3.setLocation(new Location());
        kingdom3.setPlayer(new Player());
        kingdom3.setPopulation(1);
        kingdom3.setResourcesList(new ArrayList<>());
        kingdom3.setRuler("Ruler");

        Location location1 = new Location();
        location1.setCoordinateX(1);
        location1.setCoordinateY(1);
        location1.setId(123L);
        location1.setKingdom(kingdom3);

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
        kingdom5.setLocation(location1);
        kingdom5.setPlayer(player1);
        kingdom5.setPopulation(1);
        kingdom5.setResourcesList(new ArrayList<>());
        kingdom5.setRuler("Ruler");

        Location location2 = new Location();
        location2.setCoordinateX(1);
        location2.setCoordinateY(1);
        location2.setId(123L);
        location2.setKingdom(kingdom5);

        Kingdom kingdom6 = new Kingdom();
        kingdom6.setBuildingList(new ArrayList<>());
        kingdom6.setId(123L);
        kingdom6.setLocation(new Location());
        kingdom6.setPlayer(new Player());
        kingdom6.setPopulation(1);
        kingdom6.setResourcesList(new ArrayList<>());
        kingdom6.setRuler("Ruler");

        Location location3 = new Location();
        location3.setCoordinateX(1);
        location3.setCoordinateY(1);
        location3.setId(123L);
        location3.setKingdom(kingdom6);

        Kingdom kingdom7 = new Kingdom();
        kingdom7.setBuildingList(new ArrayList<>());
        kingdom7.setId(123L);
        kingdom7.setLocation(new Location());
        kingdom7.setPlayer(new Player());
        kingdom7.setPopulation(1);
        kingdom7.setResourcesList(new ArrayList<>());
        kingdom7.setRuler("Ruler");

        Player player2 = new Player();
        player2.setId(123L);
        player2.setKingdom(kingdom7);
        player2.setKingdomName("Kingdom Name");
        player2.setPassword("iloveyou");
        player2.setUsername("janedoe");

        Kingdom kingdom8 = new Kingdom();
        kingdom8.setBuildingList(new ArrayList<>());
        kingdom8.setId(123L);
        kingdom8.setLocation(location3);
        kingdom8.setPlayer(player2);
        kingdom8.setPopulation(1);
        kingdom8.setResourcesList(new ArrayList<>());
        kingdom8.setRuler("Ruler");

        Player player3 = new Player();
        player3.setId(123L);
        player3.setKingdom(kingdom8);
        player3.setKingdomName("Kingdom Name");
        player3.setPassword("iloveyou");
        player3.setUsername("janedoe");

        Kingdom kingdom9 = new Kingdom();
        kingdom9.setBuildingList(new ArrayList<>());
        kingdom9.setId(123L);
        kingdom9.setLocation(location2);
        kingdom9.setPlayer(player3);
        kingdom9.setPopulation(1);
        kingdom9.setResourcesList(new ArrayList<>());
        kingdom9.setRuler("Ruler");
        Resources resources = mock(Resources.class);
        when(resources.getKingdom()).thenReturn(kingdom9);
        doNothing().when(resources).setAmount((Integer) any());
        doNothing().when(resources).setGeneration((Integer) any());
        doNothing().when(resources).setId((Long) any());
        doNothing().when(resources).setKingdom((Kingdom) any());
        doNothing().when(resources).setType((String) any());
        doNothing().when(resources).setUpdatedAt((Long) any());
        resources.setAmount(10);
        resources.setGeneration(1);
        resources.setId(123L);
        resources.setKingdom(kingdom2);
        resources.setType("Type");
        resources.setUpdatedAt(1L);

        ArrayList<Resources> resourcesList = new ArrayList<>();
        resourcesList.add(resources);
        when(resourcesRepository.findAllByKingdom_Id((Long) any())).thenReturn(resourcesList);
        KingdomDetailsDTO actualKingdomResources = resourcesServiceImpl.getKingdomResources(123L);
        assertNull(actualKingdomResources.getBuildings());
        assertNull(actualKingdomResources.getTroops());
        assertTrue(actualKingdomResources.getResources().isEmpty());
        KingdomDTO kingdom10 = actualKingdomResources.getKingdom();
        assertEquals(123L, kingdom10.getKingdomId());
        assertEquals("Ruler", kingdom10.getRuler());
        assertEquals(1, kingdom10.getPopulation());
        assertEquals("Kingdom Name", kingdom10.getKingdomName());
        LocationDTO location4 = kingdom10.getLocation();
        assertEquals(1, location4.getCoordinateY().intValue());
        assertEquals(1, location4.getCoordinateX().intValue());
        verify(resourcesRepository).findAllByKingdom_Id((Long) any());
        verify(resources).getKingdom();
        verify(resources).setAmount((Integer) any());
        verify(resources).setGeneration((Integer) any());
        verify(resources).setId((Long) any());
        verify(resources).setKingdom((Kingdom) any());
        verify(resources).setType((String) any());
        verify(resources).setUpdatedAt((Long) any());
    }

    /**
     * Method under test: {@link ResourcesServiceImpl#getInitialResources()}
     */
    @Test
    void testGetInitialResources() {
        assertEquals(1, resourcesServiceImpl.getInitialResources().size());
    }

    /**
     * Method under test: {@link ResourcesServiceImpl#generateResources(Long)}
     */
    @Test
    void testGenerateResources() {
        when(resourcesRepository.findAllByKingdom_Id((Long) any())).thenReturn(new ArrayList<>());
        when(resourcesRepository.saveAll((Iterable<Resources>) any())).thenReturn(new ArrayList<>());
        resourcesServiceImpl.generateResources(123L);
        verify(resourcesRepository).findAllByKingdom_Id((Long) any());
        verify(resourcesRepository).saveAll((Iterable<Resources>) any());
    }

    /**
     * Method under test: {@link ResourcesServiceImpl#generateResources(Long)}
     */
    @Test
    void testGenerateResources2() {
        Kingdom kingdom = new Kingdom();
        kingdom.setBuildingList(new ArrayList<>());
        kingdom.setId(123L);
        kingdom.setLocation(new Location());
        kingdom.setPlayer(new Player());
        kingdom.setPopulation(1000);
        kingdom.setResourcesList(new ArrayList<>());
        kingdom.setRuler("Ruler");

        Location location = new Location();
        location.setCoordinateX(1000);
        location.setCoordinateY(1000);
        location.setId(123L);
        location.setKingdom(kingdom);

        Kingdom kingdom1 = new Kingdom();
        kingdom1.setBuildingList(new ArrayList<>());
        kingdom1.setId(123L);
        kingdom1.setLocation(new Location());
        kingdom1.setPlayer(new Player());
        kingdom1.setPopulation(1000);
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
        kingdom2.setPopulation(1000);
        kingdom2.setResourcesList(new ArrayList<>());
        kingdom2.setRuler("Ruler");

        Resources resources = new Resources();
        resources.setAmount(10);
        resources.setGeneration(1000);
        resources.setId(123L);
        resources.setKingdom(kingdom2);
        resources.setType("Type");
        resources.setUpdatedAt(1000L);

        ArrayList<Resources> resourcesList = new ArrayList<>();
        resourcesList.add(resources);
        when(resourcesRepository.findAllByKingdom_Id((Long) any())).thenReturn(resourcesList);
        when(resourcesRepository.saveAll((Iterable<Resources>) any())).thenReturn(new ArrayList<>());
        resourcesServiceImpl.generateResources(123L);
        verify(resourcesRepository).findAllByKingdom_Id((Long) any());
        verify(resourcesRepository).saveAll((Iterable<Resources>) any());
    }

    /**
     * Method under test: {@link ResourcesServiceImpl#generateResources(Long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGenerateResources3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.ArithmeticException: integer overflow
        //       at java.lang.Math.toIntExact(Math.java:1135)
        //       at com.example.projecteucyonjavatribesb.service.ResourcesServiceImpl.generateResources(ResourcesServiceImpl.java:86)
        //   In order to prevent generateResources(Long)
        //   from throwing ArithmeticException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   generateResources(Long).
        //   See https://diff.blue/R013 to resolve this issue.

        Kingdom kingdom = new Kingdom();
        kingdom.setBuildingList(new ArrayList<>());
        kingdom.setId(123L);
        kingdom.setLocation(new Location());
        kingdom.setPlayer(new Player());
        kingdom.setPopulation(1000);
        kingdom.setResourcesList(new ArrayList<>());
        kingdom.setRuler("Ruler");

        Location location = new Location();
        location.setCoordinateX(1000);
        location.setCoordinateY(1000);
        location.setId(123L);
        location.setKingdom(kingdom);

        Kingdom kingdom1 = new Kingdom();
        kingdom1.setBuildingList(new ArrayList<>());
        kingdom1.setId(123L);
        kingdom1.setLocation(new Location());
        kingdom1.setPlayer(new Player());
        kingdom1.setPopulation(1000);
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
        kingdom2.setPopulation(1000);
        kingdom2.setResourcesList(new ArrayList<>());
        kingdom2.setRuler("Ruler");

        Resources resources = new Resources();
        resources.setAmount(10);
        resources.setGeneration(1000);
        resources.setId(123L);
        resources.setKingdom(kingdom2);
        resources.setType("Type");
        resources.setUpdatedAt(1000L);

        Kingdom kingdom3 = new Kingdom();
        kingdom3.setBuildingList(new ArrayList<>());
        kingdom3.setId(123L);
        kingdom3.setLocation(new Location());
        kingdom3.setPlayer(new Player());
        kingdom3.setPopulation(1000);
        kingdom3.setResourcesList(new ArrayList<>());
        kingdom3.setRuler("gold");

        Location location1 = new Location();
        location1.setCoordinateX(1000);
        location1.setCoordinateY(1000);
        location1.setId(123L);
        location1.setKingdom(kingdom3);

        Kingdom kingdom4 = new Kingdom();
        kingdom4.setBuildingList(new ArrayList<>());
        kingdom4.setId(123L);
        kingdom4.setLocation(new Location());
        kingdom4.setPlayer(new Player());
        kingdom4.setPopulation(1000);
        kingdom4.setResourcesList(new ArrayList<>());
        kingdom4.setRuler("gold");

        Player player1 = new Player();
        player1.setId(123L);
        player1.setKingdom(kingdom4);
        player1.setKingdomName("gold");
        player1.setPassword("iloveyou");
        player1.setUsername("janedoe");

        Kingdom kingdom5 = new Kingdom();
        kingdom5.setBuildingList(new ArrayList<>());
        kingdom5.setId(123L);
        kingdom5.setLocation(location1);
        kingdom5.setPlayer(player1);
        kingdom5.setPopulation(1000);
        kingdom5.setResourcesList(new ArrayList<>());
        kingdom5.setRuler("gold");

        Resources resources1 = new Resources();
        resources1.setAmount(10);
        resources1.setGeneration(1000);
        resources1.setId(123L);
        resources1.setKingdom(kingdom5);
        resources1.setType("gold");
        resources1.setUpdatedAt(1000L);

        ArrayList<Resources> resourcesList = new ArrayList<>();
        resourcesList.add(resources1);
        resourcesList.add(resources);
        when(resourcesRepository.findAllByKingdom_Id((Long) any())).thenReturn(resourcesList);
        when(resourcesRepository.saveAll((Iterable<Resources>) any())).thenReturn(new ArrayList<>());
        resourcesServiceImpl.generateResources(123L);
    }

    /**
     * Method under test: {@link ResourcesServiceImpl#generateResources(Long)}
     */
    @Test
    void testGenerateResources4() {
        Kingdom kingdom = new Kingdom();
        kingdom.setBuildingList(new ArrayList<>());
        kingdom.setId(123L);
        kingdom.setLocation(new Location());
        kingdom.setPlayer(new Player());
        kingdom.setPopulation(1000);
        kingdom.setResourcesList(new ArrayList<>());
        kingdom.setRuler("Ruler");

        Location location = new Location();
        location.setCoordinateX(1000);
        location.setCoordinateY(1000);
        location.setId(123L);
        location.setKingdom(kingdom);

        Kingdom kingdom1 = new Kingdom();
        kingdom1.setBuildingList(new ArrayList<>());
        kingdom1.setId(123L);
        kingdom1.setLocation(new Location());
        kingdom1.setPlayer(new Player());
        kingdom1.setPopulation(1000);
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
        kingdom2.setPopulation(1000);
        kingdom2.setResourcesList(new ArrayList<>());
        kingdom2.setRuler("Ruler");

        Kingdom kingdom3 = new Kingdom();
        kingdom3.setBuildingList(new ArrayList<>());
        kingdom3.setId(123L);
        kingdom3.setLocation(new Location());
        kingdom3.setPlayer(new Player());
        kingdom3.setPopulation(1);
        kingdom3.setResourcesList(new ArrayList<>());
        kingdom3.setRuler("Ruler");

        Location location1 = new Location();
        location1.setCoordinateX(1);
        location1.setCoordinateY(1);
        location1.setId(123L);
        location1.setKingdom(kingdom3);

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
        kingdom5.setLocation(location1);
        kingdom5.setPlayer(player1);
        kingdom5.setPopulation(1);
        kingdom5.setResourcesList(new ArrayList<>());
        kingdom5.setRuler("Ruler");

        Location location2 = new Location();
        location2.setCoordinateX(1);
        location2.setCoordinateY(1);
        location2.setId(123L);
        location2.setKingdom(kingdom5);

        Kingdom kingdom6 = new Kingdom();
        kingdom6.setBuildingList(new ArrayList<>());
        kingdom6.setId(123L);
        kingdom6.setLocation(new Location());
        kingdom6.setPlayer(new Player());
        kingdom6.setPopulation(1);
        kingdom6.setResourcesList(new ArrayList<>());
        kingdom6.setRuler("Ruler");

        Location location3 = new Location();
        location3.setCoordinateX(1);
        location3.setCoordinateY(1);
        location3.setId(123L);
        location3.setKingdom(kingdom6);

        Kingdom kingdom7 = new Kingdom();
        kingdom7.setBuildingList(new ArrayList<>());
        kingdom7.setId(123L);
        kingdom7.setLocation(new Location());
        kingdom7.setPlayer(new Player());
        kingdom7.setPopulation(1);
        kingdom7.setResourcesList(new ArrayList<>());
        kingdom7.setRuler("Ruler");

        Player player2 = new Player();
        player2.setId(123L);
        player2.setKingdom(kingdom7);
        player2.setKingdomName("Kingdom Name");
        player2.setPassword("iloveyou");
        player2.setUsername("janedoe");

        Kingdom kingdom8 = new Kingdom();
        kingdom8.setBuildingList(new ArrayList<>());
        kingdom8.setId(123L);
        kingdom8.setLocation(location3);
        kingdom8.setPlayer(player2);
        kingdom8.setPopulation(1);
        kingdom8.setResourcesList(new ArrayList<>());
        kingdom8.setRuler("Ruler");

        Player player3 = new Player();
        player3.setId(123L);
        player3.setKingdom(kingdom8);
        player3.setKingdomName("Kingdom Name");
        player3.setPassword("iloveyou");
        player3.setUsername("janedoe");

        Kingdom kingdom9 = new Kingdom();
        kingdom9.setBuildingList(new ArrayList<>());
        kingdom9.setId(123L);
        kingdom9.setLocation(location2);
        kingdom9.setPlayer(player3);
        kingdom9.setPopulation(1);
        kingdom9.setResourcesList(new ArrayList<>());
        kingdom9.setRuler("Ruler");
        Resources resources = mock(Resources.class);
        when(resources.getAmount()).thenReturn(10);
        when(resources.getUpdatedAt()).thenReturn(1L);
        when(resources.getType()).thenReturn("Type");
        when(resources.getKingdom()).thenReturn(kingdom9);
        when(resources.getGeneration()).thenReturn(1);
        doNothing().when(resources).setAmount((Integer) any());
        doNothing().when(resources).setGeneration((Integer) any());
        doNothing().when(resources).setId((Long) any());
        doNothing().when(resources).setKingdom((Kingdom) any());
        doNothing().when(resources).setType((String) any());
        doNothing().when(resources).setUpdatedAt((Long) any());
        resources.setAmount(10);
        resources.setGeneration(1000);
        resources.setId(123L);
        resources.setKingdom(kingdom2);
        resources.setType("Type");
        resources.setUpdatedAt(1000L);

        ArrayList<Resources> resourcesList = new ArrayList<>();
        resourcesList.add(resources);
        when(resourcesRepository.findAllByKingdom_Id((Long) any())).thenReturn(resourcesList);
        when(resourcesRepository.saveAll((Iterable<Resources>) any())).thenReturn(new ArrayList<>());
        resourcesServiceImpl.generateResources(123L);
        verify(resourcesRepository).findAllByKingdom_Id((Long) any());
        verify(resourcesRepository).saveAll((Iterable<Resources>) any());
        verify(resources).getKingdom();
        verify(resources).getGeneration();
        verify(resources).getUpdatedAt();
        verify(resources, atLeast(1)).getType();
        verify(resources).setAmount((Integer) any());
        verify(resources).setGeneration((Integer) any());
        verify(resources).setId((Long) any());
        verify(resources).setKingdom((Kingdom) any());
        verify(resources).setType((String) any());
        verify(resources).setUpdatedAt((Long) any());
    }

    /**
     * Method under test: {@link ResourcesServiceImpl#generateResources(Long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGenerateResources5() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.ArithmeticException: integer overflow
        //       at java.lang.Math.toIntExact(Math.java:1135)
        //       at com.example.projecteucyonjavatribesb.service.ResourcesServiceImpl.generateResources(ResourcesServiceImpl.java:86)
        //   In order to prevent generateResources(Long)
        //   from throwing ArithmeticException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   generateResources(Long).
        //   See https://diff.blue/R013 to resolve this issue.

        Kingdom kingdom = new Kingdom();
        kingdom.setBuildingList(new ArrayList<>());
        kingdom.setId(123L);
        kingdom.setLocation(new Location());
        kingdom.setPlayer(new Player());
        kingdom.setPopulation(1000);
        kingdom.setResourcesList(new ArrayList<>());
        kingdom.setRuler("Ruler");

        Location location = new Location();
        location.setCoordinateX(1000);
        location.setCoordinateY(1000);
        location.setId(123L);
        location.setKingdom(kingdom);

        Kingdom kingdom1 = new Kingdom();
        kingdom1.setBuildingList(new ArrayList<>());
        kingdom1.setId(123L);
        kingdom1.setLocation(new Location());
        kingdom1.setPlayer(new Player());
        kingdom1.setPopulation(1000);
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
        kingdom2.setPopulation(1000);
        kingdom2.setResourcesList(new ArrayList<>());
        kingdom2.setRuler("Ruler");

        Kingdom kingdom3 = new Kingdom();
        kingdom3.setBuildingList(new ArrayList<>());
        kingdom3.setId(123L);
        kingdom3.setLocation(new Location());
        kingdom3.setPlayer(new Player());
        kingdom3.setPopulation(1);
        kingdom3.setResourcesList(new ArrayList<>());
        kingdom3.setRuler("Ruler");

        Location location1 = new Location();
        location1.setCoordinateX(1);
        location1.setCoordinateY(1);
        location1.setId(123L);
        location1.setKingdom(kingdom3);

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
        kingdom5.setLocation(location1);
        kingdom5.setPlayer(player1);
        kingdom5.setPopulation(1);
        kingdom5.setResourcesList(new ArrayList<>());
        kingdom5.setRuler("Ruler");

        Location location2 = new Location();
        location2.setCoordinateX(1);
        location2.setCoordinateY(1);
        location2.setId(123L);
        location2.setKingdom(kingdom5);

        Kingdom kingdom6 = new Kingdom();
        kingdom6.setBuildingList(new ArrayList<>());
        kingdom6.setId(123L);
        kingdom6.setLocation(new Location());
        kingdom6.setPlayer(new Player());
        kingdom6.setPopulation(1);
        kingdom6.setResourcesList(new ArrayList<>());
        kingdom6.setRuler("Ruler");

        Location location3 = new Location();
        location3.setCoordinateX(1);
        location3.setCoordinateY(1);
        location3.setId(123L);
        location3.setKingdom(kingdom6);

        Kingdom kingdom7 = new Kingdom();
        kingdom7.setBuildingList(new ArrayList<>());
        kingdom7.setId(123L);
        kingdom7.setLocation(new Location());
        kingdom7.setPlayer(new Player());
        kingdom7.setPopulation(1);
        kingdom7.setResourcesList(new ArrayList<>());
        kingdom7.setRuler("Ruler");

        Player player2 = new Player();
        player2.setId(123L);
        player2.setKingdom(kingdom7);
        player2.setKingdomName("Kingdom Name");
        player2.setPassword("iloveyou");
        player2.setUsername("janedoe");

        Kingdom kingdom8 = new Kingdom();
        kingdom8.setBuildingList(new ArrayList<>());
        kingdom8.setId(123L);
        kingdom8.setLocation(location3);
        kingdom8.setPlayer(player2);
        kingdom8.setPopulation(1);
        kingdom8.setResourcesList(new ArrayList<>());
        kingdom8.setRuler("Ruler");

        Player player3 = new Player();
        player3.setId(123L);
        player3.setKingdom(kingdom8);
        player3.setKingdomName("Kingdom Name");
        player3.setPassword("iloveyou");
        player3.setUsername("janedoe");

        Kingdom kingdom9 = new Kingdom();
        kingdom9.setBuildingList(new ArrayList<>());
        kingdom9.setId(123L);
        kingdom9.setLocation(location2);
        kingdom9.setPlayer(player3);
        kingdom9.setPopulation(1);
        kingdom9.setResourcesList(new ArrayList<>());
        kingdom9.setRuler("Ruler");
        Resources resources = mock(Resources.class);
        when(resources.getAmount()).thenReturn(-1);
        when(resources.getUpdatedAt()).thenReturn(1L);
        when(resources.getType()).thenReturn("Type");
        when(resources.getKingdom()).thenReturn(kingdom9);
        when(resources.getGeneration()).thenReturn(1);
        doNothing().when(resources).setAmount((Integer) any());
        doNothing().when(resources).setGeneration((Integer) any());
        doNothing().when(resources).setId((Long) any());
        doNothing().when(resources).setKingdom((Kingdom) any());
        doNothing().when(resources).setType((String) any());
        doNothing().when(resources).setUpdatedAt((Long) any());
        resources.setAmount(10);
        resources.setGeneration(1000);
        resources.setId(123L);
        resources.setKingdom(kingdom2);
        resources.setType("Type");
        resources.setUpdatedAt(1000L);

        ArrayList<Resources> resourcesList = new ArrayList<>();
        resourcesList.add(resources);
        when(resourcesRepository.findAllByKingdom_Id((Long) any())).thenReturn(resourcesList);
        when(resourcesRepository.saveAll((Iterable<Resources>) any())).thenReturn(new ArrayList<>());
        resourcesServiceImpl.generateResources(123L);
    }

    /**
     * Method under test: {@link ResourcesServiceImpl#generateResources(Long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGenerateResources6() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.ArithmeticException: integer overflow
        //       at java.lang.Math.toIntExact(Math.java:1135)
        //       at com.example.projecteucyonjavatribesb.service.ResourcesServiceImpl.generateResources(ResourcesServiceImpl.java:86)
        //   In order to prevent generateResources(Long)
        //   from throwing ArithmeticException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   generateResources(Long).
        //   See https://diff.blue/R013 to resolve this issue.

        Kingdom kingdom = new Kingdom();
        kingdom.setBuildingList(new ArrayList<>());
        kingdom.setId(123L);
        kingdom.setLocation(new Location());
        kingdom.setPlayer(new Player());
        kingdom.setPopulation(1000);
        kingdom.setResourcesList(new ArrayList<>());
        kingdom.setRuler("Ruler");

        Location location = new Location();
        location.setCoordinateX(1000);
        location.setCoordinateY(1000);
        location.setId(123L);
        location.setKingdom(kingdom);

        Kingdom kingdom1 = new Kingdom();
        kingdom1.setBuildingList(new ArrayList<>());
        kingdom1.setId(123L);
        kingdom1.setLocation(new Location());
        kingdom1.setPlayer(new Player());
        kingdom1.setPopulation(1000);
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
        kingdom2.setPopulation(1000);
        kingdom2.setResourcesList(new ArrayList<>());
        kingdom2.setRuler("Ruler");

        Kingdom kingdom3 = new Kingdom();
        kingdom3.setBuildingList(new ArrayList<>());
        kingdom3.setId(123L);
        kingdom3.setLocation(new Location());
        kingdom3.setPlayer(new Player());
        kingdom3.setPopulation(1);
        kingdom3.setResourcesList(new ArrayList<>());
        kingdom3.setRuler("Ruler");

        Location location1 = new Location();
        location1.setCoordinateX(1);
        location1.setCoordinateY(1);
        location1.setId(123L);
        location1.setKingdom(kingdom3);

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
        kingdom5.setLocation(location1);
        kingdom5.setPlayer(player1);
        kingdom5.setPopulation(1);
        kingdom5.setResourcesList(new ArrayList<>());
        kingdom5.setRuler("Ruler");

        Location location2 = new Location();
        location2.setCoordinateX(1);
        location2.setCoordinateY(1);
        location2.setId(123L);
        location2.setKingdom(kingdom5);

        Kingdom kingdom6 = new Kingdom();
        kingdom6.setBuildingList(new ArrayList<>());
        kingdom6.setId(123L);
        kingdom6.setLocation(new Location());
        kingdom6.setPlayer(new Player());
        kingdom6.setPopulation(1);
        kingdom6.setResourcesList(new ArrayList<>());
        kingdom6.setRuler("Ruler");

        Location location3 = new Location();
        location3.setCoordinateX(1);
        location3.setCoordinateY(1);
        location3.setId(123L);
        location3.setKingdom(kingdom6);

        Kingdom kingdom7 = new Kingdom();
        kingdom7.setBuildingList(new ArrayList<>());
        kingdom7.setId(123L);
        kingdom7.setLocation(new Location());
        kingdom7.setPlayer(new Player());
        kingdom7.setPopulation(1);
        kingdom7.setResourcesList(new ArrayList<>());
        kingdom7.setRuler("Ruler");

        Player player2 = new Player();
        player2.setId(123L);
        player2.setKingdom(kingdom7);
        player2.setKingdomName("Kingdom Name");
        player2.setPassword("iloveyou");
        player2.setUsername("janedoe");

        Kingdom kingdom8 = new Kingdom();
        kingdom8.setBuildingList(new ArrayList<>());
        kingdom8.setId(123L);
        kingdom8.setLocation(location3);
        kingdom8.setPlayer(player2);
        kingdom8.setPopulation(1);
        kingdom8.setResourcesList(new ArrayList<>());
        kingdom8.setRuler("Ruler");

        Player player3 = new Player();
        player3.setId(123L);
        player3.setKingdom(kingdom8);
        player3.setKingdomName("Kingdom Name");
        player3.setPassword("iloveyou");
        player3.setUsername("janedoe");

        Kingdom kingdom9 = new Kingdom();
        kingdom9.setBuildingList(new ArrayList<>());
        kingdom9.setId(123L);
        kingdom9.setLocation(location2);
        kingdom9.setPlayer(player3);
        kingdom9.setPopulation(1);
        kingdom9.setResourcesList(new ArrayList<>());
        kingdom9.setRuler("Ruler");
        Resources resources = mock(Resources.class);
        when(resources.getAmount()).thenReturn(10);
        when(resources.getUpdatedAt()).thenReturn(1L);
        when(resources.getType()).thenReturn("food");
        when(resources.getKingdom()).thenReturn(kingdom9);
        when(resources.getGeneration()).thenReturn(1);
        doNothing().when(resources).setAmount((Integer) any());
        doNothing().when(resources).setGeneration((Integer) any());
        doNothing().when(resources).setId((Long) any());
        doNothing().when(resources).setKingdom((Kingdom) any());
        doNothing().when(resources).setType((String) any());
        doNothing().when(resources).setUpdatedAt((Long) any());
        resources.setAmount(10);
        resources.setGeneration(1000);
        resources.setId(123L);
        resources.setKingdom(kingdom2);
        resources.setType("Type");
        resources.setUpdatedAt(1000L);

        ArrayList<Resources> resourcesList = new ArrayList<>();
        resourcesList.add(resources);
        when(resourcesRepository.findAllByKingdom_Id((Long) any())).thenReturn(resourcesList);
        when(resourcesRepository.saveAll((Iterable<Resources>) any())).thenReturn(new ArrayList<>());
        resourcesServiceImpl.generateResources(123L);
    }

    /**
     * Method under test: {@link ResourcesServiceImpl#generateResources(Long)}
     */
    @Test
    void testGenerateResources7() {
        Kingdom kingdom = new Kingdom();
        kingdom.setBuildingList(new ArrayList<>());
        kingdom.setId(123L);
        kingdom.setLocation(new Location());
        kingdom.setPlayer(new Player());
        kingdom.setPopulation(1000);
        kingdom.setResourcesList(new ArrayList<>());
        kingdom.setRuler("Ruler");

        Location location = new Location();
        location.setCoordinateX(1000);
        location.setCoordinateY(1000);
        location.setId(123L);
        location.setKingdom(kingdom);

        Kingdom kingdom1 = new Kingdom();
        kingdom1.setBuildingList(new ArrayList<>());
        kingdom1.setId(123L);
        kingdom1.setLocation(new Location());
        kingdom1.setPlayer(new Player());
        kingdom1.setPopulation(1000);
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
        kingdom2.setPopulation(1000);
        kingdom2.setResourcesList(new ArrayList<>());
        kingdom2.setRuler("Ruler");

        Kingdom kingdom3 = new Kingdom();
        kingdom3.setBuildingList(new ArrayList<>());
        kingdom3.setId(123L);
        kingdom3.setLocation(new Location());
        kingdom3.setPlayer(new Player());
        kingdom3.setPopulation(1000);
        kingdom3.setResourcesList(new ArrayList<>());
        kingdom3.setRuler("gold");

        Location location1 = new Location();
        location1.setCoordinateX(1000);
        location1.setCoordinateY(1000);
        location1.setId(123L);
        location1.setKingdom(kingdom3);

        Kingdom kingdom4 = new Kingdom();
        kingdom4.setBuildingList(new ArrayList<>());
        kingdom4.setId(123L);
        kingdom4.setLocation(new Location());
        kingdom4.setPlayer(new Player());
        kingdom4.setPopulation(1000);
        kingdom4.setResourcesList(new ArrayList<>());
        kingdom4.setRuler("gold");

        Player player1 = new Player();
        player1.setId(123L);
        player1.setKingdom(kingdom4);
        player1.setKingdomName("gold");
        player1.setPassword("iloveyou");
        player1.setUsername("janedoe");

        Kingdom kingdom5 = new Kingdom();
        kingdom5.setBuildingList(new ArrayList<>());
        kingdom5.setId(123L);
        kingdom5.setLocation(location1);
        kingdom5.setPlayer(player1);
        kingdom5.setPopulation(1000);
        kingdom5.setResourcesList(new ArrayList<>());
        kingdom5.setRuler("gold");

        Buildings buildings = new Buildings();
        buildings.setId(123L);
        buildings.setKingdom(kingdom5);
        buildings.setLevel(1000);
        buildings.setType("gold");

        ArrayList<Buildings> buildingsList = new ArrayList<>();
        buildingsList.add(buildings);

        Kingdom kingdom6 = new Kingdom();
        kingdom6.setBuildingList(new ArrayList<>());
        kingdom6.setId(123L);
        kingdom6.setLocation(new Location());
        kingdom6.setPlayer(new Player());
        kingdom6.setPopulation(1);
        kingdom6.setResourcesList(new ArrayList<>());
        kingdom6.setRuler("Ruler");

        Location location2 = new Location();
        location2.setCoordinateX(1);
        location2.setCoordinateY(1);
        location2.setId(123L);
        location2.setKingdom(kingdom6);

        Kingdom kingdom7 = new Kingdom();
        kingdom7.setBuildingList(new ArrayList<>());
        kingdom7.setId(123L);
        kingdom7.setLocation(new Location());
        kingdom7.setPlayer(new Player());
        kingdom7.setPopulation(1);
        kingdom7.setResourcesList(new ArrayList<>());
        kingdom7.setRuler("Ruler");

        Player player2 = new Player();
        player2.setId(123L);
        player2.setKingdom(kingdom7);
        player2.setKingdomName("Kingdom Name");
        player2.setPassword("iloveyou");
        player2.setUsername("janedoe");

        Kingdom kingdom8 = new Kingdom();
        kingdom8.setBuildingList(new ArrayList<>());
        kingdom8.setId(123L);
        kingdom8.setLocation(location2);
        kingdom8.setPlayer(player2);
        kingdom8.setPopulation(1);
        kingdom8.setResourcesList(new ArrayList<>());
        kingdom8.setRuler("Ruler");

        Location location3 = new Location();
        location3.setCoordinateX(1);
        location3.setCoordinateY(1);
        location3.setId(123L);
        location3.setKingdom(kingdom8);

        Kingdom kingdom9 = new Kingdom();
        kingdom9.setBuildingList(new ArrayList<>());
        kingdom9.setId(123L);
        kingdom9.setLocation(new Location());
        kingdom9.setPlayer(new Player());
        kingdom9.setPopulation(1);
        kingdom9.setResourcesList(new ArrayList<>());
        kingdom9.setRuler("Ruler");

        Location location4 = new Location();
        location4.setCoordinateX(1);
        location4.setCoordinateY(1);
        location4.setId(123L);
        location4.setKingdom(kingdom9);

        Kingdom kingdom10 = new Kingdom();
        kingdom10.setBuildingList(new ArrayList<>());
        kingdom10.setId(123L);
        kingdom10.setLocation(new Location());
        kingdom10.setPlayer(new Player());
        kingdom10.setPopulation(1);
        kingdom10.setResourcesList(new ArrayList<>());
        kingdom10.setRuler("Ruler");

        Player player3 = new Player();
        player3.setId(123L);
        player3.setKingdom(kingdom10);
        player3.setKingdomName("Kingdom Name");
        player3.setPassword("iloveyou");
        player3.setUsername("janedoe");

        Kingdom kingdom11 = new Kingdom();
        kingdom11.setBuildingList(new ArrayList<>());
        kingdom11.setId(123L);
        kingdom11.setLocation(location4);
        kingdom11.setPlayer(player3);
        kingdom11.setPopulation(1);
        kingdom11.setResourcesList(new ArrayList<>());
        kingdom11.setRuler("Ruler");

        Player player4 = new Player();
        player4.setId(123L);
        player4.setKingdom(kingdom11);
        player4.setKingdomName("Kingdom Name");
        player4.setPassword("iloveyou");
        player4.setUsername("janedoe");

        Kingdom kingdom12 = new Kingdom();
        kingdom12.setBuildingList(buildingsList);
        kingdom12.setId(123L);
        kingdom12.setLocation(location3);
        kingdom12.setPlayer(player4);
        kingdom12.setPopulation(1);
        kingdom12.setResourcesList(new ArrayList<>());
        kingdom12.setRuler("Ruler");
        Resources resources = mock(Resources.class);
        when(resources.getAmount()).thenReturn(10);
        when(resources.getUpdatedAt()).thenReturn(1L);
        when(resources.getType()).thenReturn("Type");
        when(resources.getKingdom()).thenReturn(kingdom12);
        when(resources.getGeneration()).thenReturn(1);
        doNothing().when(resources).setAmount((Integer) any());
        doNothing().when(resources).setGeneration((Integer) any());
        doNothing().when(resources).setId((Long) any());
        doNothing().when(resources).setKingdom((Kingdom) any());
        doNothing().when(resources).setType((String) any());
        doNothing().when(resources).setUpdatedAt((Long) any());
        resources.setAmount(10);
        resources.setGeneration(1000);
        resources.setId(123L);
        resources.setKingdom(kingdom2);
        resources.setType("Type");
        resources.setUpdatedAt(1000L);

        ArrayList<Resources> resourcesList = new ArrayList<>();
        resourcesList.add(resources);
        when(resourcesRepository.findAllByKingdom_Id((Long) any())).thenReturn(resourcesList);
        when(resourcesRepository.saveAll((Iterable<Resources>) any())).thenReturn(new ArrayList<>());
        resourcesServiceImpl.generateResources(123L);
        verify(resourcesRepository).findAllByKingdom_Id((Long) any());
        verify(resourcesRepository).saveAll((Iterable<Resources>) any());
        verify(resources).getKingdom();
        verify(resources).getGeneration();
        verify(resources).getUpdatedAt();
        verify(resources, atLeast(1)).getType();
        verify(resources).setAmount((Integer) any());
        verify(resources).setGeneration((Integer) any());
        verify(resources).setId((Long) any());
        verify(resources).setKingdom((Kingdom) any());
        verify(resources).setType((String) any());
        verify(resources).setUpdatedAt((Long) any());
    }

    /**
     * Method under test: {@link ResourcesServiceImpl#generateResources(Long)}
     */
    @Test
    void testGenerateResources8() {
        Kingdom kingdom = new Kingdom();
        kingdom.setBuildingList(new ArrayList<>());
        kingdom.setId(123L);
        kingdom.setLocation(new Location());
        kingdom.setPlayer(new Player());
        kingdom.setPopulation(1000);
        kingdom.setResourcesList(new ArrayList<>());
        kingdom.setRuler("Ruler");

        Location location = new Location();
        location.setCoordinateX(1000);
        location.setCoordinateY(1000);
        location.setId(123L);
        location.setKingdom(kingdom);

        Kingdom kingdom1 = new Kingdom();
        kingdom1.setBuildingList(new ArrayList<>());
        kingdom1.setId(123L);
        kingdom1.setLocation(new Location());
        kingdom1.setPlayer(new Player());
        kingdom1.setPopulation(1000);
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
        kingdom2.setPopulation(1000);
        kingdom2.setResourcesList(new ArrayList<>());
        kingdom2.setRuler("Ruler");

        Kingdom kingdom3 = new Kingdom();
        kingdom3.setBuildingList(new ArrayList<>());
        kingdom3.setId(123L);
        kingdom3.setLocation(new Location());
        kingdom3.setPlayer(new Player());
        kingdom3.setPopulation(1000);
        kingdom3.setResourcesList(new ArrayList<>());
        kingdom3.setRuler("gold");

        Location location1 = new Location();
        location1.setCoordinateX(1000);
        location1.setCoordinateY(1000);
        location1.setId(123L);
        location1.setKingdom(kingdom3);

        Kingdom kingdom4 = new Kingdom();
        kingdom4.setBuildingList(new ArrayList<>());
        kingdom4.setId(123L);
        kingdom4.setLocation(new Location());
        kingdom4.setPlayer(new Player());
        kingdom4.setPopulation(1000);
        kingdom4.setResourcesList(new ArrayList<>());
        kingdom4.setRuler("gold");

        Player player1 = new Player();
        player1.setId(123L);
        player1.setKingdom(kingdom4);
        player1.setKingdomName("gold");
        player1.setPassword("iloveyou");
        player1.setUsername("janedoe");

        Kingdom kingdom5 = new Kingdom();
        kingdom5.setBuildingList(new ArrayList<>());
        kingdom5.setId(123L);
        kingdom5.setLocation(location1);
        kingdom5.setPlayer(player1);
        kingdom5.setPopulation(1000);
        kingdom5.setResourcesList(new ArrayList<>());
        kingdom5.setRuler("gold");

        Buildings buildings = new Buildings();
        buildings.setId(123L);
        buildings.setKingdom(kingdom5);
        buildings.setLevel(1000);
        buildings.setType("gold");

        Kingdom kingdom6 = new Kingdom();
        kingdom6.setBuildingList(new ArrayList<>());
        kingdom6.setId(123L);
        kingdom6.setLocation(new Location());
        kingdom6.setPlayer(new Player());
        kingdom6.setPopulation(1000);
        kingdom6.setResourcesList(new ArrayList<>());
        kingdom6.setRuler("mine");

        Location location2 = new Location();
        location2.setCoordinateX(1000);
        location2.setCoordinateY(1000);
        location2.setId(123L);
        location2.setKingdom(kingdom6);

        Kingdom kingdom7 = new Kingdom();
        kingdom7.setBuildingList(new ArrayList<>());
        kingdom7.setId(123L);
        kingdom7.setLocation(new Location());
        kingdom7.setPlayer(new Player());
        kingdom7.setPopulation(1000);
        kingdom7.setResourcesList(new ArrayList<>());
        kingdom7.setRuler("mine");

        Player player2 = new Player();
        player2.setId(123L);
        player2.setKingdom(kingdom7);
        player2.setKingdomName("mine");
        player2.setPassword("iloveyou");
        player2.setUsername("janedoe");

        Kingdom kingdom8 = new Kingdom();
        kingdom8.setBuildingList(new ArrayList<>());
        kingdom8.setId(123L);
        kingdom8.setLocation(location2);
        kingdom8.setPlayer(player2);
        kingdom8.setPopulation(1000);
        kingdom8.setResourcesList(new ArrayList<>());
        kingdom8.setRuler("mine");

        Buildings buildings1 = new Buildings();
        buildings1.setId(123L);
        buildings1.setKingdom(kingdom8);
        buildings1.setLevel(1000);
        buildings1.setType("mine");

        ArrayList<Buildings> buildingsList = new ArrayList<>();
        buildingsList.add(buildings1);
        buildingsList.add(buildings);

        Kingdom kingdom9 = new Kingdom();
        kingdom9.setBuildingList(new ArrayList<>());
        kingdom9.setId(123L);
        kingdom9.setLocation(new Location());
        kingdom9.setPlayer(new Player());
        kingdom9.setPopulation(1);
        kingdom9.setResourcesList(new ArrayList<>());
        kingdom9.setRuler("Ruler");

        Location location3 = new Location();
        location3.setCoordinateX(1);
        location3.setCoordinateY(1);
        location3.setId(123L);
        location3.setKingdom(kingdom9);

        Kingdom kingdom10 = new Kingdom();
        kingdom10.setBuildingList(new ArrayList<>());
        kingdom10.setId(123L);
        kingdom10.setLocation(new Location());
        kingdom10.setPlayer(new Player());
        kingdom10.setPopulation(1);
        kingdom10.setResourcesList(new ArrayList<>());
        kingdom10.setRuler("Ruler");

        Player player3 = new Player();
        player3.setId(123L);
        player3.setKingdom(kingdom10);
        player3.setKingdomName("Kingdom Name");
        player3.setPassword("iloveyou");
        player3.setUsername("janedoe");

        Kingdom kingdom11 = new Kingdom();
        kingdom11.setBuildingList(new ArrayList<>());
        kingdom11.setId(123L);
        kingdom11.setLocation(location3);
        kingdom11.setPlayer(player3);
        kingdom11.setPopulation(1);
        kingdom11.setResourcesList(new ArrayList<>());
        kingdom11.setRuler("Ruler");

        Location location4 = new Location();
        location4.setCoordinateX(1);
        location4.setCoordinateY(1);
        location4.setId(123L);
        location4.setKingdom(kingdom11);

        Kingdom kingdom12 = new Kingdom();
        kingdom12.setBuildingList(new ArrayList<>());
        kingdom12.setId(123L);
        kingdom12.setLocation(new Location());
        kingdom12.setPlayer(new Player());
        kingdom12.setPopulation(1);
        kingdom12.setResourcesList(new ArrayList<>());
        kingdom12.setRuler("Ruler");

        Location location5 = new Location();
        location5.setCoordinateX(1);
        location5.setCoordinateY(1);
        location5.setId(123L);
        location5.setKingdom(kingdom12);

        Kingdom kingdom13 = new Kingdom();
        kingdom13.setBuildingList(new ArrayList<>());
        kingdom13.setId(123L);
        kingdom13.setLocation(new Location());
        kingdom13.setPlayer(new Player());
        kingdom13.setPopulation(1);
        kingdom13.setResourcesList(new ArrayList<>());
        kingdom13.setRuler("Ruler");

        Player player4 = new Player();
        player4.setId(123L);
        player4.setKingdom(kingdom13);
        player4.setKingdomName("Kingdom Name");
        player4.setPassword("iloveyou");
        player4.setUsername("janedoe");

        Kingdom kingdom14 = new Kingdom();
        kingdom14.setBuildingList(new ArrayList<>());
        kingdom14.setId(123L);
        kingdom14.setLocation(location5);
        kingdom14.setPlayer(player4);
        kingdom14.setPopulation(1);
        kingdom14.setResourcesList(new ArrayList<>());
        kingdom14.setRuler("Ruler");

        Player player5 = new Player();
        player5.setId(123L);
        player5.setKingdom(kingdom14);
        player5.setKingdomName("Kingdom Name");
        player5.setPassword("iloveyou");
        player5.setUsername("janedoe");

        Kingdom kingdom15 = new Kingdom();
        kingdom15.setBuildingList(buildingsList);
        kingdom15.setId(123L);
        kingdom15.setLocation(location4);
        kingdom15.setPlayer(player5);
        kingdom15.setPopulation(1);
        kingdom15.setResourcesList(new ArrayList<>());
        kingdom15.setRuler("Ruler");
        Resources resources = mock(Resources.class);
        when(resources.getAmount()).thenReturn(10);
        when(resources.getUpdatedAt()).thenReturn(1L);
        when(resources.getType()).thenReturn("Type");
        when(resources.getKingdom()).thenReturn(kingdom15);
        when(resources.getGeneration()).thenReturn(1);
        doNothing().when(resources).setAmount((Integer) any());
        doNothing().when(resources).setGeneration((Integer) any());
        doNothing().when(resources).setId((Long) any());
        doNothing().when(resources).setKingdom((Kingdom) any());
        doNothing().when(resources).setType((String) any());
        doNothing().when(resources).setUpdatedAt((Long) any());
        resources.setAmount(10);
        resources.setGeneration(1000);
        resources.setId(123L);
        resources.setKingdom(kingdom2);
        resources.setType("Type");
        resources.setUpdatedAt(1000L);

        ArrayList<Resources> resourcesList = new ArrayList<>();
        resourcesList.add(resources);
        when(resourcesRepository.findAllByKingdom_Id((Long) any())).thenReturn(resourcesList);
        when(resourcesRepository.saveAll((Iterable<Resources>) any())).thenReturn(new ArrayList<>());
        resourcesServiceImpl.generateResources(123L);
        verify(resourcesRepository).findAllByKingdom_Id((Long) any());
        verify(resourcesRepository).saveAll((Iterable<Resources>) any());
        verify(resources).getKingdom();
        verify(resources).getGeneration();
        verify(resources).getUpdatedAt();
        verify(resources, atLeast(1)).getType();
        verify(resources).setAmount((Integer) any());
        verify(resources).setGeneration((Integer) any());
        verify(resources).setId((Long) any());
        verify(resources).setKingdom((Kingdom) any());
        verify(resources).setType((String) any());
        verify(resources).setUpdatedAt((Long) any());
    }

    /**
     * Method under test: {@link ResourcesServiceImpl#getResourceGenerationPerMinute(Resources)}
     */
    @Test
    void testGetResourceGenerationPerMinute() {
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

        Resources resources = new Resources();
        resources.setAmount(10);
        resources.setGeneration(1);
        resources.setId(123L);
        resources.setKingdom(kingdom2);
        resources.setType("Type");
        resources.setUpdatedAt(1L);
        assertEquals(1, resourcesServiceImpl.getResourceGenerationPerMinute(resources).intValue());
    }

    /**
     * Method under test: {@link ResourcesServiceImpl#getResourceGenerationPerMinute(Resources)}
     */
    @Test
    void testGetResourceGenerationPerMinute2() {
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

        Kingdom kingdom3 = new Kingdom();
        kingdom3.setBuildingList(new ArrayList<>());
        kingdom3.setId(123L);
        kingdom3.setLocation(new Location());
        kingdom3.setPlayer(new Player());
        kingdom3.setPopulation(1);
        kingdom3.setResourcesList(new ArrayList<>());
        kingdom3.setRuler("Ruler");

        Location location1 = new Location();
        location1.setCoordinateX(1);
        location1.setCoordinateY(1);
        location1.setId(123L);
        location1.setKingdom(kingdom3);

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
        kingdom5.setLocation(location1);
        kingdom5.setPlayer(player1);
        kingdom5.setPopulation(1);
        kingdom5.setResourcesList(new ArrayList<>());
        kingdom5.setRuler("Ruler");

        Location location2 = new Location();
        location2.setCoordinateX(1);
        location2.setCoordinateY(1);
        location2.setId(123L);
        location2.setKingdom(kingdom5);

        Kingdom kingdom6 = new Kingdom();
        kingdom6.setBuildingList(new ArrayList<>());
        kingdom6.setId(123L);
        kingdom6.setLocation(new Location());
        kingdom6.setPlayer(new Player());
        kingdom6.setPopulation(1);
        kingdom6.setResourcesList(new ArrayList<>());
        kingdom6.setRuler("Ruler");

        Location location3 = new Location();
        location3.setCoordinateX(1);
        location3.setCoordinateY(1);
        location3.setId(123L);
        location3.setKingdom(kingdom6);

        Kingdom kingdom7 = new Kingdom();
        kingdom7.setBuildingList(new ArrayList<>());
        kingdom7.setId(123L);
        kingdom7.setLocation(new Location());
        kingdom7.setPlayer(new Player());
        kingdom7.setPopulation(1);
        kingdom7.setResourcesList(new ArrayList<>());
        kingdom7.setRuler("Ruler");

        Player player2 = new Player();
        player2.setId(123L);
        player2.setKingdom(kingdom7);
        player2.setKingdomName("Kingdom Name");
        player2.setPassword("iloveyou");
        player2.setUsername("janedoe");

        Kingdom kingdom8 = new Kingdom();
        kingdom8.setBuildingList(new ArrayList<>());
        kingdom8.setId(123L);
        kingdom8.setLocation(location3);
        kingdom8.setPlayer(player2);
        kingdom8.setPopulation(1);
        kingdom8.setResourcesList(new ArrayList<>());
        kingdom8.setRuler("Ruler");

        Player player3 = new Player();
        player3.setId(123L);
        player3.setKingdom(kingdom8);
        player3.setKingdomName("Kingdom Name");
        player3.setPassword("iloveyou");
        player3.setUsername("janedoe");

        Kingdom kingdom9 = new Kingdom();
        kingdom9.setBuildingList(new ArrayList<>());
        kingdom9.setId(123L);
        kingdom9.setLocation(location2);
        kingdom9.setPlayer(player3);
        kingdom9.setPopulation(1);
        kingdom9.setResourcesList(new ArrayList<>());
        kingdom9.setRuler("Ruler");
        Resources resources = mock(Resources.class);
        when(resources.getType()).thenReturn("Type");
        when(resources.getKingdom()).thenReturn(kingdom9);
        when(resources.getGeneration()).thenReturn(1);
        doNothing().when(resources).setAmount((Integer) any());
        doNothing().when(resources).setGeneration((Integer) any());
        doNothing().when(resources).setId((Long) any());
        doNothing().when(resources).setKingdom((Kingdom) any());
        doNothing().when(resources).setType((String) any());
        doNothing().when(resources).setUpdatedAt((Long) any());
        resources.setAmount(10);
        resources.setGeneration(1);
        resources.setId(123L);
        resources.setKingdom(kingdom2);
        resources.setType("Type");
        resources.setUpdatedAt(1L);
        assertEquals(1, resourcesServiceImpl.getResourceGenerationPerMinute(resources).intValue());
        verify(resources).getKingdom();
        verify(resources).getGeneration();
        verify(resources, atLeast(1)).getType();
        verify(resources).setAmount((Integer) any());
        verify(resources).setGeneration((Integer) any());
        verify(resources).setId((Long) any());
        verify(resources).setKingdom((Kingdom) any());
        verify(resources).setType((String) any());
        verify(resources).setUpdatedAt((Long) any());
    }

    /**
     * Method under test: {@link ResourcesServiceImpl#getResourceGenerationPerMinute(Resources)}
     */
    @Test
    void testGetResourceGenerationPerMinute3() {
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

        Kingdom kingdom3 = new Kingdom();
        kingdom3.setBuildingList(new ArrayList<>());
        kingdom3.setId(123L);
        kingdom3.setLocation(new Location());
        kingdom3.setPlayer(new Player());
        kingdom3.setPopulation(1);
        kingdom3.setResourcesList(new ArrayList<>());
        kingdom3.setRuler("Ruler");

        Location location1 = new Location();
        location1.setCoordinateX(1);
        location1.setCoordinateY(1);
        location1.setId(123L);
        location1.setKingdom(kingdom3);

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
        kingdom5.setLocation(location1);
        kingdom5.setPlayer(player1);
        kingdom5.setPopulation(1);
        kingdom5.setResourcesList(new ArrayList<>());
        kingdom5.setRuler("Ruler");

        Location location2 = new Location();
        location2.setCoordinateX(1);
        location2.setCoordinateY(1);
        location2.setId(123L);
        location2.setKingdom(kingdom5);

        Kingdom kingdom6 = new Kingdom();
        kingdom6.setBuildingList(new ArrayList<>());
        kingdom6.setId(123L);
        kingdom6.setLocation(new Location());
        kingdom6.setPlayer(new Player());
        kingdom6.setPopulation(1);
        kingdom6.setResourcesList(new ArrayList<>());
        kingdom6.setRuler("Ruler");

        Location location3 = new Location();
        location3.setCoordinateX(1);
        location3.setCoordinateY(1);
        location3.setId(123L);
        location3.setKingdom(kingdom6);

        Kingdom kingdom7 = new Kingdom();
        kingdom7.setBuildingList(new ArrayList<>());
        kingdom7.setId(123L);
        kingdom7.setLocation(new Location());
        kingdom7.setPlayer(new Player());
        kingdom7.setPopulation(1);
        kingdom7.setResourcesList(new ArrayList<>());
        kingdom7.setRuler("Ruler");

        Player player2 = new Player();
        player2.setId(123L);
        player2.setKingdom(kingdom7);
        player2.setKingdomName("Kingdom Name");
        player2.setPassword("iloveyou");
        player2.setUsername("janedoe");

        Kingdom kingdom8 = new Kingdom();
        kingdom8.setBuildingList(new ArrayList<>());
        kingdom8.setId(123L);
        kingdom8.setLocation(location3);
        kingdom8.setPlayer(player2);
        kingdom8.setPopulation(1);
        kingdom8.setResourcesList(new ArrayList<>());
        kingdom8.setRuler("Ruler");

        Player player3 = new Player();
        player3.setId(123L);
        player3.setKingdom(kingdom8);
        player3.setKingdomName("Kingdom Name");
        player3.setPassword("iloveyou");
        player3.setUsername("janedoe");

        Kingdom kingdom9 = new Kingdom();
        kingdom9.setBuildingList(new ArrayList<>());
        kingdom9.setId(123L);
        kingdom9.setLocation(location2);
        kingdom9.setPlayer(player3);
        kingdom9.setPopulation(1);
        kingdom9.setResourcesList(new ArrayList<>());
        kingdom9.setRuler("Ruler");
        Resources resources = mock(Resources.class);
        when(resources.getType()).thenReturn("gold");
        when(resources.getKingdom()).thenReturn(kingdom9);
        when(resources.getGeneration()).thenReturn(1);
        doNothing().when(resources).setAmount((Integer) any());
        doNothing().when(resources).setGeneration((Integer) any());
        doNothing().when(resources).setId((Long) any());
        doNothing().when(resources).setKingdom((Kingdom) any());
        doNothing().when(resources).setType((String) any());
        doNothing().when(resources).setUpdatedAt((Long) any());
        resources.setAmount(10);
        resources.setGeneration(1);
        resources.setId(123L);
        resources.setKingdom(kingdom2);
        resources.setType("Type");
        resources.setUpdatedAt(1L);
        assertEquals(0, resourcesServiceImpl.getResourceGenerationPerMinute(resources).intValue());
        verify(resources).getKingdom();
        verify(resources).getGeneration();
        verify(resources, atLeast(1)).getType();
        verify(resources).setAmount((Integer) any());
        verify(resources).setGeneration((Integer) any());
        verify(resources).setId((Long) any());
        verify(resources).setKingdom((Kingdom) any());
        verify(resources).setType((String) any());
        verify(resources).setUpdatedAt((Long) any());
    }

    /**
     * Method under test: {@link ResourcesServiceImpl#getResourceGenerationPerMinute(Resources)}
     */
    @Test
    void testGetResourceGenerationPerMinute4() {
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

        Kingdom kingdom3 = new Kingdom();
        kingdom3.setBuildingList(new ArrayList<>());
        kingdom3.setId(123L);
        kingdom3.setLocation(new Location());
        kingdom3.setPlayer(new Player());
        kingdom3.setPopulation(1);
        kingdom3.setResourcesList(new ArrayList<>());
        kingdom3.setRuler("Ruler");

        Location location1 = new Location();
        location1.setCoordinateX(1);
        location1.setCoordinateY(1);
        location1.setId(123L);
        location1.setKingdom(kingdom3);

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
        kingdom5.setLocation(location1);
        kingdom5.setPlayer(player1);
        kingdom5.setPopulation(1);
        kingdom5.setResourcesList(new ArrayList<>());
        kingdom5.setRuler("Ruler");

        Location location2 = new Location();
        location2.setCoordinateX(1);
        location2.setCoordinateY(1);
        location2.setId(123L);
        location2.setKingdom(kingdom5);

        Kingdom kingdom6 = new Kingdom();
        kingdom6.setBuildingList(new ArrayList<>());
        kingdom6.setId(123L);
        kingdom6.setLocation(new Location());
        kingdom6.setPlayer(new Player());
        kingdom6.setPopulation(1);
        kingdom6.setResourcesList(new ArrayList<>());
        kingdom6.setRuler("Ruler");

        Location location3 = new Location();
        location3.setCoordinateX(1);
        location3.setCoordinateY(1);
        location3.setId(123L);
        location3.setKingdom(kingdom6);

        Kingdom kingdom7 = new Kingdom();
        kingdom7.setBuildingList(new ArrayList<>());
        kingdom7.setId(123L);
        kingdom7.setLocation(new Location());
        kingdom7.setPlayer(new Player());
        kingdom7.setPopulation(1);
        kingdom7.setResourcesList(new ArrayList<>());
        kingdom7.setRuler("Ruler");

        Player player2 = new Player();
        player2.setId(123L);
        player2.setKingdom(kingdom7);
        player2.setKingdomName("Kingdom Name");
        player2.setPassword("iloveyou");
        player2.setUsername("janedoe");

        Kingdom kingdom8 = new Kingdom();
        kingdom8.setBuildingList(new ArrayList<>());
        kingdom8.setId(123L);
        kingdom8.setLocation(location3);
        kingdom8.setPlayer(player2);
        kingdom8.setPopulation(1);
        kingdom8.setResourcesList(new ArrayList<>());
        kingdom8.setRuler("Ruler");

        Player player3 = new Player();
        player3.setId(123L);
        player3.setKingdom(kingdom8);
        player3.setKingdomName("Kingdom Name");
        player3.setPassword("iloveyou");
        player3.setUsername("janedoe");

        Kingdom kingdom9 = new Kingdom();
        kingdom9.setBuildingList(new ArrayList<>());
        kingdom9.setId(123L);
        kingdom9.setLocation(location2);
        kingdom9.setPlayer(player3);
        kingdom9.setPopulation(1);
        kingdom9.setResourcesList(new ArrayList<>());
        kingdom9.setRuler("Ruler");
        Resources resources = mock(Resources.class);
        when(resources.getType()).thenReturn("food");
        when(resources.getKingdom()).thenReturn(kingdom9);
        when(resources.getGeneration()).thenReturn(1);
        doNothing().when(resources).setAmount((Integer) any());
        doNothing().when(resources).setGeneration((Integer) any());
        doNothing().when(resources).setId((Long) any());
        doNothing().when(resources).setKingdom((Kingdom) any());
        doNothing().when(resources).setType((String) any());
        doNothing().when(resources).setUpdatedAt((Long) any());
        resources.setAmount(10);
        resources.setGeneration(1);
        resources.setId(123L);
        resources.setKingdom(kingdom2);
        resources.setType("Type");
        resources.setUpdatedAt(1L);
        assertEquals(0, resourcesServiceImpl.getResourceGenerationPerMinute(resources).intValue());
        verify(resources).getKingdom();
        verify(resources).getGeneration();
        verify(resources, atLeast(1)).getType();
        verify(resources).setAmount((Integer) any());
        verify(resources).setGeneration((Integer) any());
        verify(resources).setId((Long) any());
        verify(resources).setKingdom((Kingdom) any());
        verify(resources).setType((String) any());
        verify(resources).setUpdatedAt((Long) any());
    }

    /**
     * Method under test: {@link ResourcesServiceImpl#getResourceGenerationPerMinute(Resources)}
     */
    @Test
    void testGetResourceGenerationPerMinute5() {
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

        Kingdom kingdom3 = new Kingdom();
        kingdom3.setBuildingList(new ArrayList<>());
        kingdom3.setId(123L);
        kingdom3.setLocation(new Location());
        kingdom3.setPlayer(new Player());
        kingdom3.setPopulation(1);
        kingdom3.setResourcesList(new ArrayList<>());
        kingdom3.setRuler("gold");

        Location location1 = new Location();
        location1.setCoordinateX(1);
        location1.setCoordinateY(1);
        location1.setId(123L);
        location1.setKingdom(kingdom3);

        Kingdom kingdom4 = new Kingdom();
        kingdom4.setBuildingList(new ArrayList<>());
        kingdom4.setId(123L);
        kingdom4.setLocation(new Location());
        kingdom4.setPlayer(new Player());
        kingdom4.setPopulation(1);
        kingdom4.setResourcesList(new ArrayList<>());
        kingdom4.setRuler("gold");

        Player player1 = new Player();
        player1.setId(123L);
        player1.setKingdom(kingdom4);
        player1.setKingdomName("gold");
        player1.setPassword("iloveyou");
        player1.setUsername("janedoe");

        Kingdom kingdom5 = new Kingdom();
        kingdom5.setBuildingList(new ArrayList<>());
        kingdom5.setId(123L);
        kingdom5.setLocation(location1);
        kingdom5.setPlayer(player1);
        kingdom5.setPopulation(1);
        kingdom5.setResourcesList(new ArrayList<>());
        kingdom5.setRuler("gold");

        Buildings buildings = new Buildings();
        buildings.setId(123L);
        buildings.setKingdom(kingdom5);
        buildings.setLevel(1);
        buildings.setType("gold");

        ArrayList<Buildings> buildingsList = new ArrayList<>();
        buildingsList.add(buildings);

        Kingdom kingdom6 = new Kingdom();
        kingdom6.setBuildingList(new ArrayList<>());
        kingdom6.setId(123L);
        kingdom6.setLocation(new Location());
        kingdom6.setPlayer(new Player());
        kingdom6.setPopulation(1);
        kingdom6.setResourcesList(new ArrayList<>());
        kingdom6.setRuler("Ruler");

        Location location2 = new Location();
        location2.setCoordinateX(1);
        location2.setCoordinateY(1);
        location2.setId(123L);
        location2.setKingdom(kingdom6);

        Kingdom kingdom7 = new Kingdom();
        kingdom7.setBuildingList(new ArrayList<>());
        kingdom7.setId(123L);
        kingdom7.setLocation(new Location());
        kingdom7.setPlayer(new Player());
        kingdom7.setPopulation(1);
        kingdom7.setResourcesList(new ArrayList<>());
        kingdom7.setRuler("Ruler");

        Player player2 = new Player();
        player2.setId(123L);
        player2.setKingdom(kingdom7);
        player2.setKingdomName("Kingdom Name");
        player2.setPassword("iloveyou");
        player2.setUsername("janedoe");

        Kingdom kingdom8 = new Kingdom();
        kingdom8.setBuildingList(new ArrayList<>());
        kingdom8.setId(123L);
        kingdom8.setLocation(location2);
        kingdom8.setPlayer(player2);
        kingdom8.setPopulation(1);
        kingdom8.setResourcesList(new ArrayList<>());
        kingdom8.setRuler("Ruler");

        Location location3 = new Location();
        location3.setCoordinateX(1);
        location3.setCoordinateY(1);
        location3.setId(123L);
        location3.setKingdom(kingdom8);

        Kingdom kingdom9 = new Kingdom();
        kingdom9.setBuildingList(new ArrayList<>());
        kingdom9.setId(123L);
        kingdom9.setLocation(new Location());
        kingdom9.setPlayer(new Player());
        kingdom9.setPopulation(1);
        kingdom9.setResourcesList(new ArrayList<>());
        kingdom9.setRuler("Ruler");

        Location location4 = new Location();
        location4.setCoordinateX(1);
        location4.setCoordinateY(1);
        location4.setId(123L);
        location4.setKingdom(kingdom9);

        Kingdom kingdom10 = new Kingdom();
        kingdom10.setBuildingList(new ArrayList<>());
        kingdom10.setId(123L);
        kingdom10.setLocation(new Location());
        kingdom10.setPlayer(new Player());
        kingdom10.setPopulation(1);
        kingdom10.setResourcesList(new ArrayList<>());
        kingdom10.setRuler("Ruler");

        Player player3 = new Player();
        player3.setId(123L);
        player3.setKingdom(kingdom10);
        player3.setKingdomName("Kingdom Name");
        player3.setPassword("iloveyou");
        player3.setUsername("janedoe");

        Kingdom kingdom11 = new Kingdom();
        kingdom11.setBuildingList(new ArrayList<>());
        kingdom11.setId(123L);
        kingdom11.setLocation(location4);
        kingdom11.setPlayer(player3);
        kingdom11.setPopulation(1);
        kingdom11.setResourcesList(new ArrayList<>());
        kingdom11.setRuler("Ruler");

        Player player4 = new Player();
        player4.setId(123L);
        player4.setKingdom(kingdom11);
        player4.setKingdomName("Kingdom Name");
        player4.setPassword("iloveyou");
        player4.setUsername("janedoe");

        Kingdom kingdom12 = new Kingdom();
        kingdom12.setBuildingList(buildingsList);
        kingdom12.setId(123L);
        kingdom12.setLocation(location3);
        kingdom12.setPlayer(player4);
        kingdom12.setPopulation(1);
        kingdom12.setResourcesList(new ArrayList<>());
        kingdom12.setRuler("Ruler");
        Resources resources = mock(Resources.class);
        when(resources.getType()).thenReturn("Type");
        when(resources.getKingdom()).thenReturn(kingdom12);
        when(resources.getGeneration()).thenReturn(1);
        doNothing().when(resources).setAmount((Integer) any());
        doNothing().when(resources).setGeneration((Integer) any());
        doNothing().when(resources).setId((Long) any());
        doNothing().when(resources).setKingdom((Kingdom) any());
        doNothing().when(resources).setType((String) any());
        doNothing().when(resources).setUpdatedAt((Long) any());
        resources.setAmount(10);
        resources.setGeneration(1);
        resources.setId(123L);
        resources.setKingdom(kingdom2);
        resources.setType("Type");
        resources.setUpdatedAt(1L);
        assertEquals(1, resourcesServiceImpl.getResourceGenerationPerMinute(resources).intValue());
        verify(resources).getKingdom();
        verify(resources).getGeneration();
        verify(resources, atLeast(1)).getType();
        verify(resources).setAmount((Integer) any());
        verify(resources).setGeneration((Integer) any());
        verify(resources).setId((Long) any());
        verify(resources).setKingdom((Kingdom) any());
        verify(resources).setType((String) any());
        verify(resources).setUpdatedAt((Long) any());
    }

    /**
     * Method under test: {@link ResourcesServiceImpl#getResourceGenerationPerMinute(Resources)}
     */
    @Test
    void testGetResourceGenerationPerMinute6() {
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

        Kingdom kingdom3 = new Kingdom();
        kingdom3.setBuildingList(new ArrayList<>());
        kingdom3.setId(123L);
        kingdom3.setLocation(new Location());
        kingdom3.setPlayer(new Player());
        kingdom3.setPopulation(1);
        kingdom3.setResourcesList(new ArrayList<>());
        kingdom3.setRuler("gold");

        Location location1 = new Location();
        location1.setCoordinateX(1);
        location1.setCoordinateY(1);
        location1.setId(123L);
        location1.setKingdom(kingdom3);

        Kingdom kingdom4 = new Kingdom();
        kingdom4.setBuildingList(new ArrayList<>());
        kingdom4.setId(123L);
        kingdom4.setLocation(new Location());
        kingdom4.setPlayer(new Player());
        kingdom4.setPopulation(1);
        kingdom4.setResourcesList(new ArrayList<>());
        kingdom4.setRuler("gold");

        Player player1 = new Player();
        player1.setId(123L);
        player1.setKingdom(kingdom4);
        player1.setKingdomName("gold");
        player1.setPassword("iloveyou");
        player1.setUsername("janedoe");

        Kingdom kingdom5 = new Kingdom();
        kingdom5.setBuildingList(new ArrayList<>());
        kingdom5.setId(123L);
        kingdom5.setLocation(location1);
        kingdom5.setPlayer(player1);
        kingdom5.setPopulation(1);
        kingdom5.setResourcesList(new ArrayList<>());
        kingdom5.setRuler("gold");

        Buildings buildings = new Buildings();
        buildings.setId(123L);
        buildings.setKingdom(kingdom5);
        buildings.setLevel(1);
        buildings.setType("gold");

        Kingdom kingdom6 = new Kingdom();
        kingdom6.setBuildingList(new ArrayList<>());
        kingdom6.setId(123L);
        kingdom6.setLocation(new Location());
        kingdom6.setPlayer(new Player());
        kingdom6.setPopulation(1);
        kingdom6.setResourcesList(new ArrayList<>());
        kingdom6.setRuler("mine");

        Location location2 = new Location();
        location2.setCoordinateX(1);
        location2.setCoordinateY(1);
        location2.setId(123L);
        location2.setKingdom(kingdom6);

        Kingdom kingdom7 = new Kingdom();
        kingdom7.setBuildingList(new ArrayList<>());
        kingdom7.setId(123L);
        kingdom7.setLocation(new Location());
        kingdom7.setPlayer(new Player());
        kingdom7.setPopulation(1);
        kingdom7.setResourcesList(new ArrayList<>());
        kingdom7.setRuler("mine");

        Player player2 = new Player();
        player2.setId(123L);
        player2.setKingdom(kingdom7);
        player2.setKingdomName("mine");
        player2.setPassword("iloveyou");
        player2.setUsername("janedoe");

        Kingdom kingdom8 = new Kingdom();
        kingdom8.setBuildingList(new ArrayList<>());
        kingdom8.setId(123L);
        kingdom8.setLocation(location2);
        kingdom8.setPlayer(player2);
        kingdom8.setPopulation(1);
        kingdom8.setResourcesList(new ArrayList<>());
        kingdom8.setRuler("mine");

        Buildings buildings1 = new Buildings();
        buildings1.setId(123L);
        buildings1.setKingdom(kingdom8);
        buildings1.setLevel(1);
        buildings1.setType("mine");

        ArrayList<Buildings> buildingsList = new ArrayList<>();
        buildingsList.add(buildings1);
        buildingsList.add(buildings);

        Kingdom kingdom9 = new Kingdom();
        kingdom9.setBuildingList(new ArrayList<>());
        kingdom9.setId(123L);
        kingdom9.setLocation(new Location());
        kingdom9.setPlayer(new Player());
        kingdom9.setPopulation(1);
        kingdom9.setResourcesList(new ArrayList<>());
        kingdom9.setRuler("Ruler");

        Location location3 = new Location();
        location3.setCoordinateX(1);
        location3.setCoordinateY(1);
        location3.setId(123L);
        location3.setKingdom(kingdom9);

        Kingdom kingdom10 = new Kingdom();
        kingdom10.setBuildingList(new ArrayList<>());
        kingdom10.setId(123L);
        kingdom10.setLocation(new Location());
        kingdom10.setPlayer(new Player());
        kingdom10.setPopulation(1);
        kingdom10.setResourcesList(new ArrayList<>());
        kingdom10.setRuler("Ruler");

        Player player3 = new Player();
        player3.setId(123L);
        player3.setKingdom(kingdom10);
        player3.setKingdomName("Kingdom Name");
        player3.setPassword("iloveyou");
        player3.setUsername("janedoe");

        Kingdom kingdom11 = new Kingdom();
        kingdom11.setBuildingList(new ArrayList<>());
        kingdom11.setId(123L);
        kingdom11.setLocation(location3);
        kingdom11.setPlayer(player3);
        kingdom11.setPopulation(1);
        kingdom11.setResourcesList(new ArrayList<>());
        kingdom11.setRuler("Ruler");

        Location location4 = new Location();
        location4.setCoordinateX(1);
        location4.setCoordinateY(1);
        location4.setId(123L);
        location4.setKingdom(kingdom11);

        Kingdom kingdom12 = new Kingdom();
        kingdom12.setBuildingList(new ArrayList<>());
        kingdom12.setId(123L);
        kingdom12.setLocation(new Location());
        kingdom12.setPlayer(new Player());
        kingdom12.setPopulation(1);
        kingdom12.setResourcesList(new ArrayList<>());
        kingdom12.setRuler("Ruler");

        Location location5 = new Location();
        location5.setCoordinateX(1);
        location5.setCoordinateY(1);
        location5.setId(123L);
        location5.setKingdom(kingdom12);

        Kingdom kingdom13 = new Kingdom();
        kingdom13.setBuildingList(new ArrayList<>());
        kingdom13.setId(123L);
        kingdom13.setLocation(new Location());
        kingdom13.setPlayer(new Player());
        kingdom13.setPopulation(1);
        kingdom13.setResourcesList(new ArrayList<>());
        kingdom13.setRuler("Ruler");

        Player player4 = new Player();
        player4.setId(123L);
        player4.setKingdom(kingdom13);
        player4.setKingdomName("Kingdom Name");
        player4.setPassword("iloveyou");
        player4.setUsername("janedoe");

        Kingdom kingdom14 = new Kingdom();
        kingdom14.setBuildingList(new ArrayList<>());
        kingdom14.setId(123L);
        kingdom14.setLocation(location5);
        kingdom14.setPlayer(player4);
        kingdom14.setPopulation(1);
        kingdom14.setResourcesList(new ArrayList<>());
        kingdom14.setRuler("Ruler");

        Player player5 = new Player();
        player5.setId(123L);
        player5.setKingdom(kingdom14);
        player5.setKingdomName("Kingdom Name");
        player5.setPassword("iloveyou");
        player5.setUsername("janedoe");

        Kingdom kingdom15 = new Kingdom();
        kingdom15.setBuildingList(buildingsList);
        kingdom15.setId(123L);
        kingdom15.setLocation(location4);
        kingdom15.setPlayer(player5);
        kingdom15.setPopulation(1);
        kingdom15.setResourcesList(new ArrayList<>());
        kingdom15.setRuler("Ruler");
        Resources resources = mock(Resources.class);
        when(resources.getType()).thenReturn("Type");
        when(resources.getKingdom()).thenReturn(kingdom15);
        when(resources.getGeneration()).thenReturn(1);
        doNothing().when(resources).setAmount((Integer) any());
        doNothing().when(resources).setGeneration((Integer) any());
        doNothing().when(resources).setId((Long) any());
        doNothing().when(resources).setKingdom((Kingdom) any());
        doNothing().when(resources).setType((String) any());
        doNothing().when(resources).setUpdatedAt((Long) any());
        resources.setAmount(10);
        resources.setGeneration(1);
        resources.setId(123L);
        resources.setKingdom(kingdom2);
        resources.setType("Type");
        resources.setUpdatedAt(1L);
        assertEquals(1, resourcesServiceImpl.getResourceGenerationPerMinute(resources).intValue());
        verify(resources).getKingdom();
        verify(resources).getGeneration();
        verify(resources, atLeast(1)).getType();
        verify(resources).setAmount((Integer) any());
        verify(resources).setGeneration((Integer) any());
        verify(resources).setId((Long) any());
        verify(resources).setKingdom((Kingdom) any());
        verify(resources).setType((String) any());
        verify(resources).setUpdatedAt((Long) any());
    }

    /**
     * Method under test: {@link ResourcesServiceImpl#useResource(Resources, Integer)}
     */
    @Test
    void testUseResource() {
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

        Resources resources = new Resources();
        resources.setAmount(10);
        resources.setGeneration(1);
        resources.setId(123L);
        resources.setKingdom(kingdom2);
        resources.setType("Type");
        resources.setUpdatedAt(1L);
        when(resourcesRepository.save((Resources) any())).thenReturn(resources);

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

        Resources resources1 = new Resources();
        resources1.setAmount(10);
        resources1.setGeneration(1);
        resources1.setId(123L);
        resources1.setKingdom(kingdom5);
        resources1.setType("Type");
        resources1.setUpdatedAt(1L);
        assertTrue(resourcesServiceImpl.useResource(resources1, 10));
        verify(resourcesRepository).save((Resources) any());
        assertEquals(0, resources1.getAmount().intValue());
    }

    /**
     * Method under test: {@link ResourcesServiceImpl#getResourcesByKingdomId(Long)}
     */
    @Test
    void testGetResourcesByKingdomId() {
        ArrayList<Resources> resourcesList = new ArrayList<>();
        when(resourcesRepository.findAllByKingdom_Id((Long) any())).thenReturn(resourcesList);
        List<Resources> actualResourcesByKingdomId = resourcesServiceImpl.getResourcesByKingdomId(123L);
        assertSame(resourcesList, actualResourcesByKingdomId);
        assertTrue(actualResourcesByKingdomId.isEmpty());
        verify(resourcesRepository).findAllByKingdom_Id((Long) any());
    }
}

