package com.example.projecteucyonjavatribesb.service;

import com.example.projecteucyonjavatribesb.model.Kingdom;


public interface PlayerAuthorizationService {

    Boolean playerOwnsKingdom(String playerUsername, Long kingdomId);
    Kingdom getKingdomPreviewFromUsername(String username);
}
