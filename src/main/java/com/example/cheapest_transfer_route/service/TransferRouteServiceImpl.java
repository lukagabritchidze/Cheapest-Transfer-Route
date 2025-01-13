package com.example.cheapest_transfer_route.service;

import com.example.cheapest_transfer_route.dto.TransferRequest;
import com.example.cheapest_transfer_route.dto.TransferResponse;
import com.example.cheapest_transfer_route.entity.Transfer;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.example.cheapest_transfer_route.util.TransferRouteUtil.validateInput;

@Service
public class TransferRouteServiceImpl implements TransferRouteService {

    // Method to find the cheapest transfer route based on the request
    @Override
    public TransferResponse findCheapestRoute(TransferRequest request) {

        // Validate input request (e.g., check for null values, invalid data)
        validateInput(request);

        // Get the maximum weight constraint from the request
        int maxWeight = request.getMaxWeight();

        // Create a list of Transfer objects from the request's available transfers
        List<Transfer> transfers = new ArrayList<>();
        for (TransferRequest.TransferDto dto : request.getAvailableTransfers()) {
            transfers.add(new Transfer(dto.getWeight(), dto.getCost()));
        }

        // Dynamic programming (DP) approach to solve the knapsack problem
        int n = transfers.size(); // Number of available transfers
        int[][] dp = new int[n + 1][maxWeight + 1]; // DP table to store max cost for each weight limit

        // Fill the DP table
        for (int i = 1; i <= n; i++) {
            for (int w = 0; w <= maxWeight; w++) {
                // If the transfer's weight exceeds the current weight limit, carry over previous value
                if (transfers.get(i - 1).getWeight() > w) {
                    dp[i][w] = dp[i - 1][w];
                } else {
                    // Maximize the cost either by including or excluding the current transfer
                    dp[i][w] = Math.max(dp[i - 1][w],
                            dp[i - 1][w - transfers.get(i - 1).getWeight()] + transfers.get(i - 1).getCost());
                }
            }
        }

        // Backtrack through the DP table to find the selected transfers
        List<TransferResponse.SelectedTransfer> selectedTransfers = new ArrayList<>();
        int w = maxWeight;
        for (int i = n; i > 0; i--) {
            // If the transfer was selected (i.e., its inclusion increased the cost)
            if (dp[i][w] != dp[i - 1][w]) {
                Transfer t = transfers.get(i - 1);
                TransferResponse.SelectedTransfer selectedTransfer = new TransferResponse.SelectedTransfer();
                selectedTransfer.setWeight(t.getWeight()); // Set the weight of the selected transfer
                selectedTransfer.setCost(t.getCost()); // Set the cost of the selected transfer
                selectedTransfers.add(selectedTransfer);
                w -= t.getWeight(); // Reduce the remaining weight limit
            }
        }

        // Build the response containing selected transfers and the total cost and weight
        TransferResponse response = new TransferResponse();
        response.setSelectedTransfers(selectedTransfers); // Set the selected transfers
        response.setTotalCost(dp[n][maxWeight]); // Total cost of the selected transfers
        response.setTotalWeight(maxWeight - w); // Remaining weight after selecting the transfers

        // Return the response
        return response;
    }
}
