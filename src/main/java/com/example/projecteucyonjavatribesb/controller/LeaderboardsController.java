package com.example.projecteucyonjavatribesb.controller;

import com.example.projecteucyonjavatribesb.model.DTO.LeaderboardsDTO;
import com.example.projecteucyonjavatribesb.service.LeaderboardType;
import com.example.projecteucyonjavatribesb.service.LeaderboardsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LeaderboardsController {

    private final LeaderboardsService leaderboardsService;

    @GetMapping("/leaderboards/{type}")
    public ResponseEntity<?> getLeaderboardByType(@RequestParam("type") LeaderboardType type) {
        if (Objects.nonNull(type)) {
            List<LeaderboardsDTO> leaderboard = leaderboardsService.getLeaderboard(type);
            return ResponseEntity.ok().body(leaderboard);
        } else {
            throw new RuntimeException("Leaderboard type not defined");
        }
    }
}
