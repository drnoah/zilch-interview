package my.zilch.interview.application.client.model;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

import static java.util.Collections.emptyList;

@Jacksonized
@Builder
@Getter
public class NbpRateRecord {
    String table;
    String currencyName;
    String currencyCode;

    @Builder.Default
    List<NbpRate> rates = emptyList();
}
