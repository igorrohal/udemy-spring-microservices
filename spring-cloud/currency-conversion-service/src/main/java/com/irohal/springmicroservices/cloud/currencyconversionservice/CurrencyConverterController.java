package com.irohal.springmicroservices.cloud.currencyconversionservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CurrencyConverterController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CurrencyExchangeServiceProxy currencyExchangeProxy;

    @GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean convertWithFeign(
            @PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
        final CurrencyConversionBean currencyConversionBean = currencyExchangeProxy.exchangeRate(from, to);
        currencyConversionBean.setQuantity(quantity);
        currencyConversionBean.setTotalCalculatedAmount(quantity.multiply(currencyConversionBean.getConversionMultiple()));

        logger.info("{}", currencyConversionBean);

        return currencyConversionBean;
    }

    @GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean convert(
            @PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
        final Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);

        final ResponseEntity<CurrencyConversionBean> response =
                new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}",
                        CurrencyConversionBean.class, uriVariables);

        logger.info("{}", response);

        final CurrencyConversionBean currencyConversionBean = response.getBody();
        currencyConversionBean.setQuantity(quantity);
        currencyConversionBean.setTotalCalculatedAmount(quantity.multiply(currencyConversionBean.getConversionMultiple()));

        return currencyConversionBean;
    }

}
