package com.pharmacy.pharmacy.Service;

import com.pharmacy.pharmacy.Model.MedicalReminder;
import com.pharmacy.pharmacy.Repository.MedicalReminderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalReminderService {

    @Autowired
    private MedicalReminderRepository reminderRepository;

    public List<MedicalReminder> getReminders(String patientId) {
        return reminderRepository.findByPatientId(patientId);
    }

    public MedicalReminder addReminder(MedicalReminder reminder) {
        return reminderRepository.save(reminder);
    }

    public void removeReminder(Long id, String patientId) {
        reminderRepository.deleteByIdAndPatientId(id, patientId);
    }
}

