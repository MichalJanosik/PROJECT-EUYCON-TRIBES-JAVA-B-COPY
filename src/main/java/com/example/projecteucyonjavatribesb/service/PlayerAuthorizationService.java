package com.example.projecteucyonjavatribesb.service;

import com.example.projecteucyonjavatribesb.model.Kingdom;
import com.example.projecteucyonjavatribesb.repository.KingdomRepository;


public interface PlayerAuthorizationService {

    Boolean playerOwnsKingdom(Long kingdomId, String playerUsername);
    String getUsernameFromToken(String token);
    Kingdom getKingdomPreviewFromUsername(String username);
}
