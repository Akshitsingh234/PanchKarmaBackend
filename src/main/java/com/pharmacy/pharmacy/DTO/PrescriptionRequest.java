package com.pharmacy.pharmacy.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrescriptionRequest {
    private Long doctorId;
    private Long patientId;
    private String prescriptionText;
}
