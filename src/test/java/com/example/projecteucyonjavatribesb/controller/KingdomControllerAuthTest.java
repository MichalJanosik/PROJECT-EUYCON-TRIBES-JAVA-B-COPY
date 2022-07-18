package com.example.projecteucyonjavatribesb.controller;

import com.example.projecteucyonjavatribesb.model.Kingdom;
import com.example.projecteucyonjavatribesb.model.Player;
import com.example.projecteucyonjavatribesb.service.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {KingdomController.class})
@ExtendWith(SpringExtension.class)
public class KingdomControllerAuthTest {

    @MockBean
    private BuildingsService buildingsService;

    @Autowired
    private KingdomController kingdomController;

    @MockBean
    private KingdomService kingdomService;

    @MockBean
    private PlayerAuthorizationService playerAuthorizationService;

    @MockBean
    private ResourcesService resourcesService;

    @Test
    void successfulAuthorization() throws Exception {
        Kingdom kingdom = new Kingdom();
        Player player = new Player();

        player.setId(1L);
        player.setKingdom(kingdom);
        player.setKingdomName("Moria");
        player.setPassword("password");
        player.setUsername("Michael");


        kingdom.setId(1L);
        kingdom.setPlayer(player);
        kingdom.setRuler("Michael");

        when(playerAuthorizationService.getKingdomPreviewFromUsername((String) any())).thenReturn(kingdom);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/auth")
                .header("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJNaWNoYWVsIiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MDgwL2FwaS9sb2dpbiIsImtpbmdkb20iOlsiTW9yaWEiXX0.RpYSLXLhzmLuzynJ5rZ1gaok1KchjX424kU0deujD14");
        MockMvcBuilders.standaloneSetup(kingdomController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"ruler\":\"Michael\",\"id\":1,\"kingdomName\":\"Moria\"}"));
    }
}
