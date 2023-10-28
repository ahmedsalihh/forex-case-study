package com.ahmedsalihh.forexcasestudy.service.impl;

import com.ahmedsalihh.forexcasestudy.client.DummyExchangeClient;
import com.ahmedsalihh.forexcasestudy.converter.ConversionConverter;
import com.ahmedsalihh.forexcasestudy.exception.DateNotRecognizedException;
import com.ahmedsalihh.forexcasestudy.model.Conversion;
import com.ahmedsalihh.forexcasestudy.model.ConversionApiResponse;
import com.ahmedsalihh.forexcasestudy.model.ConversionResponse;
import com.ahmedsalihh.forexcasestudy.model.ExchangeRateResponse;
import com.ahmedsalihh.forexcasestudy.repository.ConversionRepository;
import com.ahmedsalihh.forexcasestudy.service.ExchangeService;
import com.ahmedsalihh.forexcasestudy.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ExchangeServiceImpl implements ExchangeService {

    private DummyExchangeClient client;
    private ConversionRepository conversionRepository;

    private static String DEFAULT_DATE = "2100-01-01";

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

    @Override
    public List<Conversion> list(Long transactionId, String transactionDate, int pageNo, int pageSize) throws DateNotRecognizedException {
        Page<Conversion> pagedResult;
        LocalDate date;

        if (transactionDate != null && !transactionDate.isEmpty()) {
            date = DateUtils.formatDate(transactionDate);
        } else {
            date = DateUtils.formatDate(DEFAULT_DATE);
        }

        Pageable paging = PageRequest.of(pageNo, pageSize);

        if (transactionId == null) {
            pagedResult = conversionRepository.findByTransactionDate(date, paging);
        } else if (transactionDate == null || transactionDate.isEmpty()) {
            pagedResult = conversionRepository.findByTransactionId(transactionId, paging);
        } else {
            pagedResult = conversionRepository.findConversionsByTransactionIdAndTransactionDate(transactionId, date, paging);
        }

        return pagedResult.toList();
    }
}
