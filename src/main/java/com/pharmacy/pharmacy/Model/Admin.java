package com.pharmacy.pharmacy.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String clerkUserId;
    private String email;
    private String fullName;


    @Enumerated(EnumType.STRING)
    private Role role = Role.ADMIN;

}

