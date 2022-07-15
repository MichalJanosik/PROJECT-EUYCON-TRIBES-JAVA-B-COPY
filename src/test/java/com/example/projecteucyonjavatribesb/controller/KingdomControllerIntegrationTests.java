package com.example.projecteucyonjavatribesb.controller;

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
    static Long ID;
    static String USERNAME;
    static String PASSWORD;
    static String KINGDOM_NAME;

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
                        """)
        ).andExpect(status().isOk());

        ID = playerRepository.findByUsername(USERNAME).getId();

        mockMvc.perform(put("/api/locationRegister")
                        .content("""
                                {
                                    "coordinateX": "1",
                                    "coordinateY": "1",
                                    "kingdomId": "%s"
                                }""".formatted(ID))
                        .contentType("application/json"))
                .andExpect(status().isOk());

        String resultString = result.andReturn().getResponse().getContentAsString();
        JacksonJsonParser jsonParser = new JacksonJsonParser();
        return "Bearer " + jsonParser.parseMap(resultString).get("token").toString();
    }

    //>>>>>>>>>>>>GET /api/kingdoms/{id}/resources >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    @Test
    void testGetKingdomResources_OK_200() throws Exception {
        String expectKingdomName = playerRepository.findByUsername(USERNAME).getKingdomName();
        ID = playerRepository.findByUsername(USERNAME).getId();

        mockMvc
                .perform(get(String.format("/api/kingdoms/%d/resources", ID))
                        .header("Authorization", TOKEN))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.kingdom.kingdomName").value(expectKingdomName));
    }

    @Test
    void testGetKingdomResourcesIncorrectToken_UNAUTHORIZED_401() throws Exception {
        String expectError = "This kingdom does not belong to authenticated player!";

        mockMvc
                .perform(get(String.format("/api/kingdoms/%d/resources", ID))
                        .header("Authorization", INCORRECT_TOKEN))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.error").value(expectError));
    }

    @Test
    void testGetKingdomResourcesWrongId_UNAUTHORIZED_401() throws Exception {
        String expectError = "This kingdom does not belong to authenticated player!";

        mockMvc
                .perform(get(String.format("/api/kingdoms/%d/resources", ++ID))
                        .header("Authorization", INCORRECT_TOKEN))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.error").value(expectError));
    }

    //>>>>>>>>>>>>PUT /api/{id} >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    @Test
    void testRenameKingdom_OK_200() throws Exception {
        String expectKingdomName = "Galaxy even more far away";
        ID = playerRepository.findByUsername(USERNAME).getId();

        mockMvc
                .perform(put(String.format("/api/kingdoms/%d", ID))
                        .header("Authorization", TOKEN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "kingdomName": "%s"
                                }""".formatted(expectKingdomName)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.kingdomName").value(expectKingdomName))
                .andExpect(jsonPath("$.kingdomId").value(ID));
    }

    @Test
    void testRenameKingdomIncorrectToken_UNAUTHORIZED_401() throws Exception {
        String expectKingdomName = "Galaxy even more far away";
        String expectError = "This kingdom does not belong to authenticated player!";
        ID = playerRepository.findByUsername(USERNAME).getId();

        mockMvc
                .perform(put(String.format("/api/kingdoms/%d", ID))
                        .header("Authorization", INCORRECT_TOKEN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "kingdomName": "%s"
                                }""".formatted(expectKingdomName)))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.error").value(expectError));
    }

    @Test
    void testRenameKingdomWrondId_UNAUTHORIZED_401() throws Exception {
        String expectKingdomName = "Galaxy even more far away";
        String expectError = "This kingdom does not belong to authenticated player!";
        ID = playerRepository.findByUsername(USERNAME).getId();

        mockMvc
                .perform(put(String.format("/api/kingdoms/%d", ++ID))
                        .header("Authorization", TOKEN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "kingdomName": "%s"
                                }""".formatted(expectKingdomName)))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.error").value(expectError));
    }

    @Test
    void testRenameKingdomEmptyKingdomName_BAD_REQUEST_400() throws Exception {
        String expectError = "Field kingdomName was empty!";
        ID = playerRepository.findByUsername(USERNAME).getId();

        mockMvc
                .perform(put(String.format("/api/kingdoms/%d", ID))
                        .header("Authorization", TOKEN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "kingdomName": ""
                                }"""))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value(expectError));
    }
}
