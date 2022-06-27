package com.example.projecteucyonjavatribesb.service;


public interface PlayerService {

import com.example.projecteucyonjavatribesb.model.Player;

public interface PlayerService {
    boolean checkIfUsernameAlreadyExist(String username);

    void saveNewPlayerWithDefaultKingdomName(Player player);

    void saveNewPlayer(Player player);



}
