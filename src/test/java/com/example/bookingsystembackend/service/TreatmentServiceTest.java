package com.example.bookingsystembackend.service;
import com.example.bookingsystembackend.entity.Treatment;
import com.example.bookingsystembackend.repositories.TreatmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


class TreatmentServiceTest {

    @Mock
    private TreatmentRepository treatmentRepository;

    @InjectMocks
    private TreatmentService treatmentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllTreatments_ReturnListOfTreatments() {
        // Arrange
        Treatment treatment1 = new Treatment(1, "Behandling 1", "Beskrivelse af behandling", 120, 500);
        Treatment treatment2 = new Treatment(1, "Behandling 2", "Beskrivelse af behandling", 60, 250);

        List<Treatment> mockTreatments = Arrays.asList(treatment1, treatment2);

        // Stubbing the repository method to return the mocked treatments
        when(treatmentRepository.findAll()).thenReturn(mockTreatments);

        // Act
        List<Treatment> retrievedTreatments = treatmentService.getAllTreatments();

        // Assert
        assertEquals(2, retrievedTreatments.size());
        assertEquals("Behandling 1", retrievedTreatments.get(0).getName());
        assertEquals("Behandling 2", retrievedTreatments.get(1).getName());

        verify(treatmentRepository, times(1)).findAll();
    }

    @Test
    public void testGetTreatmentById_ReturnTreatmentWhenValidId() {
        // Arrange
        int treatmentId = 1;
        Treatment expectedTreatment = new Treatment();
        expectedTreatment.setTreatmentId(treatmentId);
        expectedTreatment.setName("Behandling 1");

        // Stubbing the repository method to return the mocked treatment when findById is called with treatmentId
        when(treatmentRepository.findByTreatmentId(treatmentId)).thenReturn(expectedTreatment);

        // Act
        Treatment retrievedTreatment = treatmentService.getTreatmentById(treatmentId);

        // Assert
        assertEquals(expectedTreatment, retrievedTreatment);
        assertEquals("Behandling 1", retrievedTreatment.getName());

        verify(treatmentRepository, times(1)).findByTreatmentId(treatmentId);
    }

    @Test
    public void testGetTreatmentById_ReturnNullWhenInvalidId() {
        // Arrange
        int invalidTreatmentId = 999; // Assuming this ID does not exist in the system

        // Stubbing the repository method to return null when findById is called with an invalid ID
        when(treatmentRepository.findByTreatmentId(invalidTreatmentId)).thenReturn(null);

        // Act
        Treatment retrievedTreatment = treatmentService.getTreatmentById(invalidTreatmentId);

        // Assert
        assertEquals(null, retrievedTreatment);

        verify(treatmentRepository, times(1)).findByTreatmentId(invalidTreatmentId);
    }




}