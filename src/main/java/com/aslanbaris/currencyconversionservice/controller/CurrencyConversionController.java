package com.aslanbaris.currencyconversionservice.controller;

import com.aslanbaris.currencyconversionservice.CurrencyExchangeServiceProxy;
import com.aslanbaris.currencyconversionservice.dto.CurrencyConversionDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@Slf4j
@RestController
public class CurrencyConversionController {

    @Autowired
    private CurrencyExchangeServiceProxy proxy;

    @GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionDTO convertCurrency(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
        CurrencyConversionDTO response = proxy.retrieveExchangeValue(from, to);

        log.info("response: {}", response);

        return new CurrencyConversionDTO(response.getId(), from, to, response.getConversionMultiple(), quantity, quantity.multiply(response.getConversionMultiple()), response.getPort());
    }
}
