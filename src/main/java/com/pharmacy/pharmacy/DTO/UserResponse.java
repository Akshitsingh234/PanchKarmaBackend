package com.pharmacy.pharmacy.DTO;

import com.pharmacy.pharmacy.Model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private String clerkUserId;
    private String email;
    private String fullName;
    private Role role;


    // Optional for patients
    private Integer age;
    private String gender;
}
