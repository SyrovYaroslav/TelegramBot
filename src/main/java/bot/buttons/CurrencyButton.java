package bot.buttons;

import bot.CurrencyBot;
import com.vdurmont.emoji.EmojiParser;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import parser.dto.Currencies;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.StrictMath.toIntExact;

public class CurrencyButton {


    public static SendMessage getMessage(String chatId) {
        String text = "Курс якої валюти ви хочете дізнатися?";
        SendMessage message = new SendMessage();
        message.setText(text);
        message.setChatId(chatId);
        message.setReplyMarkup(createOrEditButton(chatId));

        return message;
    }

    private static InlineKeyboardMarkup createOrEditButton (String chatId){
        List<InlineKeyboardButton> rowButton1 = Stream.of("USD")
                .map(it -> InlineKeyboardButton.builder().text(it + " " + markButton(Currencies.USD, chatId)).callbackData(it).build())
                .collect(Collectors.toList());
        List<InlineKeyboardButton> rowButton2 = Stream.of("EUR")
                .map(it -> InlineKeyboardButton.builder().text(it + " " + markButton(Currencies.EUR, chatId)).callbackData(it).build())
                .collect(Collectors.toList());
        List<InlineKeyboardButton> rowButton4 = Stream.of("Головне меню")
                .map(it -> InlineKeyboardButton.builder().text(it).callbackData(it).build())
                .collect(Collectors.toList());

        return InlineKeyboardMarkup
                .builder()
                .keyboard(Collections.singleton(rowButton1))
                .keyboard(Collections.singleton(rowButton2))
                .keyboard(Collections.singleton(rowButton4))
                .build();
    }

    private static String markButton(Currencies  currency, String chatId) {
        return CurrencyBot.getClients().get(chatId).getCurrencies().contains(currency) ? EmojiParser.parseToUnicode(":white_check_mark:") : " ";
    }

    private static EditMessageReplyMarkup getEditMessage(Update update){
        String chatId = String.valueOf(update.getCallbackQuery().getMessage().getChatId());
        int messageId = toIntExact(update.getCallbackQuery().getMessage().getMessageId());
        Currencies currency = Currencies.valueOf(update.getCallbackQuery().getData());

        if (CurrencyBot.getClients().get(chatId).getCurrencies().contains(currency)){
            CurrencyBot.getClients().get(chatId).getCurrencies().remove(currency);
        } else {
            CurrencyBot.getClients().get(chatId).getCurrencies().add(currency);
        }

        return EditMessageReplyMarkup.builder()
                .chatId(chatId)
                .messageId(messageId)
                .replyMarkup(createOrEditButton(chatId))
                .build();
    }

    public static class CurrenciesButton {
        public static EditMessageReplyMarkup setCurrencies(Update update) {
            return getEditMessage(update);
        }
    }
}