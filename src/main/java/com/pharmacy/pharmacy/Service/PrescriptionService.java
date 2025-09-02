package com.pharmacy.pharmacy.Service;

import com.pharmacy.pharmacy.DTO.RecievePrescription;
import com.pharmacy.pharmacy.Model.Doctor;
import com.pharmacy.pharmacy.Model.Patient;
import com.pharmacy.pharmacy.Model.Prescription;
import com.pharmacy.pharmacy.Repository.DoctorRepository;
import com.pharmacy.pharmacy.Repository.PatientRepository;
import com.pharmacy.pharmacy.Repository.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrescriptionService {
    @Autowired
    private PrescriptionRepository prescriptionRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientRepository patientRepository;

    public Prescription addPrescription(Long doctorId, Long patientId, String details) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        Prescription prescription = new Prescription();
        prescription.setDoctor(doctor);
        prescription.setPatient(patient);
        prescription.setDetails(details);

        return prescriptionRepository.save(prescription);


    }
    public List<RecievePrescription> getPrescriptionsForPatient(Long patientId) {
        List<Prescription> prescriptions = prescriptionRepository.findByPatientId(patientId);

        return prescriptions.stream()
                .map(p -> new RecievePrescription(
                        p.getId(),
                        p.getDetails(),
                        p.getDoctor().getFullName(),
                        p.getPatient().getFullName(),
                        p.getCreatedAt()
                ))
                .collect(Collectors.toList());
    }
    public List<Prescription> getPrescriptionsForDoctor(Long doctorId) {
        return prescriptionRepository.findByDoctorId(doctorId);
    }
}
