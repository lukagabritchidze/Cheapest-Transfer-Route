package com.example.cheapest_transfer_route.dto;

import java.util.List;

/**
 * DTO representing the input for calculating the cheapest transfer route.
 * Contains the maximum allowable weight and a list of available transfers.
 */
public class TransferRequest {

    // The maximum weight that can be handled in a single route
    private int maxWeight;

    // A list of available transfer options, each with weight and cost
    private List<TransferDto> availableTransfers;

    /**
     * Default constructor.
     * Used for object initialization without predefined values.
     */
    public TransferRequest() {}

    /**
     * Parameterized constructor to initialize a transfer request with specific values.
     *
     * @param maxWeight          The maximum allowable weight
     * @param availableTransfers The list of available transfer options
     */
    public TransferRequest(int maxWeight, List<TransferDto> availableTransfers) {
        this.maxWeight = maxWeight;
        this.availableTransfers = availableTransfers;
    }

    /**
     * Gets the maximum allowable weight for the route.
     *
     * @return The maximum weight
     */
    public int getMaxWeight() {
        return maxWeight;
    }

    /**
     * Sets the maximum allowable weight for the route.
     *
     * @param maxWeight The maximum weight to set
     */
    public void setMaxWeight(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    /**
     * Gets the list of available transfer options.
     *
     * @return A list of TransferDto objects
     */
    public List<TransferDto> getAvailableTransfers() {
        return availableTransfers;
    }

    /**
     * Sets the list of available transfer options.
     *
     * @param availableTransfers A list of TransferDto objects to set
     */
    public void setAvailableTransfers(List<TransferDto> availableTransfers) {
        this.availableTransfers = availableTransfers;
    }

    /**
     * Represents an individual transfer option, including weight and cost.
     */
    public static class TransferDto {

        // The weight that this transfer can handle
        private int weight;

        // The cost associated with this transfer
        private int cost;

        /**
         * Default constructor.
         * Used for object initialization without predefined values.
         */
        public TransferDto() {}

        /**
         * Parameterized constructor to initialize a transfer with specific values.
         *
         * @param weight The weight of the transfer
         * @param cost   The cost of the transfer
         */
        public TransferDto(int weight, int cost) {
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
