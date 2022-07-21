//package com.example.projecteucyonjavatribesb.controller;
//
//import com.example.projecteucyonjavatribesb.model.Kingdom;
//import com.example.projecteucyonjavatribesb.model.Player;
//import com.example.projecteucyonjavatribesb.service.*;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//
//@ContextConfiguration(classes = {KingdomControllerIntegrationTests.class})
//@ExtendWith(SpringExtension.class)
//public class KingdomControllerAuthTest {
//
//
//    @Autowired
//    private KingdomControllerIntegrationTests kingdomController;
//
//
//    @MockBean
//    private PlayerAuthorizationService playerAuthorizationService;
//
//    @Test
//    void successfulAuthorization() throws Exception {
//        Kingdom kingdom = new Kingdom();
//        Player player = new Player();
//
//        player.setId(1L);
//        player.setKingdom(kingdom);
//        player.setKingdomName("Prague");
//        player.setPassword("password");
//        player.setUsername("Michaels");
//
//
//        kingdom.setId(1L);
//        kingdom.setPlayer(player);
//        kingdom.setRuler("Michaels");
//
//        when(playerAuthorizationService.getKingdomPreviewFromUsername((String) any())).thenReturn(kingdom);
//        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/auth")
//                .header("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJNaWNoYWVscyIsImlzcyI6Imh0dHA6Ly9sb2NhbGhvc3Q6ODA4MC9hcGkvbG9naW4iLCJraW5nZG9tIjpbIlByYWd1ZSJdfQ.ZdwrNqs8IUk6M9rCsmhd_VXo8yVl9Wz9AW_V_5ROH08");
//        MockMvcBuilders.standaloneSetup(kingdomController)
//                .build()
//                .perform(requestBuilder)
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
//                .andExpect(MockMvcResultMatchers.content()
//                        .string("{\"ruler\":\"Michaels\",\"id\":1,\"kingdomName\":\"Prague\"}"));
//
//    }
//}
