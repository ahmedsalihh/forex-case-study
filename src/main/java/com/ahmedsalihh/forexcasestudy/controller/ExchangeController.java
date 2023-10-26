package com.ahmedsalihh.forexcasestudy.controller;

import com.ahmedsalihh.forexcasestudy.model.ConversionResponse;
import com.ahmedsalihh.forexcasestudy.model.ExchangeRateResponse;
import com.ahmedsalihh.forexcasestudy.service.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/exchange")
public class ExchangeController {
    private ExchangeService exchangeService;

    @Autowired
    public ExchangeController(ExchangeService exchangeService) {
        this.exchangeService = exchangeService;
    }

    @GetMapping("/{from}/to/{to}")
    public ResponseEntity<ExchangeRateResponse> getExchangeRate(@PathVariable("from") String from, @PathVariable("to") String to) {
        return ResponseEntity.ok(exchangeService.getExchangeRate(from, to));
    }

    @GetMapping("/convert/{from}/to/{to}/{amount}")
    public ResponseEntity<ConversionResponse> convert(@PathVariable("from") String from, @PathVariable("to") String to, @PathVariable("amount") String amount) {
        return ResponseEntity.ok(exchangeService.convert(from, to, amount));
    }
}
