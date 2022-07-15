package com.example.projecteucyonjavatribesb.projecteucyonjavatribesb.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class PlayerControllerTest {

    @Autowired
    MockMvc mockMvc;




    @Test
    void successfulRegistration() throws Exception {
        mockMvc.perform(post("/api/registration")// perform action inside POST,GET,DELETE + url. Parameters or path variable can be specified inside. It is string so ""+variable+"" can be used.
                        .content("""
                                {
                                    "username": "Thomas",
                                    "password": "password123",
                                    "kingdomName": "DisneyWorld"
                                }""")
                        .contentType("application/json"))//you need to specify what type of content is inside of body
                .andExpect(status().is(200));
    }

    @Test
    void shortPasswordRegistration() throws Exception {
        mockMvc.perform(post("/api/registration")
                        .content("""
                                {
                                    "username": "Tomas",
                                    "password": "pass",
                                    "kingdomName": "Kingdom"
                                }""")
                        .contentType("application/json"))
                .andExpect(status().is(400)); // expecting 400 because of short password

    }

    @Test
    void emptyUsernameRegistration() throws Exception {
        mockMvc.perform(post("/api/registration")
                        .content("""
                                {
                                    "username": " ",
                                    "password": "password",
                                    "kingdomName": "Aquapark"
                                }""")
                        .contentType("application/json"))
                .andExpect(status().is(400)); // expecting 400 because of empty username
    }

}
