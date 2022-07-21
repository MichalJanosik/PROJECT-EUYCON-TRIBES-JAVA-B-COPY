package com.example.projecteucyonjavatribesb.controller;

import com.example.projecteucyonjavatribesb.model.DTO.KingdomDetailsDTO;
import com.example.projecteucyonjavatribesb.model.Kingdom;
import com.example.projecteucyonjavatribesb.model.Player;
import com.example.projecteucyonjavatribesb.service.BuildingsService;
import com.example.projecteucyonjavatribesb.service.KingdomService;
import com.example.projecteucyonjavatribesb.service.PlayerAuthorizationService;
import com.example.projecteucyonjavatribesb.service.ResourcesService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {KingdomControllerIntegrationTests.class})

//@ContextConfiguration(classes = {KingdomController.class})
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class KingdomControllerKingdomDetailsTest {

    @Autowired
    MockMvc mockMvc;

    @BeforeAll
    void setUp() throws Exception {
        mockMvc.perform(post("/api/registration")
                        .content("""
                                {
                                    "username": "Michael",
                                    "password": "password",
                                    "kingdomName": "Moria"
                                }""")
                        .contentType("application/json"))
                .andExpect(status().is(200));

        mockMvc.perform(post("/api/registration")
                        .content("""
                                {
                                    "username": "David",
                                    "password": "password",
                                    "kingdomName": "Gondor"
                                }""")
                        .contentType("application/json"))
                .andExpect(status().is(200));

        mockMvc.perform(post("/api/login")
                        .content("""
                                {
                                    "username": "Michael",
                                    "password": "password"
                                }""")
                        .contentType("application/json"))
                .andExpect(status().is(200));

        mockMvc.perform(put("/api/locationRegister")
                        .content("""
                                {
                                    "coordinateX": "50",
                                    "coordinateY": "50",
                                    "kingdomId": "1"
                                }""")
                        .contentType("application/json"))
                .andExpect(status().is(200));
    }

    String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJNaWNoYWVsIiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MDgwL2FwaS9sb2dpbiIsImtpbmdkb20iOlsiTW9yaWEiXX0.RpYSLXLhzmLuzynJ5rZ1gaok1KchjX424kU0deujD14";

    @Test
    void getKingdomDetailsSuccessful() throws Exception {
        mockMvc.perform(get("/api/kingdoms/1/")
                        .header("Authorization", token))
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.kingdom.ruler").value("Michael"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.kingdom.kingdomName").value("Moria"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.buildings.size()").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.resources.size()").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.troops.size()").value(2));

    }

    @Test
    void getKingdomDetailsWithEmptyToken() throws Exception {
        mockMvc.perform(get("/api/kingdoms/1/")
                        .header("Authorization", " "))
                .andExpect(status().is(403))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"error\":\"You are trying to enter page, that is only available for token authorized players!\"}"));
    }

    @Test
    void getKingdomDetailsWithWrongId() throws Exception {
        mockMvc.perform(get("/api/kingdoms/42/")
                        .header("Authorization", token))
                .andExpect(status().is(400))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"error\":\"This kingdom does not exist.\"}"));
    }

    @Test
    void getKingdomDetailsUnauthorizedAccess() throws Exception {
        mockMvc.perform(get("/api/kingdoms/2/")
                        .header("Authorization", token))
                .andExpect(status().is(401))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"error\":\"This kingdom does not belong to authenticated player!\"}"));
    }
}