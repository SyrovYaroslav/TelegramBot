package options;

import parser.Banks;
import parser.dto.Currencies;

import java.util.ArrayList;
import java.util.List;


public class Option {
    private int singAfterCommas = 2;
    private List<Currencies> currencies = new ArrayList<>();
    private Banks chosenBank = Banks.PRIVAT;

    public Option (){
        currencies.add(Currencies.USD);
    }

    public int getSingAfterCommas() {
        return singAfterCommas;
    }

    public void setSingAfterCommas(int singAfterCommas) {
        this.singAfterCommas = singAfterCommas;
    }

    public List<Currencies> getCurrencies() {
        return currencies;
    }

    public Banks getChosenBank() {
        return chosenBank;
    }


    public void setChosenBank(Banks chosenBank) {
        this.chosenBank = chosenBank;
    }
}
