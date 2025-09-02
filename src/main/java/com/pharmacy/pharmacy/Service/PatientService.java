package com.pharmacy.pharmacy.Service;

import com.pharmacy.pharmacy.Model.Patient;
import com.pharmacy.pharmacy.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepo;

    public Patient createOrGetPatient(String clerkUserId, String email, String fullName) {
        return patientRepo.findByClerkUserId(clerkUserId)
                .orElseGet(() -> {
                    Patient newPatient = new Patient();
                    newPatient.setClerkUserId(clerkUserId);
                    newPatient.setEmail(email);
                    newPatient.setFullName(fullName);
                    return patientRepo.save(newPatient);
                });
    }

    public Optional<Patient> getByClerkId(String clerkUserId) {
        return patientRepo.findByClerkUserId(clerkUserId);
    }


    public Patient updateProfile(String clerkUserId, Integer age, String gender,
                                 String medicalHistory, String lifestyleHabits) {
        Patient patient = patientRepo.findByClerkUserId(clerkUserId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        patient.setAge(age);
        patient.setGender(gender);
        patient.setMedicalHistory(medicalHistory);
        patient.setLifestyleHabits(lifestyleHabits);

        return patientRepo.save(patient);
    }

    public List<Patient> getAllPatients() {
         return patientRepo.findAll();
    }
}
