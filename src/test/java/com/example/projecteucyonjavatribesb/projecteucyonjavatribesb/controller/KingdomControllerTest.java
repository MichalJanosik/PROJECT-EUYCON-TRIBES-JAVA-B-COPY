package com.example.projecteucyonjavatribesb.projecteucyonjavatribesb.controller;

import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class KingdomControllerTest {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    MockMvc mockMvc;


    @BeforeEach
    void setUp() throws Exception {
        mockMvc.perform(post("/api/registration")
                        .content("""
                                {
                                    "username": "adam002",
                                    "password": "password123",
                                    "kingdomName": "Discovery Channels"
                                }""")
                        .contentType("application/json"))
                .andExpect(status().is(200));

        mockMvc.perform(post("/api/registration")
                        .content("""
                                {
                                    "username": "Tom",
                                    "password": "password123",
                                    "kingdomName": "Mordor"
                                }""")
                        .contentType("application/json"))
                .andExpect(status().is(200));

        mockMvc.perform(post("/api/login")
                        .content("""
                                {
                                    "username": "adam002",
                                    "password": "password123"
                                }""")
                        .contentType("application/json"))
                .andExpect(status().is(200));

        mockMvc.perform(put("/api/locationRegister")
                        .content("""
                                {
                                    "coordinateX": "10",
                                    "coordinateY": "10",
                                    "kingdomId": "1"
                                }""")
                        .contentType("application/json"))
                .andExpect(status().is(200));
    }

//    @AfterEach
//    void afterEach() {
//        JdbcTestUtils.deleteFromTables(jdbcTemplate, "tribes.player", "tribes.kingdom",
//                "tribes.buildings", "tribes.location");
//    }

    // We know from postman, that this is the token for registered player: adam002
    String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZGFtMDAyIiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdC9hcGkvbG9naW4iLCJraW5nZG9tIjpbIkRpc2NvdmVyeSBDaGFubmVscyJdfQ.coJf0LTlpMQQCRTz9q3nzYcbP3iOZU1TBOPYOI5FvWU";
    // token with just 2 parts
    String wrongToken = "Bearer eyJzdWIiOiJhZGFtMDAyIiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdC9hcGkvbG9naW4iLCJraW5nZG9tIjpbIkRpc2NvdmVyeSBDaGFubmVscyJdfQ.coJf0LTlpMQQCRTz9q3nzYcbP3iOZU1TBOPYOI5FvWU";

    // KingdomBuildings ENDPOINT testing
    @Test
    void getKingdomBuildings_successful() throws Exception {
        mockMvc.perform(get("/api/kingdoms/1/buildings")
                        .header("Authorization", token))
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.kingdom.ruler").value("adam002"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.kingdom.kingdomName").value("Discovery Channels"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.buildings.size()").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.buildings[0].level").value(1));

    }

    @Test
    void getKingdomBuildings_unsuccessful_emptyToken() throws Exception {
        mockMvc.perform(get("/api/kingdoms/1/buildings")
                        .header("Authorization", " "))
                .andExpect(status().is(403))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"error\":\"You are trying to enter page, that is only available for token authorized players!\"}"));
    }

    @Test
    void getKingdomBuildings_unsuccessful_wrongIdOfKingdom() throws Exception {
        mockMvc.perform(get("/api/kingdoms/3841/buildings")
                        .header("Authorization", token))
                .andExpect(status().is(401))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"error\":\"This kingdom does not exists!\"}"));
    }

    @Test
    void getKingdomBuildings_unsuccessful_unauthorized() throws Exception {
        mockMvc.perform(get("/api/kingdoms/2/buildings")
                        .header("Authorization", token))
                .andExpect(status().is(401))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"error\":\"This kingdom does not belong to authenticated player!\"}"));
    }

    @Test
    void getKingdomBuildings_unsuccessful_wrongToken() throws Exception {
        mockMvc.perform(get("/api/kingdoms/2/buildings")
                        .header("Authorization", wrongToken))
                .andExpect(status().is(403))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"error\":\"The token was expected to have 3 parts, but got 2.\"}"));
    }


    //___________________________________________________________________________________________________________________
    // UpgradeBuilding ENDPOINT testing
    @Test
    void upgradeBuildings_successful() throws Exception {
        mockMvc.perform(put("/api/kingdoms/1/buildings/1")
                        .header("Authorization", token))
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.type").value("Town Hall"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.level").value(2));
    }

    @Test
    void upgradeBuildings_previousUpgradeIsNotFinished() throws Exception {
        mockMvc.perform(put("/api/kingdoms/1/buildings/1")
                        .header("Authorization", token))
                .andExpect(status().is(200));
        mockMvc.perform(put("/api/kingdoms/1/buildings/1")
                        .header("Authorization", token))
                .andExpect(status().is(400))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"error\":\"Building is not ready for reconstruction!\"}"));
    }

    @Test
    void upgradeBuildings_kingdomOfAnotherPlayer() throws Exception {
        mockMvc.perform(put("/api/kingdoms/2/buildings/1")
                        .header("Authorization", token))
                .andExpect(status().is(401))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"error\":\"This kingdom does not belong to authenticated player!\"}"));
    }

    @Test
    void upgradeBuildings_kingdomDoesNotExist() throws Exception {
        mockMvc.perform(put("/api/kingdoms/3/buildings/1")
                        .header("Authorization", token))
                .andExpect(status().is(400))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"error\":\"This kingdom does not exists!\"}"));
    }

    @Test
    void upgradeBuildings_buildingDoesNotExist() throws Exception {
        mockMvc.perform(put("/api/kingdoms/1/buildings/2")
                        .header("Authorization", token))
                .andExpect(status().is(400))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"error\":\"This building does not exists!\"}"));
    }

    @Test
    void upgradeBuildings_emptyToken() throws Exception {
        mockMvc.perform(get("/api/kingdoms/1/buildings")
                        .header("Authorization", " "))
                .andExpect(status().is(403))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"error\":\"You are trying to enter page, that is only available for token authorized players!\"}"));

    }

    @Test
    void upgradeBuildings_wrongToken() throws Exception {
        mockMvc.perform(get("/api/kingdoms/1/buildings")
                        .header("Authorization", wrongToken))
                .andExpect(status().is(403))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"error\":\"The token was expected to have 3 parts, but got 2.\"}"));

    }


}





