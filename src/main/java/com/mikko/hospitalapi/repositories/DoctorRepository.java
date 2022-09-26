package com.mikko.hospitalapi.repositories;

import com.mikko.hospitalapi.models.Doctor;
import com.mikko.hospitalapi.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
