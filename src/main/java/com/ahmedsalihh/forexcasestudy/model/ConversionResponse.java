package com.ahmedsalihh.forexcasestudy.model;

import lombok.Data;

import java.util.Date;

@Data
public class ConversionResponse {
    private double amount;
    private String from;
    private String to;
    private double totalAmount;
    private Date transactionDate;
}
