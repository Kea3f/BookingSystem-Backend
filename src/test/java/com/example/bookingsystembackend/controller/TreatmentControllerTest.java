package com.example.bookingsystembackend.controller;

import com.example.bookingsystembackend.entity.Treatment;
import com.example.bookingsystembackend.service.TreatmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class TreatmentControllerTest {

    @Mock
    private TreatmentService treatmentService;

    @InjectMocks
    private TreatmentController treatmentController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateTreatment() {
        // Arrange
        // Create a new instance of Treatment for testing.
        Treatment treatment = new Treatment();

        // Mock the behavior of treatmentService.createTreatment() to return the created treatment.
        when(treatmentService.createTreatment(treatment)).thenReturn(treatment);

        // Act
        // Call the createTreatment method on the treatmentController under test.
        Treatment result = treatmentController.createTreatment(treatment);

        // Assert
        // Ensure that the result is not null and is equal to the treatment created.
        assertNotNull(result);
        assertEquals(treatment, result);

        // Verify that the createTreatment method of treatmentService was called exactly once with the specified treatment.
        verify(treatmentService, times(1)).createTreatment(treatment);
    }

    @Test
    void testReadTreatments() {
        // Arrange
        // Create a list of treatments for testing.
        List<Treatment> treatments = Arrays.asList(new Treatment(), new Treatment());

        // Mock the behavior of treatmentService.readTreatments() to return the list of treatments.
        when(treatmentService.readTreatments()).thenReturn(treatments);

        // Act
        // Call the readTreatments method on the treatmentController under test.
        List<Treatment> result = treatmentController.readTreatments();

        // Assert
        // Ensure that the result is not null and is equal to the list of treatments created.
        assertNotNull(result);
        assertEquals(treatments, result);

        // Verify that the readTreatments method of treatmentService was called exactly once.
        verify(treatmentService, times(1)).readTreatments();
    }

    @Test
    void testEditTreatment() {
        // Arrange
        // Specify the treatmentId and create instances of Treatment for testing.
        int treatmentId = 1;
        Treatment editTreatment = new Treatment();
        Treatment updatedTreatment = new Treatment();

        // Mock the behavior of treatmentService.editTreatment() to return the updated treatment.
        when(treatmentService.editTreatment(treatmentId, editTreatment)).thenReturn(updatedTreatment);

        // Act
        // Call the editTreatment method on the treatmentController under test.
        ResponseEntity<Treatment> result = treatmentController.editTreatment(treatmentId, editTreatment);

        // Assert
        // Ensure that the result is not null and contains the updated treatment.
        assertNotNull(result);
        assertEquals(updatedTreatment, result.getBody());
        assertEquals(ResponseEntity.ok(updatedTreatment), result);

        // Verify that the editTreatment method of treatmentService was called exactly once with the specified parameters.
        verify(treatmentService, times(1)).editTreatment(treatmentId, editTreatment);
    }

    @Test
    void testDeleteTreatment() {
        // Arrange
        // Specify the treatmentId for testing.
        int treatmentId = 1;

        // Act
        // Call the deleteTreatment method on the treatmentController under test.
        ResponseEntity<String> result = treatmentController.deleteTreatment(treatmentId);

        // Assert
        // Ensure that the result is not null and contains the expected response.
        assertNotNull(result);
        assertEquals(ResponseEntity.ok("Treatment deleted"), result);

        // Verify that the deleteTreatment method of treatmentService was called exactly once with the specified treatmentId.
        verify(treatmentService, times(1)).deleteTreatment(treatmentId);
    }


}
