package com.example.ireish.fetchrewards.controller;

import com.example.ireish.fetchrewards.model.Receipt;
import com.example.ireish.fetchrewards.service.ReceiptService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/receipts")
public class ReceiptController {

    private final ReceiptService receiptService;

    ReceiptController(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @PostMapping(value = "/process", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> processReceipt(@Valid @RequestBody Receipt receipt) {
        Receipt receiptWithId = receiptService.saveReceipt(receipt);
        Map<String, String> body = new HashMap<>();
        body.put("id", receiptWithId.getId());
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/points", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getPoints(@PathVariable String id) {
        long points = receiptService.getPoints(id);

        if (points == -1) {
            return new ResponseEntity<>(Map.entry("error", "Receipt ID does not exist."), HttpStatus.NOT_FOUND);
        }
        Map<String, Long> body = new HashMap<>();
        body.put("points", points);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }
}
