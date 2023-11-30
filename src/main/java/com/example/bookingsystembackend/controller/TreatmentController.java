package com.example.bookingsystembackend.controller;

import com.example.bookingsystembackend.entity.Treatment;
import com.example.bookingsystembackend.service.TreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


    //viewing all treatments
    @GetMapping("/treatmentList")
    public List<Treatment> getAllTreatments() {
        return treatmentService.getAllTreatments();
    }

    //Select specific treatment
    @GetMapping("/selectTreatment/{customerId}/{treatmentId}")
    public ResponseEntity<Treatment> selectTreatment(@PathVariable int treatmentId) {
        Treatment treatment = treatmentService.getTreatmentById(treatmentId);
        if (treatment != null) {
            return ResponseEntity.ok(treatment);
        }
        return ResponseEntity.notFound().build();
    }


    @PutMapping("/edit{treatmentId}")
    public ResponseEntity<Treatment> editTreatment(@PathVariable int treatmentId, @RequestBody Treatment editTreatment){
        Treatment treatment = treatmentService.editTreatment(treatmentId, editTreatment);
        if (treatment != null){
            return ResponseEntity.ok(treatment);
        }
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/{treatmentId}")
    public ResponseEntity<String> deleteTreatment(@PathVariable int treatmentId){
        treatmentService.deleteTreatment(treatmentId);
        return  ResponseEntity.ok("Treatment deleted");
    }
}
