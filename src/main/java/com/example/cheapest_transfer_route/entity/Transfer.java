package com.example.cheapest_transfer_route.entity;

/**
 * Entity class representing a transfer option with weight and cost details.
 * This class is used to encapsulate the data of individual transfer options.
 */
public class Transfer {

    private int weight; // The weight capacity of this transfer
    private int cost;   // The cost associated with this transfer

    /**
     * Constructor to initialize a transfer with specific weight and cost.
     *
     * @param weight The weight capacity of the transfer
     * @param cost   The cost of the transfer
     */
    public Transfer(int weight, int cost) {
        this.weight = weight;
        this.cost = cost;
    }

    /**
     * Gets the weight capacity of the transfer.
     *
     * @return The weight capacity
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Gets the cost of the transfer.
     *
     * @return The cost of the transfer
     */
    public int getCost() {
        return cost;
    }
}

