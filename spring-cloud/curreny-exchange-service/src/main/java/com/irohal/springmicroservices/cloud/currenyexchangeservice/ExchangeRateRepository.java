package com.irohal.springmicroservices.cloud.currenyexchangeservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.math.BigDecimal;

@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {

    public ExchangeRate findOneByFromAndTo(String from, String to);

}
