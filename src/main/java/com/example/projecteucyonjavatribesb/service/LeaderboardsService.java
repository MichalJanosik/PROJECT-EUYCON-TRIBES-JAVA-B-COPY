package com.example.projecteucyonjavatribesb.service;

import com.example.projecteucyonjavatribesb.model.DTO.LeaderboardsDTO;

import java.util.List;

public interface LeaderboardsService {
    List<LeaderboardsDTO> getLeaderboard(LeaderboardType type);
}
