package com.pharmacy.pharmacy.Repository;

import com.pharmacy.pharmacy.Model.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription,Long> {

        List<Prescription> findByPatientId(Long patientId);
        List<Prescription> findByDoctorId(Long doctorId);

}
