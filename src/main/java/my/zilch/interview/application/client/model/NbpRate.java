package my.zilch.interview.application.client.model;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Builder
@Value
public class NbpRate {
    String no;
    String effectiveDate;
    double bid;
    double ask;
}
