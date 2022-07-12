package com.example.projecteucyonjavatribesb.controller;

import com.example.projecteucyonjavatribesb.service.LeaderboardType;
import com.example.projecteucyonjavatribesb.service.LeaderboardsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LeaderboardsController {

    private final LeaderboardsService leaderboardsService;

    @GetMapping("/leaderboards/{type}")
    public ResponseEntity<?> getLeaderboardByType(@RequestParam("type")LeaderboardType type) {
        return ResponseEntity.ok().build();
    }
}
