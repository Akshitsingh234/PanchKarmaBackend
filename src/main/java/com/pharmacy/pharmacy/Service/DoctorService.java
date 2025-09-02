package com.pharmacy.pharmacy.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pharmacy.pharmacy.DTO.DoctorRequest;
import com.pharmacy.pharmacy.DTO.DoctorResponse;
import com.pharmacy.pharmacy.Model.Doctor;
import com.pharmacy.pharmacy.Model.Role;
import com.pharmacy.pharmacy.Repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.security.SecureRandom;
import java.util.*;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;


//
//    @Value("${clerk.secret.key}") // put in application.properties
//    private String clerkSecretKey;

    @Value("${clerk.api.key}")
    private String clerkApiKey;

    @Value("${clerk.api.url}")
    private String clerkApiUrl;

    private static final String CHAR_POOL =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+-=";
    private static final SecureRandom random = new SecureRandom();

    // ✅ Generate a strong random password
    private String generatePassword(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(CHAR_POOL.charAt(random.nextInt(CHAR_POOL.length())));
        }
        return sb.toString();
    }

    // ✅ Register doctor
    public DoctorResponse registerDoctor(DoctorRequest request) throws Exception {
        Optional<Doctor> existingDoctor = doctorRepository.findByEmail(request.getEmail());
        if (existingDoctor.isPresent()) {
            throw new Exception("Doctor already exists");
        }

        String autoPassword = generatePassword(14);

        // Clerk API call
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(clerkApiKey);

        Map<String, Object> clerkPayload = new HashMap<>();
        clerkPayload.put("email_address", Collections.singletonList(request.getEmail()));
        clerkPayload.put("password", autoPassword);
        clerkPayload.put("first_name", request.getFullName());

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(clerkPayload, headers);

        ResponseEntity<String> response =
                restTemplate.postForEntity(clerkApiUrl + "/users", entity, String.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new Exception("Failed to create doctor in Clerk: " + response.getBody());
        }

        // Parse Clerk response
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonResponse = mapper.readTree(response.getBody());
        String clerkUserId = jsonResponse.get("id").asText();

        // Save doctor in DB
        Doctor doctor = new Doctor();
        doctor.setClerkUserId(clerkUserId);
        doctor.setFullName(request.getFullName());
        doctor.setEmail(request.getEmail());
        doctor.setSpecialization(request.getSpecialization());
        doctor.setExperience(request.getExperience());
        doctor.setRole(Role.DOCTOR);
        doctorRepository.save(doctor);

        return new DoctorResponse(doctor, autoPassword);
    }

    // ✅ Extra CRUD helpers
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public Doctor addDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }


    public Doctor getDoctorByClerkUserId(String clerkUserId) {
        return doctorRepository.findByClerkUserId(clerkUserId)
                .orElseThrow(() -> new RuntimeException("Doctor not found for Clerk ID: " + clerkUserId));
    }



    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
    }




}
