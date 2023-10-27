package com.ahmedsalihh.forexcasestudy.service.impl;

import com.ahmedsalihh.forexcasestudy.client.DummyExchangeClient;
import com.ahmedsalihh.forexcasestudy.converter.ConversionConverter;
import com.ahmedsalihh.forexcasestudy.model.Conversion;
import com.ahmedsalihh.forexcasestudy.model.ConversionApiResponse;
import com.ahmedsalihh.forexcasestudy.model.ConversionResponse;
import com.ahmedsalihh.forexcasestudy.model.ExchangeRateResponse;
import com.ahmedsalihh.forexcasestudy.repository.ConversionRepository;
import com.ahmedsalihh.forexcasestudy.service.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExchangeServiceImpl implements ExchangeService {

    private DummyExchangeClient client;
    private ConversionRepository conversionRepository;

    @Autowired
    public ExchangeServiceImpl(DummyExchangeClient client, ConversionRepository conversionRepository) {
        this.client = client;
        this.conversionRepository = conversionRepository;
    }

    @Override
    public ExchangeRateResponse getExchangeRate(String from, String to) {
        return client.getExchangeRate(from, to);
    }

    @Override
    public ConversionResponse convert(String from, String to, double amount) {
        ConversionApiResponse conversionApiResponse = client.convert(from, to, amount);

        Conversion conversion = ConversionConverter.toConversion(conversionApiResponse);

        conversionRepository.save(conversion);

        ConversionResponse conversionResponse = ConversionConverter.toConversionResponse(conversion);

        return conversionResponse;
    }
}
