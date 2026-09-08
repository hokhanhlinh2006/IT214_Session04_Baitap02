package com.example.IT214_Session04_Baitap02.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "incident_reports")
public class IncidentReport {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "driver_id", nullable = false, length = 50)
    private String driverId;

    @Column(name = "incident_type", nullable = false, length = 50)
    private String incidentType;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "reported_at", nullable = false)
    private LocalDateTime reportedAt;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // Default Constructor required by JPA
    // Protected to prevent direct initialization from outside (Encapsulation)
    protected IncidentReport() {
    }

    // Private constructor inside builder/factory pattern
    private IncidentReport(String driverId, String incidentType, Double latitude, Double longitude, String description, LocalDateTime reportedAt) {
        this.driverId = driverId;
        this.incidentType = incidentType;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
        this.reportedAt = reportedAt;
        this.createdAt = LocalDateTime.now();
    }

    // Factory method wrapper for clean instantiation with Defensive Programming
    public static IncidentReport create(String driverId, String incidentType, Double latitude, Double longitude, String description, LocalDateTime reportedAt) {
        if (driverId == null || driverId.trim().isEmpty()) {
            throw new IllegalArgumentException("Driver ID cannot be null or empty.");
        }
        if (incidentType == null || incidentType.trim().isEmpty()) {
            throw new IllegalArgumentException("Incident type cannot be null or empty.");
        }
        if (reportedAt == null) {
            throw new IllegalArgumentException("Reported time cannot be null.");
        }
        return new IncidentReport(driverId, incidentType, latitude, longitude, description, reportedAt);
    }

    // Getters only - preventing arbitrary updates post-creation
    public UUID getId() { return id; }
    public String getDriverId() { return driverId; }
    public String getIncidentType() { return incidentType; }
    public Double getLatitude() { return latitude; }
    public Double getLongitude() { return longitude; }
    public String getDescription() { return description; }
    public LocalDateTime getReportedAt() { return reportedAt; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    @Override
    public String toString() {
        return "IncidentReport{" +
                "id=" + id +
                ", driverId='" + driverId + '\'' +
                ", incidentType='" + incidentType + '\'' +
                ", reportedAt=" + reportedAt +
                '}';
    }
}
