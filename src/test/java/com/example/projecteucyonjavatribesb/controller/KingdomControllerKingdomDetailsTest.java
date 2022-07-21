package com.example.projecteucyonjavatribesb.controller;

import com.example.projecteucyonjavatribesb.model.Player;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
class KingdomControllerKingdomDetailsTest {

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
    static String USERNAME;
    static String PASSWORD;
    static String KINGDOM_NAME;

    @BeforeAll
    void setUp() throws Exception {
        USERNAME = "Michael";
        PASSWORD = "password";
        KINGDOM_NAME = "India";

        playerService.saveNewPlayer(new Player(PASSWORD, USERNAME, KINGDOM_NAME));
        playerService.saveNewPlayer(new Player("password", "user3", "somewhere"));

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
                        "username": "Michael",
                        "password": "password"
                        }
                        """)
        ).andExpect(status().isOk());
        ID = playerRepository.findByUsername(USERNAME).getId();
        ID2 = playerRepository.findByUsername("user3").getId();


        mockMvc.perform(put("/api/locationRegister")
                        .header("Authorization", TOKEN)
                        .content("""
                                {
                                    "coordinateX": "45",
                                    "coordinateY": "45",
                                    "kingdomId": "%s"
                                }""".formatted(ID))
                        .contentType("application/json"))
                .andExpect(status().isOk());

        String resultString = result.andReturn().getResponse().getContentAsString();
        JacksonJsonParser jsonParser = new JacksonJsonParser();
        return "Bearer " + jsonParser.parseMap(resultString).get("token").toString();
    }
    @Test
    void getKingdomDetailsSuccessful() throws Exception {
        mockMvc.perform(get(String.format("/api/kingdoms/%d", ID))
                        .header("Authorization", TOKEN))
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.kingdom.ruler").value("Michael"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.kingdom.kingdomName").value("India"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.buildings.size()").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.resources.size()").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.troops.size()").value(2));

    }

    @Test
    void getKingdomDetailsWithEmptyToken() throws Exception {
        mockMvc.perform(get(String.format("/api/kingdoms/%d", ID))
                        .header("Authorization", " "))
                .andExpect(status().is(403))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"error\":\"You are trying to enter page, that is only available for token authorized players!\"}"));
    }

    @Test
    void getKingdomDetailsWithWrongId() throws Exception {
        mockMvc.perform(get("/api/kingdoms/42/")
                        .header("Authorization", TOKEN))
                .andExpect(status().is(400))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"error\":\"This kingdom does not exist.\"}"));
    }

    @Test
    void getKingdomDetailsUnauthorizedAccess() throws Exception {
        mockMvc.perform(get(String.format("/api/kingdoms/%d", ID2))
                        .header("Authorization", TOKEN))
                .andExpect(status().is(401))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"error\":\"This kingdom does not belong to authenticated player!\"}"));
    }
}