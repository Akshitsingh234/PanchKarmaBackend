package com.pharmacy.pharmacy.Repository;

import com.pharmacy.pharmacy.Model.MedicalReminder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalReminderRepository extends JpaRepository<MedicalReminder,Long> {
    List<MedicalReminder> findByPatientId(String patientId);

    // Optional: delete by patient and reminder id
    void deleteByIdAndPatientId(Long id, String patientId);
}
