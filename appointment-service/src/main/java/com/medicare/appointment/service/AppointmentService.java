package com.medicare.appointment.service;

import com.medicare.appointment.model.Appointment;
import com.medicare.appointment.repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    private final AppointmentRepository repository;
    
    public Appointment create(Appointment entity) {
        return repository.save(entity);
    }
    
    public List<Appointment> findAll() {
        return repository.findAll();
    }
    
    public Appointment findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
    }
    
    public Appointment update(Long id, Appointment newEntity) {
        Appointment existing = findById(id);
        // BeanUtils.copyProperties(newEntity, existing, "id");
        // Simplify for manual copy or saving
        newEntity.setId(id);
        return repository.save(newEntity);
    }
    
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
