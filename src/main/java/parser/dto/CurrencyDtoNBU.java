package parser.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CurrencyDtoNBU {
    private Currencies cc;
    private BigDecimal rate;

}
