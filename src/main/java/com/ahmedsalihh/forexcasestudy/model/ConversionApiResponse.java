package com.ahmedsalihh.forexcasestudy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConversionApiResponse {
    private double amount;
    private String from;
    private String to;
    private double totalAmount;
    private LocalDate transactionDate;
}
