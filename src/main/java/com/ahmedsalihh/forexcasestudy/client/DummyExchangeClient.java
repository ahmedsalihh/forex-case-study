package com.ahmedsalihh.forexcasestudy.client;

import com.ahmedsalihh.forexcasestudy.model.ConversionApiResponse;
import com.ahmedsalihh.forexcasestudy.model.ExchangeRateResponse;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.Date;

@Configuration
public class DummyExchangeClient {
    public ExchangeRateResponse getExchangeRate(String from, String to) {
        ExchangeRateResponse response = new ExchangeRateResponse();
        response.setExchangeRate(27);
        response.setFrom(from);
        response.setTo(to);
        return response;
    }

    public ConversionApiResponse convert(String from, String to, double amount) {
        ConversionApiResponse conversionApiResponse = new ConversionApiResponse();
        conversionApiResponse.setAmount(amount);
        conversionApiResponse.setFrom(from);
        conversionApiResponse.setTo(to);
        conversionApiResponse.setTotalAmount(100);
        conversionApiResponse.setTransactionDate(LocalDate.now());

        return conversionApiResponse;
    }
}
