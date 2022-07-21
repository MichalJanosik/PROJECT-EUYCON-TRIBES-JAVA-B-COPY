package com.example.projecteucyonjavatribesb.projecteucyonjavatribesb.controller;

import com.example.projecteucyonjavatribesb.model.Kingdom;
import com.example.projecteucyonjavatribesb.repository.KingdomRepository;
import com.example.projecteucyonjavatribesb.repository.LocationRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
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

    @BeforeAll
    void setup() throws Exception {
        mockMvc.perform(post("/api/registration")
                        .content("""
                                {
                                    "username": "adam001",
                                    "password": "password123",
                                    "kingdomName": "Discovery Channels"
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
    }


    String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZGFtMDAyIiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdC9hcGkvbG9naW4iLCJraW5nZG9tIjpbIkRpc2NvdmVyeSBDaGFubmVscyJdfQ.coJf0LTlpMQQCRTz9q3nzYcbP3iOZU1TBOPYOI5FvWU";



    @Test
    void itShouldCreateLocation() throws Exception {
        // this test should be with status OK
        mockMvc.perform(put("/api/locationRegister")
                        .header("Authorization", token)
                        .content("""
                                {
                                    "coordinateY": "1",
                                    "coordinateX": "1",
                                    "kingdomId": "1"
                                }""")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void itShouldSayOutOfRange() throws Exception {
        // this test is for coordinate out of range 0-99
        mockMvc.perform(put("/api/locationRegister")
                        .header("Authorization", token)
                        .content("""
                                {
                                    "coordinateY": "224",
                                    "coordinateX": "4",
                                    "kingdomId": "1"
                                }""")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(400));
    }

    @Test
    void itShouldSayAlreadyTaken() throws Exception {
        mockMvc.perform(put("/api/locationRegister")
                        .content("""
                                {
                                    "coordinateY": "1",
                                    "coordinateX": "1",
                                    "kingdomId": "1"
                                }""")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(400));
    }
}


