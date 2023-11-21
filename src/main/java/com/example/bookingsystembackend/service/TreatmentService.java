package com.example.bookingsystembackend.service;

import com.example.bookingsystembackend.entity.Treatment;
import com.example.bookingsystembackend.repositories.TreatmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreatmentService {

    @Autowired
    private TreatmentRepository treatmentRepository;

    @Autowired
    public TreatmentService(TreatmentRepository treatmentRepository) {
        this.treatmentRepository = treatmentRepository;
    }
    public Treatment createTreatment(Treatment treatment){
        return treatmentRepository.save(treatment);
    }

    public List<Treatment> readTreatments(){
        return treatmentRepository.findAll();
    }

    public Treatment editTreatment (int treatmentId, Treatment editedTreatement){
        Treatment existingTreatment = treatmentRepository.findByTreatmentId(treatmentId);

        if(existingTreatment != null){
            existingTreatment.setName(editedTreatement.getName());
            existingTreatment.setDescription(editedTreatement.getDescription());
            existingTreatment.setDuration(editedTreatement.getDuration());
            existingTreatment.setPrice(editedTreatement.getPrice());
            return treatmentRepository.save(existingTreatment);
        }
        return null;
    }

    public void deleteTreatment(int treatmentId) {
        treatmentRepository.deleteById(treatmentId);
    }


}
