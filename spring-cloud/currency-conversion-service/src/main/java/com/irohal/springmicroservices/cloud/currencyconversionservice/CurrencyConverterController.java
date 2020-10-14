package com.irohal.springmicroservices.cloud.currencyconversionservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/currency-converter")
public class CurrencyConverterController {

    @GetMapping("/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean convert(
            @PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
        final BigDecimal conversionMultipleHC = BigDecimal.valueOf(15.0);
        final int portHC = 0;

        final CurrencyConversionBean currencyConversionBean = new CurrencyConversionBean();
        currencyConversionBean.setFrom(from);
        currencyConversionBean.setTo(to);
        currencyConversionBean.setQuantity(quantity);
        currencyConversionBean.setConversionMultiple(conversionMultipleHC);
        currencyConversionBean.setTotalAmount(quantity.multiply(conversionMultipleHC));
        currencyConversionBean.setPort(portHC);
        return currencyConversionBean;
    }

}
