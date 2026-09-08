package com.medicare.doctor.dto;

import lombok.*;
import java.time.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DoctorDto {
    private Long id;
    private String fullName;
    private String specialization;
    private Integer experienceYears;
    private String phone;

}
