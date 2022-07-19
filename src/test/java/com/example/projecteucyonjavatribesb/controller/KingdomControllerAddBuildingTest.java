package com.example.projecteucyonjavatribesb.controller;

import com.example.projecteucyonjavatribesb.model.DTO.BuildingRequestDTO;
import com.example.projecteucyonjavatribesb.model.Kingdom;
import com.example.projecteucyonjavatribesb.model.Player;
import com.example.projecteucyonjavatribesb.repository.KingdomRepository;
import com.example.projecteucyonjavatribesb.repository.PlayerRepository;
import com.example.projecteucyonjavatribesb.service.BuildingsServiceImpl;
import com.example.projecteucyonjavatribesb.service.KingdomServiceImpl;
import com.example.projecteucyonjavatribesb.service.PlayerService;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
//import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpFilter;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

import java.util.Optional;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)

class KingdomControllerAddBuildingTest extends HttpFilter {

    @Autowired
    PlayerRepository playerRepository;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    private PlayerService playerService;
    @Autowired
    WebApplicationContext webApplicationContext;
    static String TOKEN;
    static String INCORRECT_TOKEN;
    static Long ID;
    static String USERNAME;
    static String PASSWORD;
    static String KINGDOM_NAME;

@Before
public void setup() {
    mockMvc = MockMvcBuilders
            .webAppContextSetup(webApplicationContext)
            .apply(springSecurity()) // enable security for the mock set up
            .build();
}

    @BeforeAll
    void initialSetup() throws Exception {
        USERNAME = "MisoDaJedi";
        PASSWORD = "password";
        KINGDOM_NAME = "Galaxy far far away";

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
                        "username": "MisoDaJedi",
                        "password": "password"
                        }
                        """));

        ID = playerRepository.findByUsername(USERNAME).getId();
        mockMvc.perform(put("/api/locationRegister")
                        .content("""
                                {
                                    "coordinateX": "1",
                                    "coordinateY": "1",
                                    "kingdomId": "%s"
                                }""".formatted(ID))
                        .contentType("application/json"));

        String resultString = result.andReturn().getResponse().getContentAsString();
        JacksonJsonParser jsonParser = new JacksonJsonParser();
        return "Bearer " + jsonParser.parseMap(resultString).get("token").toString();
    }

    @WithMockUser(value = "test", password = "pass")
    @Test
    void testAddBuilding2() throws Exception {
//        Ok test
        mockMvc.perform(post("/api/kingdoms/1/buildings")
                        .content("{\"type\":\"farm\"}").contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization",TOKEN))
                .andExpect(jsonPath("$.type").value("farm")).andExpect(status().isOk());
    }

    @Test
    void testAddBuilding3() throws Exception {
//        Test with missing headers
        mockMvc.perform(post("/api/kingdoms/1/buildings")
                        .content("{\"type\":\"farm\"}").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }
}

