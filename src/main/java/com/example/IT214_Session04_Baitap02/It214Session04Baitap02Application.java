package com.example.IT214_Session04_Baitap02;

import com.example.IT214_Session04_Baitap02.dto.IncidentExtraction;
import com.example.IT214_Session04_Baitap02.entity.IncidentReport;
import com.example.IT214_Session04_Baitap02.service.IncidentMappingService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class It214Session04Baitap02Application {

	public static void main(String[] args) {
		SpringApplication.run(It214Session04Baitap02Application.class, args);
	}

	@Bean
	public CommandLineRunner run(IncidentMappingService mappingService) {
		return args -> {
			System.out.println("===============================");
			System.out.println("TESTING INCIDENT EXTRACTION TO JPA ENTITY");
			
			// 1. Simulate data extracted from LLM via BeanOutputConverter
			IncidentExtraction extraction = new IncidentExtraction(
					"DRV-1337",
					"TRAFFIC_JAM",
					"21.028511",
					"105.804817",
					"Heavy traffic near intersection",
					"2026-09-08T08:15:30"
			);
			
			System.out.println("[1] Created DTO (Record): " + extraction);
			
			// 2. Map DTO to JPA Entity
			IncidentReport report = mappingService.mapToEntity(extraction);
			System.out.println("[2] Mapped to JPA Entity: " + report);
			System.out.println("Entity Details -> Lat: " + report.getLatitude() + ", Lng: " + report.getLongitude());
			
			System.out.println("SUCCESS: No runtime errors encountered during mapping!");
			System.out.println("===============================");
		};
	}
}
