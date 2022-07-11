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
                        .content("{\n" +
                                "    \"username\": \"Tom\",\n" +
                                "    \"password\": \"password123\",\n" +
                                "    \"kingdomName\": \"DisneyWorld\"\n" +
                                "}")//request body. This is JSON
                        .contentType("application/json"))//you need to specify what type of content is inside of body
                .andExpect(status().is(200));
    }

    @Test
    void shortPasswordRegistration() throws Exception {
        mockMvc.perform(post("/api/registration")
                        .content("{\n" +
                                "    \"username\": \"Tom\",\n" +
                                "    \"password\": \"pass\",\n" +
                                "    \"kingdomName\": \"DisneyWorld\"\n" +
                                "}")
                        .contentType("application/json"))
                .andExpect(status().is(400)); // expecting 400 because of short password

    }

    @Test
    void emptyUsernameRegistration() throws Exception {
        mockMvc.perform(post("/api/registration")
                        .content("{\n" +
                                "    \"username\": \"\",\n" +
                                "    \"password\": \"password\",\n" +
                                "    \"kingdomName\": \"DisneyWorld\"\n" +
                                "}")
                        .contentType("application/json"))
                .andExpect(status().is(400)); // expecting 400 because of empty username
    }

}
