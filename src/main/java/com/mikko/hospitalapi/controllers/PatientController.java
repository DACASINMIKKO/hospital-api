package com.mikko.hospitalapi.controllers;

import com.mikko.hospitalapi.models.Doctor;
import com.mikko.hospitalapi.models.Patient;
import com.mikko.hospitalapi.services.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/patients")
public class PatientController {
    private final PatientService patientService;

    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatients() {
        return ResponseEntity.ok(patientService.getAllPatients());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
        return ResponseEntity.ok(patientService.getPatientByID(id));
    }

    @PostMapping
    public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) {
        return ResponseEntity.ok(patientService.createPatient(patient));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable Long id, @RequestBody Patient patient) {
        return ResponseEntity.ok(patientService.updatePatient(id, patient));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return ResponseEntity.ok("Successfully deleted");
    }

    @PutMapping("/{patientId}/doctors/{doctorId}")
    public ResponseEntity<Patient> assignDoctor(@PathVariable Long patientId, @PathVariable Long doctorId) {
        return ResponseEntity.ok(patientService.addDoctors(doctorId, patientId));
    }

    @PutMapping("/{patientId}/rooms/{roomId}")
    public ResponseEntity<Patient> assignRoom(@PathVariable Long patientId, @PathVariable Long roomId) {
        return ResponseEntity.ok(patientService.addRoom(patientId, roomId));
    }
}
