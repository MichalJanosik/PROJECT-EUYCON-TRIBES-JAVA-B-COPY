package com.example.projecteucyonjavatribesb.service;

import com.example.projecteucyonjavatribesb.model.Kingdom;
import com.example.projecteucyonjavatribesb.model.Player;
import com.example.projecteucyonjavatribesb.repository.KingdomRepository;
import com.example.projecteucyonjavatribesb.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;
    private final KingdomRepository kingdomRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;



    @Override
    public boolean checkIfUsernameAlreadyExist(String username) {
        if (playerRepository.findAllByUsername(username).size() > 0) {
            return true;
        }
        return false;
    }

    @Override
    public void saveNewPlayerWithDefaultKingdomName(Player player) {
        String encodedPassword = bCryptPasswordEncoder.encode(player.getPassword());
        Player player1 = new Player(encodedPassword, player.getUsername(), player.getUsername().concat("'s kingdom"));
        Kingdom kingdom = new Kingdom(player.getUsername());
        player1.setKingdom(kingdom);
        kingdom.setPlayer(player1);
        playerRepository.save(player1);
        kingdomRepository.save(kingdom);
    }

    @Override
    public void saveNewPlayer(Player player) {
        String encodedPassword = bCryptPasswordEncoder.encode(player.getPassword());
        Player player1 = new Player(encodedPassword, player.getUsername(), player.getKingdomName());
        Kingdom kingdom = new Kingdom(player.getUsername());
        player1.setKingdom(kingdom);
        kingdom.setPlayer(player1);
        playerRepository.save(player1);
        kingdomRepository.save(kingdom);
    }


}
