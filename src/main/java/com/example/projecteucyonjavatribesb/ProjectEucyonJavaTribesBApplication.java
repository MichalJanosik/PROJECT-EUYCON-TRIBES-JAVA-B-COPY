package com.example.projecteucyonjavatribesb;

import com.example.projecteucyonjavatribesb.model.Player;
import com.example.projecteucyonjavatribesb.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@RequiredArgsConstructor
public class ProjectEucyonJavaTribesBApplication implements CommandLineRunner {


    private final PlayerRepository playerRepository;

    public static void main(String[] args) {
        SpringApplication.run(ProjectEucyonJavaTribesBApplication.class, args);
    }

    //this bean should be used(dependency injection) wherever BCryptPasswordEncoder password encoding is needed
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void run(String... args) throws Exception {
        //developement purposes
//        populateWithPlayers();
    }

//    private void populateWithPlayers() {
//        String password = passwordEncoder().encode("password");
//        Player player = new Player(password, "MisoDaBadass", "Mordor");
//        playerRepository.save(player);
//    }
}
