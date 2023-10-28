package com.ahmedsalihh.forexcasestudy.controller;

import com.ahmedsalihh.forexcasestudy.model.Conversion;
import com.ahmedsalihh.forexcasestudy.model.ConversionResponse;
import com.ahmedsalihh.forexcasestudy.model.ExchangeRateResponse;
import com.ahmedsalihh.forexcasestudy.service.ExchangeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Assert;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class ExchangeControllerTest {

    @Autowired
    private ExchangeController exchangeController;

    @MockBean
    private ExchangeService exchangeService;

    private MockMvc mockMvc;
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
        from= "USD";
        to = "TRY";
        this.mockMvc = MockMvcBuilders.standaloneSetup(exchangeController).build();
    }

    @Test
    void getExchangeRate() throws Exception {
        ExchangeRateResponse expectedResponse = new ExchangeRateResponse();
        expectedResponse.setExchangeRate(6.8614807);
        Mockito.when(exchangeService.getExchangeRate(from, to)).thenReturn(expectedResponse);

        mockMvc.perform(get("/exchange/" + from + "/to/" + to).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void convert() throws Exception {
        ConversionResponse expectedResponse = new ConversionResponse();
        expectedResponse.setTransactionId(1l);
        expectedResponse.setConvertedAmount(12345);

        Mockito.when(exchangeService.convert(Mockito.any(), Mockito.any(), Mockito.anyDouble())).thenReturn(expectedResponse);

        mockMvc.perform(get("/exchange/convert/" + from + "/to/" + to + "/" + amount).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(mvcResult -> {
                    String jsonRes = mvcResult.getResponse().getContentAsString();
                    String a = JsonPath.parse(jsonRes).read("convertedAmount").toString();
                    String b = JsonPath.parse(jsonRes).read("transactionId").toString();
                    Assert.isTrue(a.equals("12345.0"), "");
                    Assert.isTrue(b.equals("1"), "");
                });
    }

    @Test
    void listByTransactionIdAndTransactionDateSuccess() throws Exception {
        Conversion conversion = new Conversion();
        conversion.setTransactionId(1L);

        List<Conversion> list = new ArrayList<>();
        list.add(conversion);

        Mockito.when(exchangeService.list(transactionId, transactionDate, pageNo, pageSize)).thenReturn(list);

        mockMvc.perform(get("/exchange/list?transactionId=" + transactionId + "&transactionDate=" + transactionDate + "&pageNo=" + pageNo + "&pageSize=" + pageSize)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void listByTransactionIdAndTransactionDateNoParameterException() throws Exception {
        Conversion conversion = new Conversion();
        conversion.setTransactionId(1L);

        List<Conversion> list = new ArrayList<>();
        list.add(conversion);

        Mockito.when(exchangeService.list(transactionId, transactionDate, pageNo, pageSize)).thenReturn(list);

        mockMvc.perform(get("/exchange/list?pageNo=" + pageNo + "&pageSize=" + pageSize)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
}