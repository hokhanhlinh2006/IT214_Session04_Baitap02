package com.example.IT214_Session04_Baitap02.service;

import com.example.IT214_Session04_Baitap02.dto.IncidentExtraction;
import com.example.IT214_Session04_Baitap02.entity.IncidentReport;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Service
public class IncidentMappingService {

    public IncidentReport mapToEntity(IncidentExtraction extraction) {
        // Defensive parsing and validation
        Double lat = null;
        Double lon = null;
        try {
            if (extraction.locationLatitude() != null) {
                lat = Double.parseDouble(extraction.locationLatitude());
            }
            if (extraction.locationLongitude() != null) {
                lon = Double.parseDouble(extraction.locationLongitude());
            }
        } catch (NumberFormatException e) {
            // Handle or log logic here, default to null for now
            System.err.println("Failed to parse coordinates: " + e.getMessage());
        }

        LocalDateTime reportedAt = LocalDateTime.now();
        if (extraction.rawMessageTime() != null) {
            try {
                reportedAt = LocalDateTime.parse(extraction.rawMessageTime(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            } catch (DateTimeParseException e) {
                System.err.println("Failed to parse date time, using current time: " + e.getMessage());
            }
        }

        // Use factory method for instantiation to ensure domain invariants
        return IncidentReport.create(
                extraction.driverId(),
                extraction.incidentType(),
                lat,
                lon,
                extraction.description(),
                reportedAt
        );
    }
}
