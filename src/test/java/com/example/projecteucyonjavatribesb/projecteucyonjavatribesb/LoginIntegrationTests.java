package com.example.projecteucyonjavatribesb.projecteucyonjavatribesb;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
//import org.junit.Test;
import com.example.projecteucyonjavatribesb.model.Player;
import com.example.projecteucyonjavatribesb.repository.PlayerRepository;
import com.example.projecteucyonjavatribesb.service.PlayerService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LoginIntegrationTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private PlayerService playerService;

    @BeforeAll
    void initialSetup() {
        playerRepository.deleteAll();
        playerService.saveNewPlayer(new Player(
                "password",
                "MisoDaBadass",
                "Mordor")
        );
    }

    @Test
    public void testLogin_OK_200() throws Exception {
        mockMvc
                .perform(post("/api/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                "username": "MisoDaBadass",
                                "password": "password"
                                }"""))
                .andExpect(status().isOk());

    }

    @Test
    public void testLogin_FAIL_401() throws Exception {
        mockMvc
                .perform(post("/api/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                "username": "MisoDaBadass",
                                "password": "incorrect_password"
                                }
                                """)
                )
                .andExpect(status().isUnauthorized());
    }
}