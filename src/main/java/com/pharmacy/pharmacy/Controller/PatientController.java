package com.pharmacy.pharmacy.Controller;

import com.pharmacy.pharmacy.Model.Patient;
import com.pharmacy.pharmacy.Model.Role;
import com.pharmacy.pharmacy.Service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:5175")
@RequestMapping("/api/patients")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @PostMapping("/Patients")
    public Patient loginOrRegister(@RequestBody Patient request) {
        request.setRole(Role.PATIENT);
        return patientService.createOrGetPatient(
                request.getClerkUserId(),
                request.getEmail(),
                request.getFullName()
        );
    }

    @PutMapping("/{clerkUserId}/profile")
    public Patient updateProfile(@PathVariable String clerkUserId, @RequestBody Patient request) {
        return patientService.updateProfile(
                clerkUserId,
                request.getAge(),
                request.getGender(),
                request.getMedicalHistory(),
                request.getLifestyleHabits()
        );
    }

    @GetMapping("/{clerkUserId}")
    public Patient getProfile(@PathVariable String clerkUserId) {
        return patientService.getByClerkId(clerkUserId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
    }
}
