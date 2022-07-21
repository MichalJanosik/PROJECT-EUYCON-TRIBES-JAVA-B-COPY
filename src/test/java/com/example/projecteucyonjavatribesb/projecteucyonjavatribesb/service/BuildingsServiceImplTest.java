//package com.example.projecteucyonjavatribesb.projecteucyonjavatribesb.service;
//
//import com.example.projecteucyonjavatribesb.model.Buildings;
//import com.example.projecteucyonjavatribesb.model.DTO.KingdomBuildingsDTO;
//import com.example.projecteucyonjavatribesb.model.DTO.KingdomDTO;
//import com.example.projecteucyonjavatribesb.model.Kingdom;
//import com.example.projecteucyonjavatribesb.model.Location;
//import com.example.projecteucyonjavatribesb.model.Player;
//import com.example.projecteucyonjavatribesb.repository.KingdomRepository;
//import com.example.projecteucyonjavatribesb.service.BuildingsServiceImpl;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.util.ArrayList;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertSame;
//import static org.mockito.Mockito.*;
//
//@ContextConfiguration(classes = {BuildingsServiceImpl.class})
//@ExtendWith(SpringExtension.class)
//@SpringBootTest
//@RunWith(MockitoJUnitRunner.class)
//class BuildingsServiceImplTest {
//
//    @Mock
//    KingdomRepository kingdomRepository;
//
//    @InjectMocks
//    BuildingsServiceImpl buildingsServiceImpl;
//
//    @Test
//    void testMakeKingdomBuildingsDTO() {
//        Kingdom kingdom = new Kingdom();
//        kingdom.setBuildingList(new ArrayList<>());
//        kingdom.setId(123L);
//        kingdom.setLocation(new Location());
//        kingdom.setPopulation(1);
//        kingdom.setResourcesList(new ArrayList<>());
//        kingdom.setRuler("Ruler");
//
//        Location location = new Location();
//        location.setCoordinateX(1);
//        location.setCoordinateY(1);
//        location.setId(123L);
//
//        Player player = new Player();
//        player.setId(123L);
//        player.setKingdomName("Kingdom Name");
//        player.setPassword("iloveyou");
//        player.setUsername("janedoe");
//
//        player.setKingdom(kingdom);
//        location.setKingdom(kingdom);
//        kingdom.setPlayer(player);
//
//
//        when(kingdomRepository.getKingdomById((Long) any())).thenReturn(kingdom);
//        KingdomBuildingsDTO actualMakeKingdomBuildingsDTOResult = buildingsServiceImpl.makeKingdomBuildingsDTO(123L);
//        KingdomDTO kingdom1 = actualMakeKingdomBuildingsDTOResult.getKingdom();
//        assertEquals(123L, kingdom1.getKingdomId());
//        assertEquals("Ruler", kingdom1.getRuler());
//    }
//
//}
//
