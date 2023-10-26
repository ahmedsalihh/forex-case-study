package com.ahmedsalihh.forexcasestudy.client;

import com.ahmedsalihh.forexcasestudy.model.ConversionResponse;
import com.ahmedsalihh.forexcasestudy.model.ExchangeRateResponse;
import org.springframework.context.annotation.Configuration;

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

    public ConversionResponse convert(String from, String to, double amount) {
        ConversionResponse conversionResponse = new ConversionResponse();
        conversionResponse.setAmount(amount);
        conversionResponse.setFrom(from);
        conversionResponse.setTo(to);
        conversionResponse.setTotalAmount(100);
        conversionResponse.setTransactionDate(new Date());

        return conversionResponse;
    }
}
