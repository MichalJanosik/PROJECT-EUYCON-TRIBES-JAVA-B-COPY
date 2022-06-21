package com.example.projecteucyonjavatribesb;

import com.example.projecteucyonjavatribesb.model.Player;
import com.example.projecteucyonjavatribesb.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RequiredArgsConstructor
public class ProjectEucyonJavaTribesBApplication implements CommandLineRunner {

    private final PlayerRepository playerRepository;

    public static void main(String[] args) {
        SpringApplication.run(ProjectEucyonJavaTribesBApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
//        Map<String, PasswordEncoder> encoders = new HashMap<>();
//        encoders.put("bcrypt", new BCryptPasswordEncoder());
//        DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("bcrypt", encoders);
//        passwordEncoder.setDefaultPasswordEncoderForMatches(new BCryptPasswordEncoder());
//        return passwordEncoder;
        return new BCryptPasswordEncoder();
    }

    @Override
    public void run(String... args) throws Exception {
        //developement purposes
        populateWithPlayers();
    }

    private void populateWithPlayers() {
        Player player = new Player("password", "MisoTheBadass", "Mordor");
        player.setPassword(passwordEncoder().encode(player.getPassword()));
        playerRepository.save(player);
    }
}
