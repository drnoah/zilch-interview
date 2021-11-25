package my.zilch.interview.application.gateway;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
class RatesControllerIT {

    @Autowired
    private MockMvc mockMvc;

    private WireMockServer wireMockServer;

    @BeforeEach
    public void setup() {
        wireMockServer = new WireMockServer(8090);
        wireMockServer.start();
    }

    @Test
    void shouldReturnSuccessfulResponseWhileDataAvailable() throws Exception {
        //given
        wireMockServer.stubFor(get(("/api/exchangerates/rates/c/GBP/2020-02-04/?format=json"))
                .willReturn(aResponse().withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withBodyFile("success-nbp-response.json")));

        String expectedResult = "{\"date\":\"2020-02-04\",\"buyPrice\":5.015,\"sellPrice\":5.1164}";

        //when
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/currencyRate/GBP/2020-02-04")).andReturn();

        //then
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getContentAsString()).isEqualTo(expectedResult);
    }

    @AfterEach
    public void teardown() {
        wireMockServer.stop();
    }
}