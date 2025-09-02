package com.pharmacy.pharmacy.Service;

import com.pharmacy.pharmacy.Model.Appointment;
import com.pharmacy.pharmacy.Model.Doctor;
import com.pharmacy.pharmacy.Repository.AppointmentRepository;
import com.pharmacy.pharmacy.Repository.DoctorRepository;
import com.pharmacy.pharmacy.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;



    public Appointment book(Appointment appointment) {
        Long doctorId = appointment.getDoctor().getId(); // get the ID
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found with id " + doctorId));

        appointment.setDoctor(doctor); // set the managed entity
        return appointmentRepository.save(appointment);
    }




    public List<Appointment> getAll() {
        return appointmentRepository.findAll();
    }



    public Appointment updateStatus(Long id, String status) {
        Appointment a = appointmentRepository.findById(id).orElseThrow();
        a.setStatus(status);
        return appointmentRepository.save(a);
    }




    public List<Appointment> getByPatientId(String clerkUserId) {
        return appointmentRepository.findByPatientId(clerkUserId);
    }
    public List<Appointment> getAppointmentsForDoctor(Long doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        return appointmentRepository.findByDoctor(doctor);
    }
    public Appointment rejectAppointment(Long appointmentId) {
        Appointment appt = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        appt.setStatus("REJECTED");
        return appointmentRepository.save(appt);
    }

    public Appointment approveAppointment(Long appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
        appointment.setStatus("APPROVED");
        return appointmentRepository.save(appointment);
    }
}