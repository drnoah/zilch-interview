package my.zilch.interview.application.gateway;

import lombok.RequiredArgsConstructor;
import my.zilch.interview.application.client.model.CurrencyCode;
import my.zilch.interview.application.model.Rate;
import my.zilch.interview.application.service.NbpRateService;
import my.zilch.interview.application.service.RateService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class RatesController {

    private final RateService nbpRateService;

    @GetMapping(value = "/currencyRate/{currencyCode}/{date}", produces = "application/json")
    ResponseEntity<Rate> getCurrencyRate(@PathVariable("currencyCode") CurrencyCode currencyCode,
                                         @DateTimeFormat(pattern = "yyyy-MM-dd")
                                         @PathVariable("date") LocalDate date) {

        Optional<Rate> rate = nbpRateService.getExchangeRateFor(currencyCode, date);

        return rate.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }
}
