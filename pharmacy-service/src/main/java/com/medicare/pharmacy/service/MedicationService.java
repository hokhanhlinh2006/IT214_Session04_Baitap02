package com.medicare.pharmacy.service;

import com.medicare.pharmacy.model.Medication;
import com.medicare.pharmacy.repository.MedicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicationService {
    private final MedicationRepository repository;
    
    public Medication create(Medication entity) {
        return repository.save(entity);
    }
    
    public List<Medication> findAll() {
        return repository.findAll();
    }
    
    public Medication findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
    }
    
    public Medication update(Long id, Medication newEntity) {
        Medication existing = findById(id);
        // BeanUtils.copyProperties(newEntity, existing, "id");
        // Simplify for manual copy or saving
        newEntity.setId(id);
        return repository.save(newEntity);
    }
    
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
