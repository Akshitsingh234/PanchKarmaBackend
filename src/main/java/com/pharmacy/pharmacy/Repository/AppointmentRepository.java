package com.pharmacy.pharmacy.Repository;

import com.pharmacy.pharmacy.Model.Appointment;
import com.pharmacy.pharmacy.Model.Doctor;
import com.pharmacy.pharmacy.Model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Long> {

    List<Appointment> findByPatientId(String patientId);
    List<Appointment> findByDoctor(Doctor doctor);// Clerk’s userId stored here
}

