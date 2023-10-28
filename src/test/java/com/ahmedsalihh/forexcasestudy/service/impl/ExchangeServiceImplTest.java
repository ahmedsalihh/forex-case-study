package com.ahmedsalihh.forexcasestudy.service.impl;

import com.ahmedsalihh.forexcasestudy.client.DummyExchangeClient;
import com.ahmedsalihh.forexcasestudy.exception.DateNotRecognizedException;
import com.ahmedsalihh.forexcasestudy.model.Conversion;
import com.ahmedsalihh.forexcasestudy.model.ConversionApiResponse;
import com.ahmedsalihh.forexcasestudy.model.ExchangeRateResponse;
import com.ahmedsalihh.forexcasestudy.repository.ConversionRepository;
import com.ahmedsalihh.forexcasestudy.service.ExchangeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.isA;

@SpringBootTest
class ExchangeServiceImplTest {

    @Autowired
    private ExchangeService exchangeService;

    @MockBean
    private DummyExchangeClient client;

    @MockBean
    private ConversionRepository conversionRepository;

    public int pageNo;
    public int pageSize;
    public Long transactionId;
    public String transactionDate;
    public double amount;
    public String from;
    public String to;

    @BeforeEach
    void setUp() {
        pageNo = 0;
        pageSize = 10;
        transactionId = 1L;
        transactionDate = "2020-07-19";
        amount = 10;
        from = "USD";
        to = "TRY";
    }

    @Test
    void getExchangeRate() {
        ExchangeRateResponse expectedResponse = new ExchangeRateResponse();
        expectedResponse.setExchangeRate(10d);
        Mockito.when(client.getExchangeRate(Mockito.any(), Mockito.any())).thenReturn(expectedResponse);

        exchangeService.getExchangeRate(from, to);

        assertEquals(expectedResponse.getExchangeRate(), 10);
    }

    @Test
    void convert() {
        ConversionApiResponse response = new ConversionApiResponse(10d, "USD", "TRY", 100d, LocalDate.now());

        Mockito.when(client.convert(Mockito.any(), Mockito.any(), Mockito.anyDouble())).thenReturn(response);

        exchangeService.convert(from, to, amount);

        Mockito.verify(conversionRepository, Mockito.times(1)).save(Mockito.any(Conversion.class));
        assertNotNull(response);
    }

    @Test
    void listSuccess() {
        Page<Conversion> list = Mockito.mock(Page.class);

        Mockito.when(conversionRepository.findConversionsByTransactionIdAndTransactionDate(Mockito.anyLong(), Mockito.any(), isA(Pageable.class))).thenReturn(list);

        exchangeService.list(1L, transactionDate, pageNo, pageSize);

        assertNotNull(list);
    }

    @Test
    void listWithDateSuccess() {
        Page<Conversion> list = Mockito.mock(Page.class);

        Mockito.when(conversionRepository.findByTransactionDate(Mockito.any(), isA(Pageable.class))).thenReturn(list);

        exchangeService.list(null, transactionDate, pageNo, pageSize);

        assertNotNull(list);
    }

    @Test
    void listWithTransactionIdSuccess() {
        Page<Conversion> list = Mockito.mock(Page.class);

        Mockito.when(conversionRepository.findByTransactionId(Mockito.anyLong(), isA(Pageable.class))).thenReturn(list);

        exchangeService.list(1L, null, pageNo, pageSize);

        assertNotNull(list);
    }

    @Test
    void listDateNotRecognizedException() {
        transactionDate = "2020-07-1";
        Page<Conversion> list = Mockito.mock(Page.class);

        Mockito.when(conversionRepository.findConversionsByTransactionIdAndTransactionDate(Mockito.anyLong(), Mockito.any(), isA(Pageable.class))).thenReturn(list);

        DateNotRecognizedException ex = assertThrows(DateNotRecognizedException.class, () -> {
            exchangeService.list(1L, transactionDate, pageNo, pageSize);
        });

        assertEquals("Wrong date format. Date should be provided in 'yyyy-MM-dd' format", ex.getMessage());
    }
}