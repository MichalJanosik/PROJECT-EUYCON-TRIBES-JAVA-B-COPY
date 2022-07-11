package com.example.projecteucyonjavatribesb;

import com.example.projecteucyonjavatribesb.model.Buildings;
import com.example.projecteucyonjavatribesb.model.Kingdom;
import com.example.projecteucyonjavatribesb.model.Player;
import com.example.projecteucyonjavatribesb.repository.BuildingsRepository;
import com.example.projecteucyonjavatribesb.repository.KingdomRepository;
import com.example.projecteucyonjavatribesb.repository.PlayerRepository;
import com.example.projecteucyonjavatribesb.service.BuildingsServiceImpl;
import com.example.projecteucyonjavatribesb.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final KingdomRepository kingdomRepository;
    private final BuildingsRepository buildingsRepository;
    private final BuildingsServiceImpl buildingsService;
//    private final PlayerService playerService;


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
        buildingsService.setCosts();
        Kingdom kingdom = new Kingdom("mira");
        Buildings buildings = new Buildings();
        Buildings building = new Buildings();
        buildings.setType("townhall");
        building.setType("farm");
        kingdom.getBuildingList().add(buildings);
//        Player player= new Player("as","ass");
//        playerService.saveNewPlayer(player);

        kingdomRepository.save(kingdom);
        building.setKingdom(kingdom);
        buildings.setKingdom(kingdom);
        buildingsRepository.save(buildings);
        buildingsRepository.save(building);
    }
}
