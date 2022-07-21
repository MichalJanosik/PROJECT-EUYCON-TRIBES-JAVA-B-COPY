package com.example.projecteucyonjavatribesb.controller;

import com.example.projecteucyonjavatribesb.model.DTO.LeaderboardsDTO;
import com.example.projecteucyonjavatribesb.service.LeaderboardType;
import com.example.projecteucyonjavatribesb.service.LeaderboardsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LeaderboardsController {

    private final LeaderboardsService leaderboardsService;

    @GetMapping("/leaderboards/{type}")
    public ResponseEntity<?> getLeaderboardByType(@PathVariable("type") String type) {
        if (Objects.nonNull(type)) {
            if (ObjectUtils.containsConstant(LeaderboardType.values(), type, false)) {
                List<LeaderboardsDTO> leaderboard = leaderboardsService.getLeaderboard(
                        LeaderboardType.valueOf(type.toUpperCase())
                );
                return ResponseEntity.ok().body(leaderboard);
            } else {
                throw new RuntimeException("Leaderboard type must be defined");
            }
        } else {
            throw new RuntimeException("Leaderboard type must be defined");
        }
    }
}
