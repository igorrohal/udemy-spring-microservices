package com.irohal.springmicroservices.cloud.currenyexchangeservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/currency-exchange")
public class CurrencyExchangeController {

	private final Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);

	@Autowired
	private Environment environment;

	@Autowired
	private ExchangeRateRepository repository;

	@GetMapping("/from/{from}/to/{to}")
	public ExchangeRate exchangeRate(@PathVariable String from, @PathVariable String to) {
		final ExchangeRate exchangeRate = repository.findOneByFromAndTo(from, to);
		logger.info("{}", exchangeRate);
		exchangeRate.setPort(environment.getProperty("local.server.port", Integer.class));
		return exchangeRate;
	}

}
