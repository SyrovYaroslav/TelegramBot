package message;

import options.Option;
import parser.*;
import parser.dto.CurrencyRate;

import java.math.RoundingMode;
import java.util.List;

public class MessageCurrency {
    CurrencyApiService monobank = new CurrencyMonoParser();
    CurrencyApiService privatbank = new CurrencyPrivateParser();
    CurrencyApiService nbu = new CurrencyNBUParser();

    public String printMesssage(Option option){
        String result = "";
        if (option.getChosenBank().equals(Banks.MONO)) {
            result += "Курс в Monobank:\n";
            result += printCurrensy(monobank.getRate(option.getCurrencies()), option.getSingAfterCommas(), option);
        } else if(option.getChosenBank().equals(Banks.PRIVAT)) {
            result += "Курс в PrivatBank:\n";
            result += printCurrensy(privatbank.getRate(option.getCurrencies()), option.getSingAfterCommas(), option);
        } else {
            result += "Курс в NBU:\n";
            result += printCurrensy(nbu.getRate(option.getCurrencies()), option.getSingAfterCommas(), option);
        }
        return result;
    }

    private String printCurrensy(List<CurrencyRate> currensyList, int singAfterCommas, Option option) {
        StringBuilder currensyResult = new StringBuilder();
        if (option.getChosenBank().equals(Banks.NBU)) {
            for (CurrencyRate currensyInfo: currensyList) {
                currensyResult.append(currensyInfo.getCurrency()).append("/UAH\n");
                currensyResult.append("Курс:")
                        .append(currensyInfo.getBuy().setScale(singAfterCommas, RoundingMode.DOWN))
                                .append("\n");
            }
        } else {
            for (CurrencyRate currensyInfo : currensyList) {
                currensyResult.append(currensyInfo.getCurrency()).append("/UAH\n");
                currensyResult.append("Купівля:")
                        .append(currensyInfo.getBuy().setScale(singAfterCommas, RoundingMode.DOWN))
                        .append("\n");
                currensyResult.append("Продаж:")
                        .append(currensyInfo.getSell().setScale(singAfterCommas, RoundingMode.DOWN))
                        .append("\n");
            }
        }
        return currensyResult.toString();
    }
}
