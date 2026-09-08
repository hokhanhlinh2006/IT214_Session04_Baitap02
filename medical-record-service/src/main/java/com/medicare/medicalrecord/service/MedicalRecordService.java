package com.medicare.medicalrecord.service;

import com.medicare.medicalrecord.model.MedicalRecord;
import com.medicare.medicalrecord.repository.MedicalRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicalRecordService {
    private final MedicalRecordRepository repository;
    
    public MedicalRecord create(MedicalRecord entity) {
        return repository.save(entity);
    }
    
    public List<MedicalRecord> findAll() {
        return repository.findAll();
    }
    
    public MedicalRecord findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
    }
    
    public MedicalRecord update(Long id, MedicalRecord newEntity) {
        MedicalRecord existing = findById(id);
        // BeanUtils.copyProperties(newEntity, existing, "id");
        // Simplify for manual copy or saving
        newEntity.setId(id);
        return repository.save(newEntity);
    }
    
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
