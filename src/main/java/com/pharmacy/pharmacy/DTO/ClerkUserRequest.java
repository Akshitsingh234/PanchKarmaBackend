package com.pharmacy.pharmacy.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClerkUserRequest {
    private String clerkUserId;
    private String email;
    private String fullName;
}

