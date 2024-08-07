package com.example.ireish.fetchrewards.util;

import com.example.ireish.fetchrewards.model.Item;
import com.example.ireish.fetchrewards.model.Receipt;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class PointsCalculator {

    public static long calculatePoints(Receipt receipt) {
        long points = 0;

        // One point for every alphanumeric character in the retailer name
        points += receipt.getRetailer().replaceAll("[^a-zA-Z0-9]", "").length();

        // 50 points if the total is a round dollar amount with no cents
        BigDecimal total = new BigDecimal(receipt.getTotal());
        if (total.scale() == 0) {
            points += 50;
        }

        // 25 points if the total is a multiple of 0.25
        if (total.remainder(BigDecimal.valueOf(0.25)).compareTo(BigDecimal.ZERO) == 0) {
            points += 25;
        }

        // 5 points for every two items on the receipt
        points += (receipt.getItems().size() / 2) * 5L;

        // Points based on item description length
        for (Item item : receipt.getItems()) {
            String trimmedDescription = item.getShortDescription().trim();
            if (trimmedDescription.length() % 3 == 0) {
                BigDecimal price = new BigDecimal(item.getPrice());
                points += price.multiply(BigDecimal.valueOf(0.2)).setScale(0, RoundingMode.UP).longValue();
            }
        }

        // 6 points if the day in the purchase date is odd
        int dayOfMonth = Integer.parseInt(receipt.getPurchaseDate().split("-")[2]);
        if (dayOfMonth % 2 != 0) {
            points += 6;
        }

        // 10 points if the time of purchase is after 2:00pm and before 4:00pm
        LocalTime purchaseTime = LocalTime.parse(receipt.getPurchaseTime(), DateTimeFormatter.ofPattern("HH:mm"));
        if (purchaseTime.isAfter(LocalTime.of(14, 0)) && purchaseTime.isBefore(LocalTime.of(16, 0))) {
            points += 10;
        }

        return points;
    }
}
