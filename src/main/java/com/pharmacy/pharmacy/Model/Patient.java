package com.pharmacy.pharmacy.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "patients")
@Builder
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String clerkUserId;


    private String email;
    private String fullName;

    // Profile setup
    private Integer age;
    private String gender;

    @Column(length = 1000)
    private String medicalHistory;

    @Column(length = 1000)
    private String lifestyleHabits;

    @Enumerated(EnumType.STRING)
    private Role role = Role.PATIENT;

}
