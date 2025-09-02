package com.pharmacy.pharmacy.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String clerkUserId;
    private String fullName;
    private String email;
    private int experience;  // years of experience
    private String specialization; // field of professionalism


    @Enumerated(EnumType.STRING)
    private Role role = Role.DOCTOR;
}

