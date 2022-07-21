package com.example.projecteucyonjavatribesb.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.example.projecteucyonjavatribesb.model.DTO.KingdomDTO;
import com.example.projecteucyonjavatribesb.model.DTO.KingdomNameDTO;
import com.example.projecteucyonjavatribesb.model.DTO.LocationDTO;
import com.example.projecteucyonjavatribesb.repository.BuildingsRepository;
import com.example.projecteucyonjavatribesb.repository.KingdomRepository;
import com.example.projecteucyonjavatribesb.service.BuildingsService;
import com.example.projecteucyonjavatribesb.service.KingdomService;
import com.example.projecteucyonjavatribesb.service.PlayerAuthorizationService;
import com.example.projecteucyonjavatribesb.service.ResourcesService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {KingdomController.class})
@ExtendWith(SpringExtension.class)
class KingdomControllerTest {


    @Autowired
    private KingdomController kingdomController;


    @MockBean
    private KingdomService kingdomService;

    @MockBean
    private PlayerAuthorizationService playerAuthorizationService;



    @Test
    void testRenameKingdom() throws Exception {
        when(playerAuthorizationService.playerOwnsKingdom((String) any(), (Long) any())).thenReturn(true);
        when(kingdomService.getRenamedKingdomDTO((Long) any()))
                .thenReturn(new KingdomDTO(123L, "Kingdom Name", "Ruler", 1, new LocationDTO(1, 1)));
        doNothing().when(kingdomService).renameKingdom((Long) any(), (KingdomNameDTO) any());

        KingdomNameDTO kingdomNameDTO = new KingdomNameDTO();
        kingdomNameDTO.setKingdomName("Kingdom Name");
        String content = (new ObjectMapper()).writeValueAsString(kingdomNameDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/kingdoms/{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(kingdomController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"kingdomId\":123,\"kingdomName\":\"Kingdom Name\",\"ruler\":\"Ruler\",\"population\":1,\"location\":{\"coordinateX"
                                        + "\":1,\"coordinateY\":1}}"));
    }
}

