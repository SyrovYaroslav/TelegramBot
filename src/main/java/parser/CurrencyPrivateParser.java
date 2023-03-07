package parser;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.jsoup.Jsoup;
import parser.dto.Currencies;
import parser.dto.CurrencyDtoPrivate;
import parser.dto.CurrencyRate;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CurrencyPrivateParser implements CurrencyApiService {

    private static final String PRIVATE_API_URL = "https://api.privatbank.ua/p24api/pubinfo?exchange&json&coursid=11";
    public static final Set<String> SUPPORTED_CURRENCIES = Set.of("USD", "EUR");
    public static final String BASE_CURRENCY = "UAH";

    private static String getResponse(){
        String text;
        try{
            text = Jsoup
                    .connect(PRIVATE_API_URL)
                    .ignoreContentType(true)
                    .get()
                    .body()
                    .text();
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException("Can't connect to Private API");
        }
        return text;
    }

    private static List<CurrencyDtoPrivate> filterSupportedCurrencies(List<CurrencyDtoPrivate> fromJson){
        return fromJson
        .stream()
        .filter(item -> SUPPORTED_CURRENCIES.contains(item.getCcy()) && item.getBase_ccy().equals(BASE_CURRENCY))
        .collect(Collectors.toList());
    }

    private static List<CurrencyRate> convertToRates(List<Currencies> currencies, List<CurrencyDtoPrivate> fromJson) {
        List<CurrencyRate> res = new ArrayList<>();
        for (Currencies currency: currencies) {
            for (CurrencyDtoPrivate item : fromJson) {
                if (currency.name().equals(item.getCcy())) {
                    res.add(
                            new CurrencyRate(
                                    BigDecimal.valueOf(item.getSale()),
                                    BigDecimal.valueOf(item.getBuy()),
                                    currency));
                }
            }
        }
        return res;
    }
    @Override
    public List<CurrencyRate> getRate(List<Currencies> currencies) {
        // 0. Request currency rates from Private API
        String response = getResponse();
        Gson gson = new Gson();

        List<CurrencyDtoPrivate> fromJson = gson.fromJson(
                response, TypeToken.getParameterized(List.class, CurrencyDtoPrivate.class).getType());

        // 1. Filter supported currencies only
        fromJson = filterSupportedCurrencies(fromJson);

        // 2. Convert to Rates
        return convertToRates(currencies, fromJson);
    }
}
