package com.ahmedsalihh.forexcasestudy.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ConversionApiResponse {
    private double amount;
    private String from;
    private String to;
    private double totalAmount;
    private LocalDate transactionDate;
}
