package com.pharmacy.pharmacy.Repository;

import com.pharmacy.pharmacy.Model.Doctor;
import com.pharmacy.pharmacy.Model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Long> {
    Optional<Doctor> findByClerkUserId(String clerkUserId);
    Optional<Doctor> findByEmail(String email);
}
