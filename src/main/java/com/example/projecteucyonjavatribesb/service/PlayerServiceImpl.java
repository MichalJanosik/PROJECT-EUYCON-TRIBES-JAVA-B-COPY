package com.example.projecteucyonjavatribesb.service;

import com.example.projecteucyonjavatribesb.model.Kingdom;
import com.example.projecteucyonjavatribesb.model.Player;
import com.example.projecteucyonjavatribesb.repository.KingdomRepository;
import com.example.projecteucyonjavatribesb.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class PlayerServiceImpl implements PlayerService, UserDetailsService {

    private final PlayerRepository playerRepository;
    private final KingdomRepository kingdomRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean checkIfUsernameAlreadyExist(String username) {
        if (playerRepository.findAllByUsername(username).size() > 0) {
            return true;
        }
        return false;
    }

    @Override
    public void saveNewPlayer(Player player) {
        String encodedPassword = passwordEncoder.encode(player.getPassword());
        Kingdom kingdom = new Kingdom(player.getUsername());
        Player player1;
        if (player.getKingdomName().isEmpty()) {
            player1 = new Player(encodedPassword, player.getUsername(), player.getUsername().concat("'s kingdom"));
        } else {
            player1 = new Player(encodedPassword, player.getUsername(), player.getKingdomName());
        }
        player1.setKingdom(kingdom);
        kingdom.setPlayer(player1);
        playerRepository.save(player1);
        kingdomRepository.save(kingdom);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            Player player = playerRepository.findByUsername(username);
            if (player == null) {
                log.error("Player not found in the database");
                throw new UsernameNotFoundException("User not found in the database");
            } else {
                log.info("Player found in the database: {}", username);
            }
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            //kingdom name could be also extracted from player.getKingdom().getName()
            authorities.add(new SimpleGrantedAuthority(player.getKingdomName()));
            return new User(player.getUsername(), player.getPassword(), authorities);
    }
}
