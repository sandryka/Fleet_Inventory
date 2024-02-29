package com.example.fleetinventory;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class FleetManagerCalculator {
    // Map to hold eVTOL names and their payload capacities
    private static final Map<String, Integer> eVtolPayloads;

    static {
        Map<String, Integer> aMap = new HashMap<>();
        aMap.put("Beta Technologies Alia", 1500);
        aMap.put("Boeing PAV", 500);
        aMap.put("Bell Nexus Air Taxi", 600); // Example payload, replace with actual value
        aMap.put("WISK Cora", 400);
        aMap.put("EHang 216", 485);
        aMap.put("Joby Aviation S4", 840);
        aMap.put("Vertical Aerospace VX4", 992);
        aMap.put("Lilium Jet", 441);
        aMap.put("Volocopter 2X", 350);
        aMap.put("Volocopter VoloCity", 441);
        aMap.put("Overair Butterfly", 1100);
        aMap.put("EmbraerX Eve", 600); // Example payload, replace with actual value
        aMap.put("Pipistrel 801 eVTOL", 1000);
        aMap.put("Skai by Alaka'i Technologi", 1000);
        aMap.put("Archer Maker", 1000);
        aMap.put("Jaunt Air Mobility eVTOL", 11000);
        // Add other eVTOL models here if needed
        eVtolPayloads = Collections.unmodifiableMap(aMap);
    }

    public static int calculateEVtolsRequired(int totalDailyWeightLb, String eVtolType) {
        Integer payloadCapacity = eVtolPayloads.get(eVtolType);

        if (payloadCapacity == null) {
            throw new IllegalArgumentException("Unknown eVTOL type: " + eVtolType);
        }

        if (totalDailyWeightLb <= 0) {
            throw new IllegalArgumentException("Total daily weight must be positive.");
        }

        return (int) Math.ceil((double) totalDailyWeightLb / payloadCapacity);
    }

    public static Map<String, Integer> getEvTolPayloads() {
        return eVtolPayloads;
    }
}
