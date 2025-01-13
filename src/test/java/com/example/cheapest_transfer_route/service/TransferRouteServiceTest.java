package com.example.cheapest_transfer_route.service;

import com.example.cheapest_transfer_route.dto.TransferRequest;
import com.example.cheapest_transfer_route.dto.TransferRequest.TransferDto;
import com.example.cheapest_transfer_route.exception.TransferRouteException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TransferRouteServiceTest {

    private final TransferRouteService service = new TransferRouteServiceImpl(); // Initialize the service to be tested

    // Test case for valid input where the service returns the correct response
    @Test
    void testValidInput() {
        // Prepare a valid TransferRequest with available transfers
        TransferRequest request = new TransferRequest();
        request.setMaxWeight(15);
        request.setAvailableTransfers(List.of(
                createTransfer(5, 10), // First transfer with weight 5 and cost 10
                createTransfer(10, 20) // Second transfer with weight 10 and cost 20
        ));

        // Call the service method to find the cheapest route
        var response = service.findCheapestRoute(request);

        // Assert the response is correct
        assertEquals(2, response.getSelectedTransfers().size()); // Check that two transfers are selected
        assertEquals(30, response.getTotalCost()); // Check that the total cost is correct
        assertEquals(15, response.getTotalWeight()); // Check that the total weight is correct
    }

    // Test case for when the request is null
    @Test
    void testNullRequest() {
        // Expecting a TransferRouteException to be thrown with the correct message
        Exception exception = assertThrows(TransferRouteException.class, () -> service.findCheapestRoute(null));
        assertEquals("Request cannot be null.", exception.getMessage());
    }

    // Test case for when the max weight is negative
    @Test
    void testNegativeMaxWeight() {
        // Prepare a request with negative maxWeight
        TransferRequest request = new TransferRequest();
        request.setMaxWeight(-1); // Invalid maxWeight
        request.setAvailableTransfers(List.of(createTransfer(5, 10)));

        // Expecting a TransferRouteException to be thrown with the correct message
        Exception exception = assertThrows(TransferRouteException.class, () -> service.findCheapestRoute(request));
        assertEquals("Maximum weight must be greater than 0.", exception.getMessage());
    }

    // Test case for transfers with zero weight or cost
    @Test
    void testZeroWeightAndCostTransfer() {
        // Prepare a request with transfers having zero weight or zero cost
        TransferRequest request = new TransferRequest();
        request.setMaxWeight(10);
        request.setAvailableTransfers(List.of(createTransfer(0, 10), createTransfer(5, 0))); // Invalid transfers

        // Expecting a TransferRouteException to be thrown for invalid weight or cost
        Exception exception = assertThrows(TransferRouteException.class, () -> service.findCheapestRoute(request));
        assertTrue(exception.getMessage().contains("Transfer weight must be greater than 0.") || // Check for invalid weight
                exception.getMessage().contains("Transfer cost must be greater than 0.")); // Check for invalid cost
    }

    // Test case for when the transfers list is empty
    @Test
    void testEmptyTransfersList() {
        // Prepare a request with an empty list of transfers
        TransferRequest request = new TransferRequest();
        request.setMaxWeight(10);
        request.setAvailableTransfers(List.of()); // Empty list of transfers

        // Expecting a TransferRouteException to be thrown with the correct message
        Exception exception = assertThrows(TransferRouteException.class, () -> service.findCheapestRoute(request));
        assertEquals("At least one transfer must be provided.", exception.getMessage());
    }

    // Helper method to create a TransferDto object
    private TransferDto createTransfer(int weight, int cost) {
        TransferDto transfer = new TransferDto();
        transfer.setWeight(weight); // Set weight
        transfer.setCost(cost); // Set cost
        return transfer; // Return the created TransferDto
    }
}
