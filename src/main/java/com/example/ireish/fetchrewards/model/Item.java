package com.example.ireish.fetchrewards.model;

import jakarta.validation.constraints.Pattern;

public class Item {

    @Pattern(regexp = "^[\\w\\s\\-]+$", message = "Invalid description format at ${validatedValue}. Only letters, numbers, spaces, and hyphens are allowed.")
    private String shortDescription;

    @Pattern(regexp = "^\\d+\\.\\d{2}$", message = "Invalid price format at ${validatedValue}. It should be a number with two decimal places (e.g., 12.34).")
    private String price;

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}