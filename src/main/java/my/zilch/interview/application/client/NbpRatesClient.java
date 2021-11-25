package my.zilch.interview.application.client;

import my.zilch.interview.application.client.model.CurrencyCode;
import my.zilch.interview.application.client.model.NbpRateRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDate;

@FeignClient(name = "nbpRate", url = "${nbp.rate.service.url}")
public interface NbpRatesClient {

    @RequestMapping(method = RequestMethod.GET, value = "/api/exchangerates/rates/c/{currencyCode}/{date}/?format=json", consumes = "application/json", produces = "application/json")
    NbpRateRecord getExchangeRateFor(@PathVariable("currencyCode") CurrencyCode currencyCode,
                                     @PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date);
}
