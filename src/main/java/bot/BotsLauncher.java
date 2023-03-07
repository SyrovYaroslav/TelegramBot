package bot;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class BotsLauncher {
    private CurrencyBot currencyBot;
    public BotsLauncher() {
        currencyBot = new CurrencyBot();
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(currencyBot);

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
