package my.zilch.interview.application.service;

import my.zilch.interview.application.client.model.CurrencyCode;
import my.zilch.interview.application.model.Rate;

import java.time.LocalDate;
import java.util.Optional;

public interface RateService {
    Optional<Rate> getExchangeRateFor(CurrencyCode currencyCode, LocalDate date);
}
