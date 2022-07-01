package com.example.projecteucyonjavatribesb.service;

import com.example.projecteucyonjavatribesb.model.Kingdom;
import com.example.projecteucyonjavatribesb.repository.KingdomRepository;


public interface PlayerAuthorizationService {

    Boolean playerOwnsKingdom(String playerUsername, Long kingdomId);
    Kingdom getKingdomPreviewFromUsername(String username);
}
