package com.example.cheapest_transfer_route.controller;

import com.example.cheapest_transfer_route.dto.TransferRequest;
import com.example.cheapest_transfer_route.dto.TransferResponse;
import com.example.cheapest_transfer_route.exception.TransferRouteException;
import com.example.cheapest_transfer_route.service.TransferRouteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransferRouteControllerTest {

    private TransferRouteController controller;
    private TransferRouteService service;

    // Set up method to initialize the controller and mock service before each test
    @BeforeEach
    void setUp() {
        service = mock(TransferRouteService.class); // Mock the TransferRouteService to simulate its behavior
        controller = new TransferRouteController(service); // Create an instance of the controller using the mocked service
    }

    // Test case for a valid request where the service returns the expected response
    @Test
    public void testValidRequest() {
        // Prepare a valid input TransferRequest
        TransferRequest.TransferDto transfer1 = new TransferRequest.TransferDto(5, 10);
        TransferRequest.TransferDto transfer2 = new TransferRequest.TransferDto(10, 20);
        TransferRequest request = new TransferRequest(15, List.of(transfer1, transfer2));

        // Define the expected TransferResponse
        TransferResponse.SelectedTransfer selectedTransfer1 = new TransferResponse.SelectedTransfer(5, 10);
        TransferResponse.SelectedTransfer selectedTransfer2 = new TransferResponse.SelectedTransfer(10, 20);
        TransferResponse expectedResponse = new TransferResponse(List.of(selectedTransfer1, selectedTransfer2), 15, 30);

        // Stub the service method to return the expected response when called
        when(service.findCheapestRoute(request)).thenReturn(expectedResponse);

        // Call the controller method and get the actual response
        TransferResponse actualResponse = controller.findCheapestRoute(request);

        // Assert that the response is not null
        assertNotNull(actualResponse, "Response should not be null");
        // Assert the total weight and cost in the response match the expected values
        assertEquals(expectedResponse.getTotalWeight(), actualResponse.getTotalWeight(), "Total weight mismatch");
        assertEquals(expectedResponse.getTotalCost(), actualResponse.getTotalCost(), "Total cost mismatch");

        // Compare the selected transfers in the expected and actual response
        List<TransferResponse.SelectedTransfer> expectedTransfers = expectedResponse.getSelectedTransfers();
        List<TransferResponse.SelectedTransfer> actualTransfers = actualResponse.getSelectedTransfers();
        assertEquals(expectedTransfers.size(), actualTransfers.size(), "Mismatch in number of selected transfers");

        // Assert that each selected transfer's weight and cost match the expected values
        for (int i = 0; i < expectedTransfers.size(); i++) {
            assertEquals(expectedTransfers.get(i).getWeight(), actualTransfers.get(i).getWeight(), "Weight mismatch");
            assertEquals(expectedTransfers.get(i).getCost(), actualTransfers.get(i).getCost(), "Cost mismatch");
        }
    }

    // Test case for invalid maximum weight in the request
    @Test
    void testInvalidMaxWeight() {
        // Arrange: Create a request with an invalid maxWeight
        TransferRequest request = new TransferRequest();
        request.setMaxWeight(-1); // Invalid maxWeight

        // Stub the service to throw an exception when called with the invalid request
        when(service.findCheapestRoute(request)).thenThrow(new TransferRouteException("Maximum weight must be greater than 0."));

        // Act & Assert: Expect a TransferRouteException to be thrown when calling the controller
        TransferRouteException exception = assertThrows(TransferRouteException.class, () -> controller.findCheapestRoute(request));
        assertEquals("Maximum weight must be greater than 0.", exception.getMessage());
        // Verify that the service method was called once
        verify(service, times(1)).findCheapestRoute(request);
    }

    // Test case for an empty transfer list in the request
    @Test
    void testEmptyTransferList() {
        // Arrange: Create a request with an empty list of transfers
        TransferRequest request = new TransferRequest();
        request.setMaxWeight(15);
        request.setAvailableTransfers(List.of()); // Empty list

        // Stub the service to throw an exception when called with the empty list
        when(service.findCheapestRoute(request)).thenThrow(new TransferRouteException("At least one transfer must be provided."));

        // Act & Assert: Expect a TransferRouteException to be thrown when calling the controller
        TransferRouteException exception = assertThrows(TransferRouteException.class, () -> controller.findCheapestRoute(request));
        assertEquals("At least one transfer must be provided.", exception.getMessage());
        // Verify that the service method was called once
        verify(service, times(1)).findCheapestRoute(request);
    }

    // Test case for a null request
    @Test
    void testNullRequest() {
        // Arrange: Stub the service to throw an exception when a null request is passed
        when(service.findCheapestRoute(null)).thenThrow(new TransferRouteException("Request cannot be null."));

        // Act & Assert: Expect a TransferRouteException to be thrown when calling the controller with a null request
        TransferRouteException exception = assertThrows(TransferRouteException.class, () -> controller.findCheapestRoute(null));
        assertEquals("Request cannot be null.", exception.getMessage());
        // Verify that the service method was called once
        verify(service, times(1)).findCheapestRoute(null);
    }

    // Test case for an invalid transfer weight in the request
    @Test
    void testInvalidTransferWeights() {
        // Arrange: Create a request with an invalid transfer weight
        TransferRequest request = new TransferRequest();
        request.setMaxWeight(15);
        TransferRequest.TransferDto transfer1 = new TransferRequest.TransferDto(-5, 10); // Invalid weight
        request.setAvailableTransfers(List.of(transfer1));

        // Stub the service to throw an exception when called with the invalid transfer weight
        when(service.findCheapestRoute(request)).thenThrow(new TransferRouteException("Transfer weight must be greater than 0."));

        // Act & Assert: Expect a TransferRouteException to be thrown when calling the controller
        TransferRouteException exception = assertThrows(TransferRouteException.class, () -> controller.findCheapestRoute(request));
        assertEquals("Transfer weight must be greater than 0.", exception.getMessage());
        // Verify that the service method was called once
        verify(service, times(1)).findCheapestRoute(request);
    }
}
