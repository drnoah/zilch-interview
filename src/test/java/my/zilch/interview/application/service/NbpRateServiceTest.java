package my.zilch.interview.application.service;

import my.zilch.interview.application.client.NbpRatesClient;
import my.zilch.interview.application.client.model.CurrencyCode;
import my.zilch.interview.application.client.model.NbpRate;
import my.zilch.interview.application.client.model.NbpRateRecord;
import my.zilch.interview.application.model.Rate;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class NbpRateServiceTest {

    private final NbpRatesClient nbpRatesClient = mock(NbpRatesClient.class);
    private final RateService nbpRateService = new NbpRateService(nbpRatesClient);

    @Test
    void shouldReturnCorrectlyTransformedNbpRate() {
        //given
        CurrencyCode currencyCode = CurrencyCode.GBP;
        LocalDate date = LocalDate.now();
        NbpRate nbpRate = NbpRate.builder().ask(3.0).bid(2.0).effectiveDate("2020-01-01").build();
        NbpRateRecord nbpRateRecord = NbpRateRecord.builder().currencyCode("GBP").rates(List.of(nbpRate)).build();
        when(nbpRatesClient.getExchangeRateFor(currencyCode, date)).thenReturn(nbpRateRecord);
        Rate expectedRate = Rate.builder().buyPrice(2.0).sellPrice(3.0).date("2020-01-01").build();

        //when
        Optional<Rate> rate = nbpRateService.getExchangeRateFor(currencyCode, date);

        //then
        assertThat(rate).isPresent();
        assertThat(rate.get()).isEqualTo(expectedRate);
    }

    @Test
    void shouldReturnEmptyOptionalIfNoDataAvailable() {
        //given
        CurrencyCode currencyCode = CurrencyCode.GBP;
        LocalDate date = LocalDate.now();
        when(nbpRatesClient.getExchangeRateFor(currencyCode, date)).thenReturn(NbpRateRecord.builder().build());

        //when
        Optional<Rate> rate = nbpRateService.getExchangeRateFor(currencyCode, date);

        //then
        assertThat(rate).isNotPresent();
    }


//     wireMockServer.stubFor(get(urlPathEqualTo("/api/exchangerates/rates/c/GBP/2020-02-04/?format=json"))
//            .willReturn(aResponse().withHeader("Content-Type", "text/plain")
//                        .withStatus(200)
//                        .withBodyFile("wiremock/success-nbp-response.json")));

//    private WireMockServer wireMockServer;
//
//    @BeforeEach
//    public void setup() {
//        wireMockServer = new WireMockServer(8090);
//        wireMockServer.start();
//    }
//    @AfterEach
//    public void teardown() {
//        wireMockServer.stop();
//    }
}