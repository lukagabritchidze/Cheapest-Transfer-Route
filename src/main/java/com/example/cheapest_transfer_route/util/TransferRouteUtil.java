package com.example.cheapest_transfer_route.util;

import com.example.cheapest_transfer_route.dto.TransferRequest;
import com.example.cheapest_transfer_route.exception.TransferRouteException;

public class TransferRouteUtil {

    // Validate the input request to ensure all fields are valid
    public static void validateInput(TransferRequest request) {
        // Check if the request is null
        if (request == null) {
            throw new TransferRouteException("Request cannot be null.");
        }

        // Check if the maximum weight is valid (greater than 0)
        if (request.getMaxWeight() <= 0) {
            throw new TransferRouteException("Maximum weight must be greater than 0.");
        }

        // Check if at least one transfer is provided in the request
        if (request.getAvailableTransfers() == null || request.getAvailableTransfers().isEmpty()) {
            throw new TransferRouteException("At least one transfer must be provided.");
        }

        // Validate each transfer in the available transfers list
        request.getAvailableTransfers().forEach(transfer -> {
            // Check if the transfer's weight is greater than 0
            if (transfer.getWeight() <= 0) {
                throw new TransferRouteException("Transfer weight must be greater than 0.");
            }
            // Check if the transfer's cost is greater than 0
            if (transfer.getCost() <= 0) {
                throw new TransferRouteException("Transfer cost must be greater than 0.");
            }
        });
    }
}



