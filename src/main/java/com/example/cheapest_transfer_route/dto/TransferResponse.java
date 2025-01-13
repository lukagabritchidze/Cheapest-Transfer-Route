package com.example.cheapest_transfer_route.dto;

import java.util.List;

/**
 * DTO(data transfer object) for representing the response of the cheapest transfer route.
 */
public class TransferResponse {
    private List<SelectedTransfer> selectedTransfers; // List of transfers included in the route
    private int totalWeight; // Total weight of all selected transfers
    private int totalCost; // Total cost of all selected transfers

    public TransferResponse() {}

    public TransferResponse(List<SelectedTransfer> selectedTransfers, int totalWeight, int totalCost) {
        this.selectedTransfers = selectedTransfers;
        this.totalWeight = totalWeight;
        this.totalCost = totalCost;
    }

    public List<SelectedTransfer> getSelectedTransfers() {
        return selectedTransfers;
    }

    public void setSelectedTransfers(List<SelectedTransfer> selectedTransfers) {
        this.selectedTransfers = selectedTransfers;
    }

    public int getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(int totalWeight) {
        this.totalWeight = totalWeight;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    /**
     * Represents a single selected transfer in the response.
     */
    public static class SelectedTransfer {
        private int weight; // Weight of the transfer
        private int cost; // Cost of the transfer

        public SelectedTransfer() {}

        public SelectedTransfer(int weight, int cost) {
            this.weight = weight;
            this.cost = cost;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public int getCost() {
            return cost;
        }

        public void setCost(int cost) {
            this.cost = cost;
        }
    }
}
