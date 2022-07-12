package com.example.projecteucyonjavatribesb.projecteucyonjavatribesb.controller;

import com.example.projecteucyonjavatribesb.controller.KingdomController;
import com.example.projecteucyonjavatribesb.model.Buildings;
import com.example.projecteucyonjavatribesb.model.Kingdom;
import com.example.projecteucyonjavatribesb.model.Location;
import com.example.projecteucyonjavatribesb.model.Player;
import com.example.projecteucyonjavatribesb.repository.KingdomRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class KingdomControllerTest {

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer();

    private MockMvc mockMvc;

    @Mock
    private KingdomRepository kingdomRepository;

    @InjectMocks
    private KingdomController kingdomController;


    Player player = new Player("password123", "Tom");
    Location location = new Location(1, 1);
    Buildings building1 = new Buildings("farm", 1);
    Buildings building2 = new Buildings("wall", 1);
    Buildings building3 = new Buildings("mine", 1);

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(kingdomController).build();
    }

//    @Test
//    void getKingdomBuildings_success() throws Exception {
//        Kingdom kingdom = new Kingdom();
//        kingdom.setId(1L);
//        kingdom.setPopulation(10);
//        kingdom.setRuler("Tom");
//        kingdom.setPlayer(player);
//        kingdom.setLocation(location);
//        kingdom.setBuildingList(new ArrayList<>());
//        kingdom.getBuildingList().add(building1);
//        kingdom.getBuildingList().add(building2);
//        kingdom.getBuildingList().add(building3);
//
//        Mockito.when(kingdomRepository.findKingdomById(kingdom.getId()).getBuildingList()).thenReturn(kingdom.getBuildingList());
//
//        mockMvc.perform(MockMvcRequestBuilders
//                .get("kingdom/1/buildings")
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(3)))
//                .andExpect(jsonPath("$[1].type", is("wall"))));
//    }

    @Test
    void upgradeBuildings() {
    }
}