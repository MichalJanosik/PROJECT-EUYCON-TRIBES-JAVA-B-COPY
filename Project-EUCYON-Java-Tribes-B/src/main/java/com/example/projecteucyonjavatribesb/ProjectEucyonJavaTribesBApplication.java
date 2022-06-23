package com.example.projecteucyonjavatribesb;

import com.example.projecteucyonjavatribesb.model.Kingdom;
import com.example.projecteucyonjavatribesb.model.Location;
import com.example.projecteucyonjavatribesb.repository.KingdomRepository;
import com.example.projecteucyonjavatribesb.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjectEucyonJavaTribesBApplication implements CommandLineRunner {
    //TODO: delete commnads from this class - it was just for test purpose
    public Kingdom kingdom2 = new Kingdom();
    public Location location4 = new Location();
    private final KingdomRepository kingdomRepository;
    private final LocationRepository locationRepository;
    @Autowired
    public ProjectEucyonJavaTribesBApplication(KingdomRepository kingdomRepository, LocationRepository locationRepository) {
        this.kingdomRepository = kingdomRepository;
        this.locationRepository = locationRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(ProjectEucyonJavaTribesBApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        kingdomRepository.save(kingdom2);
//        location4.setCoordinateX(1);
//        location4.setCoordinateY(1);
//        locationRepository.save(location4);
    }
}
