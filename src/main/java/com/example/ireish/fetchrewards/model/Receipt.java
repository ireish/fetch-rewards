package com.example.ireish.fetchrewards.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

import java.util.List;

public class Receipt {
    private String id;

    @Pattern(regexp = "^[\\w\\s\\-&]+$", message = "Invalid retailer name at ${validatedValue}. Only letters, numbers, spaces, ampersand, and hyphens are allowed.")
    private String retailer;

    @ValidDate
    private String purchaseDate;

    @Pattern(regexp = "^(?:[01]\\d|2[0-3]):[0-5]\\d$", message = "Invalid purchased time format. Use HH:mm in 24-hour format")
    private String purchaseTime;

    @Pattern(regexp = "^\\d+\\.\\d{2}$", message = "Invalid total format at ${validatedValue}. It should be a number with two decimal places (e.g., 12.34).")
    private String total;

    @NotEmpty(message = "Receipt should have at least one item.")
    private List<@Valid Item> items;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRetailer() {
        return retailer;
    }

    public void setRetailer(String retailer) {
        this.retailer = retailer;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(String purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}