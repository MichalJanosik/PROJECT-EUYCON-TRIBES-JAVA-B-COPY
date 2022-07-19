package com.example.projecteucyonjavatribesb.controller;

import com.example.projecteucyonjavatribesb.model.DTO.KingdomDetailsDTO;
import com.example.projecteucyonjavatribesb.model.Kingdom;
import com.example.projecteucyonjavatribesb.model.Player;
import com.example.projecteucyonjavatribesb.service.BuildingsService;
import com.example.projecteucyonjavatribesb.service.KingdomService;
import com.example.projecteucyonjavatribesb.service.PlayerAuthorizationService;
import com.example.projecteucyonjavatribesb.service.ResourcesService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {KingdomControllerIntegrationTests.class})

//@ContextConfiguration(classes = {KingdomController.class})
@ExtendWith(SpringExtension.class)
class KingdomControllerKingdomDetailsTest {
    @MockBean
    private BuildingsService buildingsService;

    @Autowired
    private KingdomControllerIntegrationTests kingdomController;

    @MockBean
    private KingdomService kingdomService;

    @MockBean
    private PlayerAuthorizationService playerAuthorizationService;

    @MockBean
    private ResourcesService resourcesService;

    @Test
    void testGetKingdomDetailsUnsuccessful() throws Exception {
        when(playerAuthorizationService.playerOwnsKingdom((String) any(), (Long) any())).thenReturn(false);

        Kingdom kingdom = new Kingdom();
        Player player = new Player();

        player.setId(1L);
        player.setKingdom(kingdom);
        player.setKingdomName("Moria");
        player.setPassword("password");
        player.setUsername("Michael");


        kingdom.setId(1L);
        kingdom.setPlayer(player);
        kingdom.setRuler("Michael");

        when(kingdomService.getKingdomDetailsDTOById((Long) any())).thenReturn(mock(KingdomDetailsDTO.class));
        when(kingdomService.findKingdomById((Long) any())).thenReturn(kingdom);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/kingdoms/{id}", 1L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(kingdomController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(401))
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"error\":\"This kingdom does not belong to authenticated player!\"}"));
    }

//    @Test
//    void testGetKingdomDetailsSuccessful() throws Exception {
//        when(playerAuthorizationService.playerOwnsKingdom((String) any(), (Long) any())).thenReturn(true);
//
//        Player player = new Player();
//        Location location = new Location();
//        Kingdom kingdom = new Kingdom();
//
//        player.setId(1L);
//        player.setKingdom(kingdom);
//        player.setKingdomName("Mordor");
//        player.setPassword("password");
//        player.setUsername("David");
//
//        kingdom.setBuildingList(new ArrayList<>());
//        kingdom.setId(1L);
//        kingdom.setLocation(location);
//        kingdom.setPlayer(player);
//        kingdom.setPopulation(1);
//        kingdom.setResourcesList(new ArrayList<>());
//        kingdom.setRuler("David");
//
//        location.setCoordinateX(1);
//        location.setCoordinateY(1);
//        location.setId(1L);
//        location.setKingdom(kingdom);
//
//        when(kingdomService.getKingdomDetailsDTOById((Long) any())).thenReturn(mock(KingdomDetailsDTO.class));
//        when(kingdomService.findKingdomById((Long) any())).thenReturn(kingdom);
//        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/kingdoms/{id}", 1L);
////                .header("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJEYXZpZCIsImlzcyI6Imh0dHA6Ly9sb2NhbGhvc3Q6ODA4MC9hcGkvbG9naW4iLCJraW5nZG9tIjpbIk1vcmRvciJdfQ.jbNwmyt6ubw0a1WCysV3zTIrqc6uvI2WO6Jw9IkpHRg");
//        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(kingdomController)
//                .build()
//                .perform(requestBuilder);
//        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(200));
////                        .string("{\"kingdom\":\"kingdomId\":\1,\"kingdomName\":\"Mordor\",\"ruler\":\"David\",\"population\":14,\"location\":\"coordinateX\":12,\"coordinateY\":8,\"resources\":\"buildings\":\"}"));
//    }
}