package parser;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.jsoup.Jsoup;
import parser.dto.Currencies;
import parser.dto.CurrencyDtoMono;
import parser.dto.CurrencyRate;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CurrencyMonoParser implements CurrencyApiService {
    @Override

    public List<CurrencyRate> getRate(List<Currencies> currencies) {

        String response = getResponse();

        Gson gson = new Gson();

        List<CurrencyDtoMono> fromJson = gson.fromJson(
                response, TypeToken.getParameterized(List.class, CurrencyDtoMono.class).getType());

        fromJson = getMonoResponseDTOS(fromJson);

        return getRates(currencies, fromJson);
    }

    private static List<CurrencyDtoMono> getMonoResponseDTOS(List<CurrencyDtoMono> fromJson) {
        Map<Integer, String> currencyMap = Map.of(
                840, "USD",
                978, "EUR");

        fromJson = fromJson.stream().peek(item -> {
                    if (currencyMap.containsKey(item.getCurrencyCodeA()) && item.getCurrencyCodeB() == 980) {
                        item.setCurrencyName(currencyMap.get(item.getCurrencyCodeA()));
                    }
                })
                .filter(item -> item.getCurrencyName() != null)
                .collect(Collectors.toList());
        return fromJson;
    }

    private static String getResponse() {
        String url = "https://api.monobank.ua/bank/currency";
        String response = null;
        try {
            response = Jsoup.connect(url)
                    .ignoreContentType(true)
                    .get()
                    .body()
                    .text();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return response;
    }

    private static List<CurrencyRate> getRates(List<Currencies> currencies, List<CurrencyDtoMono> fromJson) {
        List<CurrencyRate> result = new ArrayList<>();
        for (Currencies currency : currencies) {
            for (CurrencyDtoMono item : fromJson) {
                if (currency.name().equals(item.getCurrencyName())) {
                    result.add(
                            new CurrencyRate(
                                    BigDecimal.valueOf(item.getRateSell()),
                                    BigDecimal.valueOf(item.getRateBuy()),
                                    currency));
                }
            }
        }
        return result;
    }
}
