package my.zilch.interview.application.service;

import lombok.RequiredArgsConstructor;
import my.zilch.interview.application.client.NbpRatesClient;
import my.zilch.interview.application.client.model.CurrencyCode;
import my.zilch.interview.application.client.model.NbpRate;
import my.zilch.interview.application.model.Rate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NbpRateService implements RateService {

    private final NbpRatesClient nbpRatesClient;

    public Optional<Rate> getExchangeRateFor(CurrencyCode currencyCode, LocalDate date) {
        List<NbpRate> nbpRates = nbpRatesClient.getExchangeRateFor(currencyCode, date).getRates();

        return nbpRates.stream()
                .findFirst()
                .map(nbpRate -> Rate.builder()
                        .date(nbpRate.getEffectiveDate())
                        .buyPrice(nbpRate.getBid())
                        .sellPrice(nbpRate.getAsk())
                        .build());
    }
}
