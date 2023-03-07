package parser;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.jsoup.Jsoup;
import parser.dto.Currencies;
import parser.dto.CurrencyDtoNBU;
import parser.dto.CurrencyRate;

import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CurrencyNBUParser implements CurrencyApiService {
    @Override
    public List<CurrencyRate> getRate(List<Currencies> currencies) {
        String url = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json";
        String json = null;
        try {
            json = Jsoup.connect(url)
                    .ignoreContentType(true)
                    .get()
                    .body()
                    .text();
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException("Connection was not success");
        }
        Type typeToken = TypeToken.getParameterized(List.class, CurrencyDtoNBU.class)
                .getType();

        Gson gson = new Gson();
        List<CurrencyDtoNBU> currencyDtos = gson.fromJson(json, typeToken);
        List<CurrencyRate> list = new ArrayList<>();

        for (int i = 0; i < currencies.size(); i++) {
            if (currencies.get(i).equals(Currencies.USD)) {
                BigDecimal rate = currencyDtos.stream().filter(it -> it.getCc() == Currencies.USD)
                        .map(it -> it.getRate()).findFirst().orElseThrow();
                CurrencyRate currencyRate = new CurrencyRate(null, rate , Currencies.USD);
                list.add(currencyRate);

            } else if (currencies.get(i).equals(Currencies.EUR)) {
                BigDecimal rate = currencyDtos.stream().filter(it -> it.getCc() == Currencies.EUR)
                        .map(it -> it.getRate()).findFirst().orElseThrow();
                CurrencyRate currencyRate = new CurrencyRate(null, rate , Currencies.EUR);

                list.add(currencyRate);
            }
        }
        return list;
    }


}

