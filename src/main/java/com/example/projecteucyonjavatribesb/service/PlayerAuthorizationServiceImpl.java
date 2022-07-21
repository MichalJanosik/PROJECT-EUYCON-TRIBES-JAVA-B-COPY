package com.example.projecteucyonjavatribesb.service;

import com.example.projecteucyonjavatribesb.model.Kingdom;
import com.example.projecteucyonjavatribesb.repository.KingdomRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PlayerAuthorizationServiceImpl implements PlayerAuthorizationService {

    private final KingdomRepository kingdomRepository;

//    @Override
//    public Boolean playerOwnsKingdom(Long kingdomId, String playerUsername) {
//        return kingdomRepository.findKingdomByRulerAndId(playerUsername, kingdomId).isPresent();
//    }
//
//    @Override
//    public String getUsernameFromToken(String token) {
//        return jwtRequestFilter.getUsername(token);
//    }
        public Boolean playerOwnsKingdom (String playerUsername, Long kingdomId){
            return kingdomRepository.findKingdomByRulerAndId(playerUsername, kingdomId).isPresent();
        }

        @Override
        public Kingdom getKingdomPreviewFromUsername (String username){
            return kingdomRepository.findKingdomByRuler(username);
        }
}
