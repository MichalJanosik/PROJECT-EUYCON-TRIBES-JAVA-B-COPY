package com.example.projecteucyonjavatribesb.utility;

import com.example.projecteucyonjavatribesb.service.BuildingsServiceImpl;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

public class BuildingAttributeUtility {
    private final BuildingsServiceImpl buildingsService;

    public void setUp(){
        //This set hashmap from buildingServiceImpl and sets type of building as key and
        //cost of this type as value of hashmap, necessary for this function
        buildingsService.setCosts();
    }
}
