package com.example.projecteucyonjavatribesb.controller;

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
class KingdomControllerTroopsTest {


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
    static Long kingdomID;
    static Long ID2;
    static String USERNAME;
    static String PASSWORD;
    static String KINGDOM_NAME;

    @BeforeAll
    void setUp() throws Exception {
        USERNAME = "MichaelAgain";
        PASSWORD = "password";
        KINGDOM_NAME = "AvengersAcademy";

        playerService.saveNewPlayer(new Player(PASSWORD, USERNAME, KINGDOM_NAME));
        playerService.saveNewPlayer(new Player("password", "user5", "badBoy"));

        TOKEN = extractToken();
        INCORRECT_TOKEN =
                "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9" +
                        ".eyJzdWIiOiJqYW5rb0hyYXNrbzIiLCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgwODAvYXBpL2xvZ2luIiwia2luZ2RvbSI6WyJqYW5rb0hyYXNrbzIncyBraW5nZG9tIl19" +
                        ".V5AXsxmXSvigzHTbM4X2gxNnJSr3pnjugh0rMLR7TIw";
        SHORT_TOKEN =
                "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9" +
                        ".V5AXsxmXSvigzHTbM4X2gxNnJSr3pnjugh0rMLR7TIw";

    }
    private String extractToken() throws Exception {
        ResultActions result = mockMvc.perform(post("/api/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                        "username": "MichaelAgain",
                        "password": "password"
                        }
                        """)
        ).andExpect(status().isOk());
        ID = playerRepository.findByUsername(USERNAME).getId();
        kingdomID = playerRepository.findByUsername(USERNAME).getKingdom().getId();
        ID2 = playerRepository.findByUsername("user5").getId();


        mockMvc.perform(put("/api/locationRegister")
                        .header("Authorization", TOKEN)
                        .content("""
                                {
                                    "coordinateX": "64",
                                    "coordinateY": "64",
                                    "kingdomId": "%s"
                                }""".formatted(kingdomID))
                        .contentType("application/json"))
                .andExpect(status().isOk());

        String resultString = result.andReturn().getResponse().getContentAsString();
        JacksonJsonParser jsonParser = new JacksonJsonParser();
        return "Bearer " + jsonParser.parseMap(resultString).get("token").toString();
    }
    @Test
    void getKingdomTroopsSuccessful() throws Exception {
        mockMvc.perform(get(String.format("/api/kingdoms/%d/troops", ID))
                        .header("Authorization", TOKEN))
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.kingdom.ruler").value("MichaelAgain"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.kingdom.kingdomName").value("AvengersAcademy"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.kingdom.population").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.troops.size()").value(2));

    }

    @Test
    void getKingdomTroopsWithEmptyToken() throws Exception {
        mockMvc.perform(get(String.format("/api/kingdoms/%d/troops", ID))
                        .header("Authorization", " "))
                .andExpect(status().is(403))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"error\":\"You are trying to enter page, that is only available for token authorized players!\"}"));
    }

    @Test
    void getKingdomTroopsWithWrongId() throws Exception {
        mockMvc.perform(get("/api/kingdoms/42/troops")
                        .header("Authorization", TOKEN))
                .andExpect(status().is(404))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"error\":\"This kingdom does not exist.\"}"));
    }

    @Test
    void getKingdomTroopsUnauthorizedAccess() throws Exception {
        mockMvc.perform(get(String.format("/api/kingdoms/%d/troops", ID2))
                        .header("Authorization", TOKEN))
                .andExpect(status().is(401))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"error\":\"This kingdom does not belong to authenticated player!\"}"));
    }
}