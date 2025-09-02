package com.pharmacy.pharmacy.Controller;
import com.pharmacy.pharmacy.DTO.DoctorRequest;
import com.pharmacy.pharmacy.DTO.DoctorResponse;
import com.pharmacy.pharmacy.Model.Appointment;
import com.pharmacy.pharmacy.Model.Doctor;
import com.pharmacy.pharmacy.Model.Role;
import com.pharmacy.pharmacy.Service.AppointmentService;
import com.pharmacy.pharmacy.Service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/doctors")
@CrossOrigin(origins = "http://localhost:5175")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

@Autowired
private AppointmentService appointmentService;

    // ✅ Register new doctor (goes via Clerk + saves in DB)
    @PostMapping("/register")
    public ResponseEntity<?> registerDoctor(@RequestBody DoctorRequest request) {
        try {
            DoctorResponse response = doctorService.registerDoctor(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public List<Doctor> getDoctors() {
        return doctorService.getAllDoctors();
    }

    @PostMapping
    public Doctor addDoctor(@RequestBody Doctor doctor) {
        doctor.setRole(Role.DOCTOR);
        return doctorService.addDoctor(doctor);
    }

    @DeleteMapping("/{id}")
    public void deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
    }


    @GetMapping("/by-clerk/{clerkUserId}")
    public Doctor getDoctorByClerk(@PathVariable String clerkUserId) {
        return doctorService.getDoctorByClerkUserId(clerkUserId);
    }






    @GetMapping("/appointments/doctor/{doctorId}")
    public List<Appointment> getAppointments(@PathVariable Long doctorId) {
        return appointmentService.getAppointmentsForDoctor(doctorId);
    }

    @PostMapping("/appointments/{appointmentId}/reject")
    public Appointment rejectAppointment(@PathVariable Long appointmentId) {
        return appointmentService.rejectAppointment(appointmentId);
    }

    @PostMapping("/appointments/{appointmentId}/approve")
    public Appointment approveAppointment(@PathVariable Long appointmentId) {
        return appointmentService.approveAppointment(appointmentId);
    }
}
