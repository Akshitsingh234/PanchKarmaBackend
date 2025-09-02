package com.pharmacy.pharmacy.Controller;

import com.pharmacy.pharmacy.DTO.PrescriptionRequest;
import com.pharmacy.pharmacy.DTO.RecievePrescription;
import com.pharmacy.pharmacy.Model.Prescription;
import com.pharmacy.pharmacy.Service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/prescriptions")
public class PrescriptionController {
    @Autowired
    private PrescriptionService prescriptionService;

    @PostMapping("/add")
    public Prescription addPrescription(
            @RequestBody PrescriptionRequest request) {
        return prescriptionService.addPrescription(
                request.getDoctorId(),
                request.getPatientId(),
                request.getPrescriptionText());
    }

    @GetMapping("/patient/{patientId}")
    public List<RecievePrescription> getPrescriptionsForPatient(@PathVariable Long patientId) {
        return prescriptionService.getPrescriptionsForPatient(patientId);
    }
    @GetMapping("/doctor/{doctorId}")
    public List<Prescription> getPrescriptionsForDoctor(@PathVariable Long doctorId) {
        return prescriptionService.getPrescriptionsForDoctor(doctorId);
    }
}
