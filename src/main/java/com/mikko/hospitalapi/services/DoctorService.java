package com.mikko.hospitalapi.services;

import com.mikko.hospitalapi.controllers.DoctorController;
import com.mikko.hospitalapi.exceptions.ResourceNotFoundException;
import com.mikko.hospitalapi.models.Doctor;
import com.mikko.hospitalapi.models.Patient;
import com.mikko.hospitalapi.repositories.DoctorRepository;
import com.mikko.hospitalapi.repositories.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DoctorService {
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public Doctor getDoctorByID(Long id) {
        return doctorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Doctor not found"));
    }

    public Doctor createDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public Doctor updateDoctor(Long doctorId, Doctor doctor) {
        Doctor existingDoctor = getDoctorByID(doctorId);
        existingDoctor.setFirstName(doctor.getFirstName());
        existingDoctor.setLastName(doctor.getLastName());
        return createDoctor(existingDoctor);
    }

    public void deleteDoctor(Long id) {
        Doctor existingDoctor = getDoctorByID(id);
        doctorRepository.delete(existingDoctor);
    }

    public Doctor addPatients(Long doctorId, Long patientId) {
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(() -> new ResourceNotFoundException("Doctor not found"));
        Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new ResourceNotFoundException("Patient not found"));
        doctor.addPatients(patient);
        patient.setDoctor(doctor);
        return doctorRepository.save(doctor);
    }
}
