package com.example.cheapest_transfer_route.controller;

import com.example.cheapest_transfer_route.dto.TransferRequest;
import com.example.cheapest_transfer_route.dto.TransferResponse;
import com.example.cheapest_transfer_route.service.TransferRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class to handle HTTP requests related to finding the cheapest transfer route.
 * This class exposes REST endpoints to interact with the application.
 */
@RestController
@RequestMapping("/api/cheapest-route") // Base path for all endpoints in this controller
public class TransferRouteController {

    private final TransferRouteService transferRouteService;

    /**
     * Constructor to inject the TransferRouteService dependency.
     *
     * @param transferRouteService Service layer to handle business logic for cheapest route calculation
     */
    @Autowired
    public TransferRouteController(TransferRouteService transferRouteService) {
        this.transferRouteService = transferRouteService;
    }

    /**
     * Endpoint to calculate the cheapest transfer route based on the provided request.
     *
     * @param request The TransferRequest containing the inputs for the calculation
     * @return A TransferResponse object containing the details of the selected route
     */
    @PostMapping("/cheapest-route") // Defines this method as a POST endpoint at /cheapest-route
    public TransferResponse findCheapestRoute(@RequestBody TransferRequest request) {
        // Delegates the request to the service layer to process the business logic
        return transferRouteService.findCheapestRoute(request);
    }
}

