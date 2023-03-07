package parser.dto;

import lombok.Data;

@Data

public class CurrencyDtoPrivate {
    private String  ccy;
    private String  base_ccy;
    private float buy;
    private float sale;
}
