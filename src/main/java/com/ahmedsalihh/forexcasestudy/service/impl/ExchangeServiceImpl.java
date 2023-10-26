package com.ahmedsalihh.forexcasestudy.service.impl;

import com.ahmedsalihh.forexcasestudy.client.DummyExchangeClient;
import com.ahmedsalihh.forexcasestudy.model.ExchangeRateResponse;
import com.ahmedsalihh.forexcasestudy.service.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExchangeServiceImpl implements ExchangeService {

    private DummyExchangeClient client;

    @Autowired
    public ExchangeServiceImpl(DummyExchangeClient client) {
        this.client = client;
    }

    @Override
    public ExchangeRateResponse getExchangeRate(String from, String to) {
        return client.getExchangeRate(from, to);
    }
}
