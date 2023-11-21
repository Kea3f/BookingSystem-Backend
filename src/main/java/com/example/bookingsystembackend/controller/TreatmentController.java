package com.example.bookingsystembackend.controller;

import com.example.bookingsystembackend.entity.Treatment;
import com.example.bookingsystembackend.service.TreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/treatment")
public class TreatmentController {

    private final TreatmentService treatmentService;
    @Autowired
    public TreatmentController(TreatmentService treatmentService){
        this.treatmentService = treatmentService;
    }

    @PostMapping("/create")
    public Treatment createTreatment(@RequestBody Treatment treatment){
        return treatmentService.createTreatment(treatment);
    }

    @PutMapping("/edit")
    public ResponseEntity<Treatment> editTreatment(@PathVariable int treatmentId, @RequestBody Treatment editTreatment){
        Treatment treatment = treatmentService.editTreatment(treatmentId, editTreatment);
        if (treatment != null){
            return ResponseEntity.ok(treatment);
        }
        return ResponseEntity.noContent().build();
    }


}
