package com.pharmacy.pharmacy.Controller;

import com.pharmacy.pharmacy.DTO.ClerkUserRequest;
import com.pharmacy.pharmacy.DTO.UserResponse;
import com.pharmacy.pharmacy.Model.Admin;
import com.pharmacy.pharmacy.Model.Doctor;
import com.pharmacy.pharmacy.Model.Patient;
import com.pharmacy.pharmacy.Model.Role;
import com.pharmacy.pharmacy.Repository.AdminRepository;
import com.pharmacy.pharmacy.Repository.DoctorRepository;
import com.pharmacy.pharmacy.Service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5175")
public class AuthController {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientService patientService;

    @PostMapping("/login")
    public UserResponse login(@RequestBody ClerkUserRequest request) {

        // 1. Check Admin
        Optional<Admin> adminOpt = adminRepository.findByClerkUserId(request.getClerkUserId());
        if (adminOpt.isPresent()) {
            Admin admin = adminOpt.get();
            return new UserResponse(
                    admin.getClerkUserId(),
                    admin.getEmail(),
                    admin.getFullName(),
                    Role.ADMIN,
                    null,
                    null
            );
        }

        // 2. Check Doctor
        Optional<Doctor> doctorOpt = doctorRepository.findByClerkUserId(request.getClerkUserId());
        if (doctorOpt.isPresent()) {
            Doctor doctor = doctorOpt.get();
            return new UserResponse(
                    doctor.getClerkUserId(),
                    doctor.getEmail(),
                    doctor.getFullName(),
                    Role.DOCTOR,
                    null,
                    null
            );
        }

        // 3. Default → Patient
        Patient patient = patientService.createOrGetPatient(
                request.getClerkUserId(),
                request.getEmail(),
                request.getFullName()
        );

        return new UserResponse(
                patient.getClerkUserId(),
                patient.getEmail(),
                patient.getFullName(),
                Role.PATIENT,
                patient.getAge(),
                patient.getGender()
        );
    }
}
