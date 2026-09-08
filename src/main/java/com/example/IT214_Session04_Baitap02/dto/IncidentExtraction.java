package com.example.IT214_Session04_Baitap02.dto;

public record IncidentExtraction(
    String driverId,
    String incidentType,
    String locationLatitude,
    String locationLongitude,
    String description,
    String rawMessageTime
) {
    public IncidentExtraction {
        // Defensive checks during extraction initialization
        if (driverId == null || driverId.isBlank()) {
            throw new IllegalArgumentException("Driver ID cannot be blank.");
        }
        if (incidentType == null || incidentType.isBlank()) {
            throw new IllegalArgumentException("Incident type cannot be blank.");
        }
        
        // Let other fields be nullable or dirty, business validation will handle them later.
    }
}
