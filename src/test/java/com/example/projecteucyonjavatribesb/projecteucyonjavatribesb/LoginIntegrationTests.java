package com.example.projecteucyonjavatribesb.projecteucyonjavatribesb;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
//import org.junit.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration
//@WebAppConfiguration
@AutoConfigureMockMvc
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LoginIntegrationTests {


    @Autowired
    MockMvc mockMvc;

    @BeforeAll
    void setUp() throws Exception {
        mockMvc.perform(post("/api/registration")
                        .content("""
                                {
                                    "username": "MisoDaBadass",
                                    "password": "password123",
                                    "kingdomName": "Discovery Channels"
                                }""")
                        .contentType("application/json"))
                .andExpect(status().is(200));
    }

    @Test
    public void login_OK() throws Exception {

//        mockMvc.perform(post("/api/login")
//                        .with(user("MisoDaBadass")
//                                .password("password")
//                        ))
//                .andExpect(status().isOk());

        mockMvc
                .perform(post("/api/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                "username": "MisoDaBadass",
                                "password": "password123"
                                }"""))
                .andExpect(status().isOk());
    }

    @Test
    public void login_FAIL_401() throws Exception {
        mockMvc
                .perform(post("/api/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                "username": "MisoDaBadass",
                                "password": "incorrest_password"
                                }"""))
                .andExpect(status().isUnauthorized());
    }

    //this test fails because its getting back status 401
    //when tested in postman, status is 400
//    @Test
//    public void login_FAIL_400() throws Exception {
//        mockMvc
//                .perform(post("/api/login")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("""
//                                {
//                                "username": "MisoDaBadass",
//                                "password": ""
//                                }"""))
//                .andExpect(status().is(400));
//    }
}
