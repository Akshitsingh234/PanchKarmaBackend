package com.pharmacy.pharmacy.Controller;

import com.pharmacy.pharmacy.Model.Appointment;
import com.pharmacy.pharmacy.Service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
@CrossOrigin(origins = "http://localhost:5173")

public class AppointmnetCotroller {
    @Autowired
    private AppointmentService appointmentService;

    @PostMapping
    public Appointment book(@RequestBody Appointment appointment) {
        return appointmentService.book(appointment);
    }



    @GetMapping("/clerk/{patientId}")
    public List<Appointment> getAppointmentsByPatient(@PathVariable String patientId) {
        return appointmentService.getByPatientId(patientId);
    }
    @PutMapping("/{id}/status")
    public Appointment updateStatus(@PathVariable Long id, @RequestParam String status) {
        return appointmentService.updateStatus(id, status);
    }
}
