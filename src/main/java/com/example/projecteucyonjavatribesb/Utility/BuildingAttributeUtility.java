package com.example.projecteucyonjavatribesb.Utility;

import lombok.*;
import org.springframework.stereotype.Service;

import javax.websocket.server.ServerEndpoint;
import java.util.HashMap;

@Getter
@Setter
@Service
public class BuildingAttributeUtility {
    public HashMap<String, Integer> costs = new HashMap<>();
    public HashMap<String, Long> time = new HashMap<>();


    public BuildingAttributeUtility() {
        costs.put("farm",30);
        costs.put("Town Hall",90);
//        costs.put("Town Hall",20);
        costs.put("mine",30);
        costs.put("academy",50);
        costs.put("barracks",60);
        costs.put("walls",60);

        time.put("farm",1800000L); // 30 min
        time.put("Town Hall",3600000L); // 1 h
        time.put("mine",1800000L); // 30 min
        time.put("academy",2700000L); // 45 min
        time.put("barracks",2700000L); // 45 min
        time.put("walls",1200000L); // 20 min
    }

}
