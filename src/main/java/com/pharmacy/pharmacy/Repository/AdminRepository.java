package com.pharmacy.pharmacy.Repository;

import com.pharmacy.pharmacy.Model.Admin;
import com.pharmacy.pharmacy.Model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Long> {
    Optional<Admin> findByClerkUserId(String clerkUserId);
}
