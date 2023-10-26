package com.ahmedsalihh.forexcasestudy.model;

import lombok.Data;

import java.util.Date;

@Data
public class ConversionResponse {
    private float amount;
    private Date transactionDate;
}
