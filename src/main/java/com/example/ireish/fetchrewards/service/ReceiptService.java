package com.example.ireish.fetchrewards.service;

import com.example.ireish.fetchrewards.model.Receipt;
import com.example.ireish.fetchrewards.repository.ReceiptRepository;
import com.example.ireish.fetchrewards.util.PointsCalculator;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReceiptService {
    private final ReceiptRepository receiptRepository;

    ReceiptService(ReceiptRepository receiptRepository) {
        this.receiptRepository = receiptRepository;
    }

    public Receipt saveReceipt(Receipt receipt) {
        return receiptRepository.save(receipt);
    }

    @Cacheable(value="pointsCache", key="#receiptId", unless="#result == -1")
    public long getPoints(String receiptId) {
        Optional<Receipt> receiptOptional = receiptRepository.findById(receiptId);
        return receiptOptional.map(PointsCalculator::calculatePoints).orElse(-1L);
    }
}
