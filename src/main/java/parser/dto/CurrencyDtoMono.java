package parser.dto;

import lombok.Data;

@Data
public class CurrencyDtoMono {
    private int currencyCodeA;
    private int currencyCodeB;
    private int date;
    private float rateBuy;
    private float rateSell;
    private float rateCross;
    private String currencyName;

}
