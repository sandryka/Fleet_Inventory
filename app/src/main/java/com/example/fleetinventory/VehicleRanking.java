package com.example.fleetinventory;

import java.util.Map;

public class VehicleRanking {
    private final Map<String, Integer> rankings;

    public VehicleRanking(Map<String, Integer> rankings) {
        this.rankings = rankings;
    }

    public Map<String, Integer> getRankings() {
        return rankings;
    }
}
