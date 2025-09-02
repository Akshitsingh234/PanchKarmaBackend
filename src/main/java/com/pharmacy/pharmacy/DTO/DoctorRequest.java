package com.pharmacy.pharmacy.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorRequest {
    private String fullName;
    private String email;
    private String specialization;
    private   int experience;

}
