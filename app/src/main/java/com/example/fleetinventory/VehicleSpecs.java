package com.example.fleetinventory;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class VehicleSpecs {
    private static class EVtolModelSpecs {
        int payloadCapacity;
        int range; // Range in miles

        EVtolModelSpecs(int payloadCapacity, int range) {
            this.payloadCapacity = payloadCapacity;
            this.range = range;
        }
    }

    private static final Map<String, EVtolModelSpecs> eVtolSpecs = new HashMap<>();

    static {
        // Initializing payload capacities and ranges for various eVTOL models
        eVtolSpecs.put("Beta Technologies Alia", new EVtolModelSpecs(1500, 250));
        eVtolSpecs.put("Boeing PAV", new EVtolModelSpecs(500, 50));
        eVtolSpecs.put("Bell Nexus Air Taxi", new EVtolModelSpecs(600, 60)); // Replace with actual values
        eVtolSpecs.put("WISK Cora", new EVtolModelSpecs(400, 62));
        eVtolSpecs.put("EHang 216", new EVtolModelSpecs(485, 22));
        eVtolSpecs.put("Joby Aviation S4", new EVtolModelSpecs(840, 150));
        eVtolSpecs.put("Vertical Aerospace VX4", new EVtolModelSpecs(992, 100));
        eVtolSpecs.put("Lilium Jet", new EVtolModelSpecs(441, 190));
        eVtolSpecs.put("Volocopter 2X", new EVtolModelSpecs(350, 17));
        eVtolSpecs.put("Volocopter VoloCity", new EVtolModelSpecs(441, 35)); // Range is approximate
        eVtolSpecs.put("Overair Butterfly", new EVtolModelSpecs(1100, 100));
        eVtolSpecs.put("EmbraerX Eve", new EVtolModelSpecs(600, 60)); // Replace with actual values
        eVtolSpecs.put("Pipistrel 801 eVTOL", new EVtolModelSpecs(1000, 60));
        eVtolSpecs.put("Skai by Alaka'i Technologi", new EVtolModelSpecs(1000, 400));
        eVtolSpecs.put("Archer Maker", new EVtolModelSpecs(1000, 60));
        eVtolSpecs.put("Jaunt Air Mobility eVTOL", new EVtolModelSpecs(11000, 80));
        // Additional models can be added here
    }

    public static int getPayloadCapacity(String eVtolModel) {
        EVtolModelSpecs specs = eVtolSpecs.getOrDefault(eVtolModel, new EVtolModelSpecs(0, 0));
        return specs.payloadCapacity;
    }

    public static int getRange(String eVtolModel) {
        EVtolModelSpecs specs = eVtolSpecs.getOrDefault(eVtolModel, new EVtolModelSpecs(0, 0));
        return specs.range;
    }

    public static Set<String> getModels() {
        return eVtolSpecs.keySet();
    }
}
