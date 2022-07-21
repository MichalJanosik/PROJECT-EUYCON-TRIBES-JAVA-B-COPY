package com.example.projecteucyonjavatribesb.projecteucyonjavatribesb.controller;

import com.example.projecteucyonjavatribesb.model.Kingdom;
import com.example.projecteucyonjavatribesb.model.Player;
import com.example.projecteucyonjavatribesb.repository.KingdomRepository;
import com.example.projecteucyonjavatribesb.repository.LocationRepository;
import com.example.projecteucyonjavatribesb.repository.PlayerRepository;
import com.example.projecteucyonjavatribesb.service.PlayerService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PlayerControllerLocationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private PlayerService playerService;

    static String TOKEN;
    static String INCORRECT_TOKEN;
    static Long ID;
    static String USERNAME;
    static String PASSWORD;
    static String KINGDOM_NAME;

    @BeforeAll
    void setup() throws Exception {
        USERNAME = "Adam007";
        PASSWORD = "password";
        KINGDOM_NAME = "Moria";

        playerService.saveNewPlayer(new Player(PASSWORD, USERNAME, KINGDOM_NAME));
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
                        "username": "Adam007",
                        "password": "password"
                        }
                        """)
        ).andExpect(status().isOk());

        String resultString = result.andReturn().getResponse().getContentAsString();
        JacksonJsonParser jsonParser = new JacksonJsonParser();
        return "Bearer " + jsonParser.parseMap(resultString).get("token").toString();
    }


    @Test
    void itShouldCreateLocation() throws Exception {
        ID = playerRepository.findByUsername(USERNAME).getId();
        // this test should be with status OK
        mockMvc.perform(put("/api/locationRegister")
                        .header("Authorization", TOKEN)
                        .content("""
                                {
                                    "coordinateX": "19",
                                    "coordinateY": "19",
                                    "kingdomId": "%s"
                                }""".formatted(ID))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void itShouldSayOutOfRange() throws Exception {
        ID = playerRepository.findByUsername(USERNAME).getId();
        // this test is for coordinate out of range 0-99
        mockMvc.perform(put("/api/locationRegister")
                        .header("Authorization", TOKEN)
                        .content("""
                                {
                                    "coordinateX": "11111",
                                    "coordinateY": "11111",
                                    "kingdomId": "%s"
                                }""".formatted(ID))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(400));
    }


}

