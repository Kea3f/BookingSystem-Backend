package com.example.bookingsystembackend.controller;

import com.example.bookingsystembackend.entity.Treatment;
import com.example.bookingsystembackend.service.CustomerService;
import com.example.bookingsystembackend.service.TreatmentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/treatment")
public class TreatmentController {

    private final TreatmentService treatmentService;
    private final CustomerService customerService;

    @Autowired
    public TreatmentController(TreatmentService treatmentService, CustomerService customerService){
        this.treatmentService = treatmentService;
        this.customerService = customerService;
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
    public ResponseEntity<Treatment> selectTreatment(@PathVariable int customerId, @PathVariable int treatmentId, HttpSession httpSession) {
        // Tjek if the customer is  logget nd
        if (httpSession.getAttribute("customerId") == null || (int) httpSession.getAttribute("customerId") != customerId) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        // treatment saves in the customer session
        Treatment treatment = treatmentService.getTreatmentById(treatmentId);
        if (treatment != null) {
            // save option chose in customer session
            treatmentService.setCustomerTreatment(customerId, treatmentId);
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
