package com.example.projecteucyonjavatribesb.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.projecteucyonjavatribesb.filter.JwtRequestFilter;
import com.example.projecteucyonjavatribesb.model.Kingdom;
import com.example.projecteucyonjavatribesb.repository.KingdomRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.projecteucyonjavatribesb.repository.KingdomRepository;

@Service
@AllArgsConstructor
public class PlayerAuthorizationServiceImpl implements PlayerAuthorizationService {

    private final KingdomRepository kingdomRepository;
    private final JwtRequestFilter jwtRequestFilter;

    @Override
    public Boolean playerOwnsKingdom(Long kingdomId, String playerUsername) {
        return kingdomRepository.findKingdomByRulerAndId(kingdomId, playerUsername).isPresent();
    }

    @Override
    public String getUsernameFromToken(String token) {
        return jwtRequestFilter.getUsername(token);
    }

    @Override
    public Kingdom getKingdomPreviewFromUsername(String username) {
        return kingdomRepository.findKingdomByRuler(username);
    }


}
