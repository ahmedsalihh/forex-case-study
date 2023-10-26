package com.ahmedsalihh.forexcasestudy.client;

import com.ahmedsalihh.forexcasestudy.model.ExchangeRateResponse;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DummyExchangeClient {
    public ExchangeRateResponse getExchangeRate(String from, String to) {
        ExchangeRateResponse response = new ExchangeRateResponse();
        response.setExchangeRate(27);
        response.setFrom(from);
        response.setTo(to);
        return response;
    }
}
