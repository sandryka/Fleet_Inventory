package com.example.fleetinventory;

public class MissionRequirement {
    private final int totalDailyWeightLb;

    public MissionRequirement(int totalDailyWeightLb) {
        this.totalDailyWeightLb = totalDailyWeightLb;
    }

    public int getTotalDailyWeightLb() {
        return totalDailyWeightLb;
    }
}
