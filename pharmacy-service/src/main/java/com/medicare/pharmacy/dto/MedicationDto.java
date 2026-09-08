package com.medicare.pharmacy.dto;

import lombok.*;
import java.time.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicationDto {
    private Long id;
    private String name;
    private String manufacturer;
    private Double price;
    private Integer quantity;

}
