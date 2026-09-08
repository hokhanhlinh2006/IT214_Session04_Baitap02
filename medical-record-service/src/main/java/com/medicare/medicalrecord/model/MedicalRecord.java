package com.medicare.medicalrecord.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.*;


@Entity
@Table(name = "medicalrecords")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicalRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long patientId;
    private Long doctorId;
    private String diagnosis;
    private String treatment;
    private String notes;

}
