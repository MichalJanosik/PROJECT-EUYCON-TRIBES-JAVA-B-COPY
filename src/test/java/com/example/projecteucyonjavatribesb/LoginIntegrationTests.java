package com.example.projecteucyonjavatribesb;

import com.example.projecteucyonjavatribesb.model.Player;
import com.example.projecteucyonjavatribesb.repository.PlayerRepository;
import com.example.projecteucyonjavatribesb.service.PlayerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest //(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoginIntegrationTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private PlayerService playerService;

    @BeforeEach
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
//        mockMvc.perform(post("/api/registration")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("""
//                                {
//                                "username": "MisoDaBadass",
//                                "password": "password"
//                                }
//                                """)
//                )
//                .andExpect(status().isOk())
//        ;

        mockMvc
                .perform(post("/api/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                "username": "MisoDaBadass",
                                "password": "password"
                                }
                                """)
                )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.status").value("ok"))
                        ;
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
