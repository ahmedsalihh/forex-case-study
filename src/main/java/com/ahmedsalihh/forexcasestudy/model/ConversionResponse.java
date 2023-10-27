package com.ahmedsalihh.forexcasestudy.model;

import lombok.Data;

@Data
public class ConversionResponse {
    private Long transactionId;
    private double convertedAmount;
}
