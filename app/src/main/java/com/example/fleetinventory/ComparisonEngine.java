package com.example.fleetinventory;

import java.util.HashMap;
import java.util.Map;

public class ComparisonEngine {

    public VehicleRanking compareVehicles(MissionRequirement requirement, int missionRange) {
        Map<String, Integer> rankings = new HashMap<>();
        for (String model : VehicleSpecs.getModels()) {
            int payload = VehicleSpecs.getPayloadCapacity(model);
            int range = VehicleSpecs.getRange(model);

            if (range >= missionRange) {
                int eVtolsRequired = (int) Math.ceil((double) requirement.getTotalDailyWeightLb() / payload);
                rankings.put(model, eVtolsRequired);
            }
        }
        return new VehicleRanking(rankings);
    }
}
