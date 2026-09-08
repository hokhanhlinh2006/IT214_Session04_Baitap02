package com.medicare.medicalrecord.dto;

import lombok.*;
import java.time.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicalRecordDto {
    private Long id;
    private Long patientId;
    private Long doctorId;
    private String diagnosis;
    private String treatment;
    private String notes;

}
