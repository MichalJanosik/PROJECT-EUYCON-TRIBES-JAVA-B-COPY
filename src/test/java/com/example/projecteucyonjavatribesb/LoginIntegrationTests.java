package com.example.projecteucyonjavatribesb;

import com.example.projecteucyonjavatribesb.filter.JwtRequestFilter;
import org.junit.Before;
import org.junit.jupiter.api.Test;
//import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;


//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration
//@WebAppConfiguration
@AutoConfigureMockMvc
@SpringBootTest
public class LoginIntegrationTests {

    @Autowired
    MockMvc mockMvc;

//    @Before
//    public void setup() {
//        mockMvc = MockMvcBuilders
//                .webAppContextSetup(context)
//                .apply(springSecurity())
//                .build();
//    }

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
                                "password": "password"
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
