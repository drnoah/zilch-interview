package my.zilch.interview.application.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Rate {
    String date;
    double buyPrice;
    double sellPrice;
}
