package com.example.projecteucyonjavatribesb.service;

import com.example.projecteucyonjavatribesb.model.Player;
import com.example.projecteucyonjavatribesb.repository.PlayerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PlayerServiceTest {


    @Test
    public void checkIfUsernameAlreadyExist_success() {
        // Arange
        Player player1 = new Player();
        player1.setUsername("Adam001");
        player1.setPassword("password123");
        player1.setKingdomName("Mordor");
        List<Player> playerList = new ArrayList<>();
        playerList.add(player1);

        PlayerRepository mockedPlayerRepository = Mockito.mock(PlayerRepository.class);
        Mockito.when(mockedPlayerRepository.findAllByUsername(player1.getUsername())).thenReturn(playerList);

        // Act
        List<Player> result = mockedPlayerRepository.findAllByUsername(player1.getUsername());

        // Assert
        assertFalse(result.size() == 0);
        assertTrue(result.size() == 1);
    }


}

