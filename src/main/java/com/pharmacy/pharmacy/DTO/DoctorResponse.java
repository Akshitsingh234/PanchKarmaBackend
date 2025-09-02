package com.pharmacy.pharmacy.DTO;


import com.pharmacy.pharmacy.Model.Doctor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorResponse {
    private Doctor doctor;
    private String tempPassword;
}

