package com.irohal.springmicroservices.cloud.currenyexchangeservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/currency-exchange")
public class CurrencyExchangeController {

	@Autowired
	private Environment environment;

	@Autowired
	private ExchangeRateRepository repository;

	@GetMapping("/from/{from}/to/{to}")
	public ExchangeRate exchangeRate(@PathVariable String from, @PathVariable String to) {
		final ExchangeRate exchangeRate = repository.findOneByFromAndTo(from, to);
		exchangeRate.setPort(environment.getProperty("local.server.port", Integer.class));
		return exchangeRate;
	}

}
