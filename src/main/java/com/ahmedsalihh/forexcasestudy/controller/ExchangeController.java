package com.ahmedsalihh.forexcasestudy.controller;

import com.ahmedsalihh.forexcasestudy.exception.DateNotRecognizedException;
import com.ahmedsalihh.forexcasestudy.exception.NoParameterProvidedException;
import com.ahmedsalihh.forexcasestudy.model.Conversion;
import com.ahmedsalihh.forexcasestudy.model.ConversionResponse;
import com.ahmedsalihh.forexcasestudy.model.ExchangeRateResponse;
import com.ahmedsalihh.forexcasestudy.service.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<ConversionResponse> convert(@PathVariable("from") String from, @PathVariable("to") String to, @PathVariable("amount") double amount) {
        return ResponseEntity.ok(exchangeService.convert(from, to, amount));
    }

    @GetMapping("/list")
    public ResponseEntity<List<Conversion>> list(@RequestParam(name = "transactionId", required = false) Long transactionId,
                                                 @RequestParam(name = "transactionDate", required = false) String transactionDate,
                                                 @RequestParam(name = "pageNo", required = false, defaultValue = "0") int pageNo,
                                                 @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize) throws DateNotRecognizedException, NoParameterProvidedException {
        if (transactionId == null && transactionDate == null) {
            throw new NoParameterProvidedException("You should at least provide one parameter transactionId or transactionDate");
        }
        return ResponseEntity.ok(exchangeService.list(transactionId, transactionDate, pageNo, pageSize));
    }
}
