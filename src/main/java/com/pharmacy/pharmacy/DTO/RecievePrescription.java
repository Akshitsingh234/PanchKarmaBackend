package com.pharmacy.pharmacy.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecievePrescription {
    private Long id;
    private String details;
    private String doctorName;
    private String patientName;
    private LocalDateTime createdAt;
}
