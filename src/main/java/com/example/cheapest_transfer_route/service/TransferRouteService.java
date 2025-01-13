package com.example.cheapest_transfer_route.service;

import com.example.cheapest_transfer_route.dto.TransferRequest;
import com.example.cheapest_transfer_route.dto.TransferResponse;

// Service interface for finding the cheapest transfer route
public interface TransferRouteService {

    // Method to find the cheapest transfer route based on the provided request
    // Takes a TransferRequest object and returns a TransferResponse object
    TransferResponse findCheapestRoute(TransferRequest request);
}
