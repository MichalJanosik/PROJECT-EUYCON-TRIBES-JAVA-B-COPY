package com.example.projecteucyonjavatribesb;

import com.example.projecteucyonjavatribesb.model.Buildings;
import com.example.projecteucyonjavatribesb.model.Kingdom;
import com.example.projecteucyonjavatribesb.model.Player;
import com.example.projecteucyonjavatribesb.repository.BuildingsRepository;
import com.example.projecteucyonjavatribesb.repository.KingdomRepository;
import com.example.projecteucyonjavatribesb.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class ProjectEucyonJavaTribesBApplication implements CommandLineRunner {


    private final PlayerRepository playerRepository;
    private final KingdomRepository kingdomRepository;
    private final BuildingsRepository buildingsRepository;

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
        Kingdom kingdom = new Kingdom("mira");
        Buildings buildings = new Buildings();
        Buildings building = new Buildings();
        buildings.setType("townhall");
        building.setType("farm");
        kingdom.getBuildingsList().add(buildings);

        kingdomRepository.save(kingdom);
        building.setKingdom(kingdom);
        buildings.setKingdom(kingdom);
        buildingsRepository.save(buildings);
        buildingsRepository.save(building);
    }
}
