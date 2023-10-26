package com.ahmedsalihh.forexcasestudy.model;

import lombok.Data;

@Data
public class ExchangeRateResponse {
    private String from;
    private String to;
    private double exchangeRate;
}
