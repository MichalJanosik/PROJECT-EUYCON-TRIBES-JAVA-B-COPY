package com.example.projecteucyonjavatribesb.controller;

import com.example.projecteucyonjavatribesb.model.Player;
import com.example.projecteucyonjavatribesb.repository.PlayerRepository;
import com.example.projecteucyonjavatribesb.service.PlayerService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class KingdomControllerIntegrationTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private PlayerRepository playerRepository;

    static String TOKEN;
    static String INCORRECT_TOKEN;
    static Long id;

    @BeforeAll
    void initialSetup() throws Exception {
        String username = "MisoDaJedi";
        String password = "password";
        String kingdomName = "Galaxy far far away";

        playerService.saveNewPlayer(new Player(password, username, kingdomName));

        TOKEN = extractToken();
        INCORRECT_TOKEN =
                "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9" +
                        ".eyJzdWIiOiJqYW5rb0hyYXNrbzIiLCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgwODAvYXBpL2xvZ2luIiwia2luZ2RvbSI6WyJqYW5rb0hyYXNrbzIncyBraW5nZG9tIl19" +
                        ".V5AXsxmXSvigzHTbM4X2gxNnJSr3pnjugh0rMLR7TIw";
    }

    private String extractToken() throws Exception {
        ResultActions result = mockMvc.perform(post("/api/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                        "username": "MisoDaJedi",
                        "password": "password"
                        }
                        """)
        ).andExpect(status().isOk());

        id = playerRepository.findByUsername("MisoDaJedi").getId();

        mockMvc.perform(put("/api/locationRegister")
                        .content("""
                                {
                                    "coordinateX": "1",
                                    "coordinateY": "1",
                                    "kingdomId": "%s"
                                }""".formatted(id))
                        .contentType("application/json"))
                .andExpect(status().isOk());

        String resultString = result.andReturn().getResponse().getContentAsString();
        JacksonJsonParser jsonParser = new JacksonJsonParser();
        return "Bearer " + jsonParser.parseMap(resultString).get("token").toString();
    }

    @Test
    void testGetKingdomResources_OK_200() throws Exception {
        String ExpectKingdomName = playerRepository.findByUsername("MisoDaJedi").getKingdomName();
        id = playerRepository.findByUsername("MisoDaJedi").getId();

        mockMvc
                .perform(get(String.format("/api/kingdoms/%d/resources", id))
                        .header("Authorization", TOKEN))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.kingdom.kingdomName").value(ExpectKingdomName))
        ;
    }

    @Test
    void testGetKingdomResourcesWrongToken_UNAUTHORIZED_401() throws Exception {
        String error = "This kingdom does not belong to authenticated player!";

        mockMvc
                .perform(get(String.format("/api/kingdoms/%d/resources", id))
                        .header("Authorization", INCORRECT_TOKEN))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.error").value(error))
        ;
    }

    @Test
    void testGetKingdomResourcesWrongId_UNAUTHORIZED_401() throws Exception {
        String error = "This kingdom does not belong to authenticated player!";

        mockMvc
                .perform(get(String.format("/api/kingdoms/%d/resources", ++id))
                        .header("Authorization", INCORRECT_TOKEN))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.error").value(error))
        ;
    }

}
