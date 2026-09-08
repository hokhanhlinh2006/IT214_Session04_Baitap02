package com.medicare.patient.service;

import com.medicare.patient.model.Patient;
import com.medicare.patient.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository repository;
    
    public Patient create(Patient entity) {
        return repository.save(entity);
    }
    
    public List<Patient> findAll() {
        return repository.findAll();
    }
    
    public Patient findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
    }
    
    public Patient update(Long id, Patient newEntity) {
        Patient existing = findById(id);
        // BeanUtils.copyProperties(newEntity, existing, "id");
        // Simplify for manual copy or saving
        newEntity.setId(id);
        return repository.save(newEntity);
    }
    
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
