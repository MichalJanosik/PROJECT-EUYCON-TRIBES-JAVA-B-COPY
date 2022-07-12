package com.example.projecteucyonjavatribesb.service;

import com.example.projecteucyonjavatribesb.model.DTO.LeaderboardsDTO;
import com.example.projecteucyonjavatribesb.model.Kingdom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LeaderboardsServiceImpl implements LeaderboardsService{

    private final KingdomService kingdomService;

    public List<LeaderboardsDTO> getLeaderboard(LeaderboardType type) {
        List<LeaderboardsDTO> leaderboard = new ArrayList<>();
        switch (type) {
            case BUILDINGS -> leaderboard = getLeaderBoardByBuildings();
            case TROOPS -> leaderboard = getLeaderboardByTroops();
            case KINGDOMS -> leaderboard = getLeaderboardByKingdoms();
        }

        return leaderboard;
    }

    private List<LeaderboardsDTO> getLeaderboardByKingdoms() {
        return null;
    }

    private List<LeaderboardsDTO> getLeaderboardByTroops() {
        return null;
    }

    private List<LeaderboardsDTO> getLeaderBoardByBuildings() {
        return null;
    }
}
