package com.medicare.doctor.service;

import com.medicare.doctor.model.Doctor;
import com.medicare.doctor.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorService {
    private final DoctorRepository repository;
    
    public Doctor create(Doctor entity) {
        return repository.save(entity);
    }
    
    public List<Doctor> findAll() {
        return repository.findAll();
    }
    
    public Doctor findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
    }
    
    public Doctor update(Long id, Doctor newEntity) {
        Doctor existing = findById(id);
        // BeanUtils.copyProperties(newEntity, existing, "id");
        // Simplify for manual copy or saving
        newEntity.setId(id);
        return repository.save(newEntity);
    }
    
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
