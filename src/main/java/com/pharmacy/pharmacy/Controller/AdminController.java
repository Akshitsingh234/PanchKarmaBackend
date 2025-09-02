package com.pharmacy.pharmacy.Controller;

import com.pharmacy.pharmacy.Model.Doctor;
import com.pharmacy.pharmacy.Model.Patient;
import com.pharmacy.pharmacy.Model.Role;
import com.pharmacy.pharmacy.Service.DoctorService;
import com.pharmacy.pharmacy.Service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private DoctorService doctorService;
    @Autowired
    private PatientService patientService;

    @PostMapping("/doctor")
    public Doctor addDoctor(@RequestBody Doctor doctor) {
        doctor.setRole(Role.DOCTOR);
        return doctorService.addDoctor(doctor);
    }

    @DeleteMapping("/doctor/{id}")
    public void deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
    }


    @GetMapping("/doctors")
    public List<Doctor> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    @GetMapping("/patients")
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

}
