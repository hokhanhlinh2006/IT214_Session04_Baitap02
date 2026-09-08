package com.medicare.doctor.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.*;


@Entity
@Table(name = "doctors")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String specialization;
    private Integer experienceYears;
    private String phone;

}
