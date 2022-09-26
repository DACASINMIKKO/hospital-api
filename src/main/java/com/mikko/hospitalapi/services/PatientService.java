package com.mikko.hospitalapi.services;

import com.mikko.hospitalapi.exceptions.ResourceNotFoundException;
import com.mikko.hospitalapi.models.Doctor;
import com.mikko.hospitalapi.models.Patient;
import com.mikko.hospitalapi.models.Room;
import com.mikko.hospitalapi.repositories.DoctorRepository;
import com.mikko.hospitalapi.repositories.PatientRepository;
import com.mikko.hospitalapi.repositories.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final RoomRepository roomRepository;

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Patient getPatientByID(Long id) {
        return patientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Patient not found"));
    }

    public Patient createPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public Patient updatePatient(Long id, Patient patient) {
        Patient existingPatient = getPatientByID(id);
        existingPatient.setFirstName(patient.getFirstName());
        existingPatient.setLastName(patient.getLastName());
        existingPatient.setAge(patient.getAge());
        existingPatient.setGender(patient.getGender());
        existingPatient.setIsDeceased(patient.getIsDeceased());
        existingPatient.setDoctor(patient.getDoctor());
        existingPatient.setRoom(patient.getRoom());
        return patientRepository.save(existingPatient);
    }

    public void deletePatient(Long id) {
        Patient existingPatient = getPatientByID(id);
        patientRepository.delete(existingPatient);
    }

    public Patient addDoctors(Long doctorId, Long patientId) {
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(() -> new ResourceNotFoundException("Doctor not found"));
        Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new ResourceNotFoundException("Patient not found"));
        patient.addDoctor(doctor);
        doctor.addPatients(patient);
        return patientRepository.save(patient);
    }

    public Patient addRoom(Long patientId, Long roomId) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new ResourceNotFoundException("Patient not found"));
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new ResourceNotFoundException("Room not found"));
        patient.addRoom(room);
        room.addPatient(patient);
        return patientRepository.save(patient);
    }
}
