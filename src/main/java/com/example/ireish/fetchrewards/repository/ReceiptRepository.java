package com.example.ireish.fetchrewards.repository;

import com.example.ireish.fetchrewards.model.Receipt;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class ReceiptRepository {
    private final Map<String, Receipt> receiptStore = new ConcurrentHashMap<>();
    public Receipt save(Receipt receipt) {
        String receiptId = UUID.randomUUID().toString();
        receipt.setId(receiptId);
        receiptStore.put(receiptId, receipt);
        return receipt;
    }

    public Optional<Receipt> findById(String id) {
        return Optional.ofNullable(receiptStore.get(id));
    }
}
