package com.example.projecteucyonjavatribesb.utility;

import com.example.projecteucyonjavatribesb.service.BuildingsServiceImpl;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.HashMap;

@RequiredArgsConstructor
@Getter
@Setter
public class BuildingAttributeUtility {
    public HashMap<String, Integer> costs = new HashMap<>();
    public HashMap<String, Long> time = new HashMap<>();


    public void BuildingAttributeUtility(){
        costs.put("farm",30);
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
