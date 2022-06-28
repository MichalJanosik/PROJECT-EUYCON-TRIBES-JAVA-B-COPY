package com.example.projecteucyonjavatribesb.service;

import com.example.projecteucyonjavatribesb.model.Player;

public interface PlayerService {
    boolean checkIfUsernameAlreadyExist(String username);

    void saveNewPlayer(Player player);


}
