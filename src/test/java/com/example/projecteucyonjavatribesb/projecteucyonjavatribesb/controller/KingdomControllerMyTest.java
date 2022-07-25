package com.example.projecteucyonjavatribesb.projecteucyonjavatribesb.controller;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.projecteucyonjavatribesb.model.Player;
import com.example.projecteucyonjavatribesb.repository.PlayerRepository;
import com.example.projecteucyonjavatribesb.service.PlayerService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@AutoConfigureMockMvc
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class KingdomControllerMyTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private PlayerRepository playerRepository;

    static String TOKEN;
    static String INCORRECT_TOKEN;
    static String SHORT_TOKEN;
    static Long ID;
    static Long ID2;
    static Long kingdomID;
    static String USERNAME;
    static String PASSWORD;
    static String KINGDOM_NAME;

    @BeforeAll
    void setUp() throws Exception {
        USERNAME = "Tomass";
        PASSWORD = "password";
        KINGDOM_NAME = "Mordor";

        playerService.saveNewPlayer(new Player(PASSWORD, USERNAME, KINGDOM_NAME));
        playerService.saveNewPlayer(new Player("password", "user2", "justForCheck"));

        TOKEN = extractToken();
        INCORRECT_TOKEN =
                "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9" +
                        ".eyJzdWIiOiJqYW5rb0hyYXNrbzIiLCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgwODAvYXBpL2xvZ2luIiwia2luZ2RvbSI6WyJqYW5rb0hyYXNrbzIncyBraW5nZG9tIl19" +
                        ".V5AXsxmXSvigzHTbM4X2gxNnJSr3pnjugh0rMLR7TIw";
        SHORT_TOKEN =
                "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9" +
                        ".V5AXsxmXSvigzHTbM4X2gxNnJSr3pnjugh0rMLR7TIw";

        mockMvc.perform(post("/api/auth")
                        .header("Authorization", TOKEN))
                .andExpect(status().is(200));

        ID = playerRepository.findByUsername(USERNAME).getId();
        ID2 = playerRepository.findByUsername("user2").getId();
        kingdomID = playerRepository.findByUsername(USERNAME).getKingdom().getId();

        mockMvc.perform(put("/api/locationRegister")
                        .header("Authorization", TOKEN)
                        .content("""
                                {
                                    "coordinateX": "19",
                                    "coordinateY": "19",
                                    "kingdomId": "%s"
                                }""".formatted(kingdomID))
                        .contentType("application/json"))
                .andExpect(status().isOk());


    }

    private String extractToken() throws Exception {
        ResultActions result = mockMvc.perform(post("/api/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                        "username": "Tomass",
                        "password": "password"
                        }
                        """)
        ).andExpect(status().isOk());


        String resultString = result.andReturn().getResponse().getContentAsString();
        JacksonJsonParser jsonParser = new JacksonJsonParser();
        return "Bearer " + jsonParser.parseMap(resultString).get("token").toString();
    }

    // KingdomBuildings ENDPOINT testing
    @Test
    void getKingdomBuildings_successful() throws Exception {
        mockMvc.perform(get(String.format("/api/kingdoms/%d/buildings", ID))
                        .header("Authorization", TOKEN))
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.kingdom.ruler").value("Tomass"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.kingdom.kingdomName").value("Mordor"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.buildings.size()").value(1));

    }

    @Test
    void getKingdomBuildings_unsuccessful_wrongIdOfKingdom() throws Exception {
        mockMvc.perform(get("/api/kingdoms/3841/buildings")
                        .header("Authorization", TOKEN))
                .andExpect(status().is(401))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"error\":\"This kingdom does not exists!\"}"));
    }

    @Test
    void getKingdomBuildings_unsuccessful_unauthorized() throws Exception {
        mockMvc.perform(get(String.format("/api/kingdoms/%d/buildings", ID2))
                        .header("Authorization", TOKEN))
                .andExpect(status().is(401))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"error\":\"This kingdom does not belong to authenticated player!\"}"));
    }

    @Test
    void getKingdomBuildings_unsuccessful_wrongToken() throws Exception {
        mockMvc.perform(get("/api/kingdoms/2/buildings")
                        .header("Authorization", SHORT_TOKEN))
                .andExpect(status().is(403))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"error\":\"The token was expected to have 3 parts, but got 2.\"}"));
    }


    //___________________________________________________________________________________________________________________
    // UpgradeBuilding ENDPOINT testing
//    @Test
//    void upgradeBuildings_successful() throws Exception {
//        mockMvc.perform(put(String.format("/api/kingdoms/%d/buildings/1", ID))
//                        .header("Authorization", TOKEN))
//                .andExpect(status().is(200));
//
//    }


    @Test
    void upgradeBuildings_kingdomOfAnotherPlayer() throws Exception {
        mockMvc.perform(put(String.format("/api/kingdoms/%d/buildings/1", ID2))
                        .header("Authorization", TOKEN))
                .andExpect(status().is(401))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"error\":\"This kingdom does not belong to authenticated player!\"}"));
    }

    @Test
    void upgradeBuildings_kingdomDoesNotExist() throws Exception {
        mockMvc.perform(put("/api/kingdoms/3/buildings/1")
                        .header("Authorization", TOKEN))
                .andExpect(status().is(400))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"error\":\"This kingdom does not exists!\"}"));
    }

    @Test
    void upgradeBuildings_buildingDoesNotExist() throws Exception {
        mockMvc.perform(put(String.format("/api/kingdoms/%d/buildings/2", ID))
                        .header("Authorization", TOKEN))
                .andExpect(status().is(400))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"error\":\"This building does not exists!\"}"));
    }

    @Test
    void upgradeBuildings_emptyToken() throws Exception {
        mockMvc.perform(put("/api/kingdoms/1/buildings/1")
                        .header("Authorization", " "))
                .andExpect(status().is(403))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"error\":\"You are trying to enter page, that is only available for token authorized players!\"}"));

    }

    @Test
    void upgradeBuildings_wrongToken() throws Exception {
        mockMvc.perform(put("/api/kingdoms/1/buildings/1")
                        .header("Authorization", SHORT_TOKEN))
                .andExpect(status().is(403))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"error\":\"The token was expected to have 3 parts, but got 2.\"}"));

    }

}





