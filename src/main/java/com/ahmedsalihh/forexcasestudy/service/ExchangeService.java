package com.ahmedsalihh.forexcasestudy.service;

import com.ahmedsalihh.forexcasestudy.model.Conversion;
import com.ahmedsalihh.forexcasestudy.model.ExchangeRateResponse;

public interface ExchangeService {
    ExchangeRateResponse getExchangeRate(String from, String to);

    Conversion convert(String from, String to, double amount);
}
