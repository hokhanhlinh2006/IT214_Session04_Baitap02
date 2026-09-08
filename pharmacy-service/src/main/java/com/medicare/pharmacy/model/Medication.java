package com.medicare.pharmacy.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.*;


@Entity
@Table(name = "medications")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Medication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String manufacturer;
    private Double price;
    private Integer quantity;

}
