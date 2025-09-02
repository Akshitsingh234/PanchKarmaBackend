package com.pharmacy.pharmacy.Controller;

import com.pharmacy.pharmacy.Model.MedicalReminder;
import com.pharmacy.pharmacy.Service.MedicalReminderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reminders")
public class MedicalReminderController {

    @Autowired
    private MedicalReminderService reminderService;

    @GetMapping("/patient/{patientId}")
    public List<MedicalReminder> getPatientReminders(@PathVariable String patientId) {
        return reminderService.getReminders(patientId);
    }

    @PostMapping("/patient")
    public MedicalReminder addReminder(@RequestBody MedicalReminder reminder) {
        return reminderService.addReminder(reminder);
    }

    @DeleteMapping("/patient/{patientId}/{reminderId}")
    @Transactional
    public void removeReminder(@PathVariable String patientId,
                               @PathVariable Long reminderId) {
        reminderService.removeReminder(reminderId, patientId);
    }
}
