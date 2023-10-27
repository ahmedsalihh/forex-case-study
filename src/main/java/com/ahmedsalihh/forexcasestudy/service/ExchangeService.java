package com.ahmedsalihh.forexcasestudy.service;

import com.ahmedsalihh.forexcasestudy.exception.DateNotRecognizedException;
import com.ahmedsalihh.forexcasestudy.model.Conversion;
import com.ahmedsalihh.forexcasestudy.model.ConversionResponse;
import com.ahmedsalihh.forexcasestudy.model.ExchangeRateResponse;

import java.util.List;

public interface ExchangeService {
    ExchangeRateResponse getExchangeRate(String from, String to);

    ConversionResponse convert(String from, String to, double amount);

    List<Conversion> list(Long transactionId, String transactionDate, int pageNo, int pageSize) throws DateNotRecognizedException;
}
