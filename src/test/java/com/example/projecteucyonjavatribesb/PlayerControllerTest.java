package com.example.projecteucyonjavatribesb;

import com.example.projecteucyonjavatribesb.model.Kingdom;
import com.example.projecteucyonjavatribesb.repository.KingdomRepository;
import com.example.projecteucyonjavatribesb.repository.LocationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional(propagation = Propagation.NOT_SUPPORTED)

class PlayerControllerTest {
    public Kingdom kingdom = new Kingdom();

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    @MockBean
    LocationRepository locationRepository;
    KingdomRepository mockedKingdomRepo =  Mockito.mock(KingdomRepository.class);


    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void itShouldCreateLocation() throws Exception {
        // this test should be with status OK
        Kingdom kingdom1 = new Kingdom();
        kingdom1.setId(1L);
        Optional<Kingdom> optionalKingdom = Optional.of(new Kingdom());
        Mockito.when(mockedKingdomRepo.findById(anyLong())).thenReturn(optionalKingdom);
            mockMvc.perform(put("/api/locationRegister")
                            .content("""
                                    {
                                        "coordinateY": "4",
                                        "coordinateX": "4",
                                        "kingdomId": "1"
                                    }""")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().is4xxClientError());
    }

    @Test
    void itShouldSayOutOfRange() throws Exception {
        // this test is for coordinate out of range 0-99
        mockMvc.perform(put("/api/locationRegister")
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
        // this test is for coordinate out of range 0-99
        mockMvc.perform(put("/api/locationRegister")
                        .content("""
                                {
                                    "coordinateY": "1",
                                    "coordinateX": "1",
                                    "kingdomId": "2"
                                }""")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(400));
    }
}



