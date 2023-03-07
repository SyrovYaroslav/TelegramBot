package bot.buttons;

import lombok.SneakyThrows;
import options.Option;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BankButton {

    public static EditMessageReplyMarkup getUpdatedKeyboard(Update update, Option option) {
        String callData = update.getCallbackQuery().getData();
        Integer messageId = update.getCallbackQuery().getMessage().getMessageId();
        long chatId = update.getCallbackQuery().getMessage().getChatId();
        String inlineMessageId = update.getCallbackQuery().getInlineMessageId();

        EditMessageReplyMarkup new_message = new EditMessageReplyMarkup();
        new_message.setChatId(chatId);
        new_message.setMessageId(messageId);
        new_message.setInlineMessageId(inlineMessageId);
        new_message.setReplyMarkup(getInlineKeyboardMarkup(option));
        return new_message;
    }

    @SneakyThrows
    public static SendMessage getMessage(String chatId, Option option) {
        String text = "Оберіть банк";
        SendMessage message = new SendMessage(chatId, text);
        message.setReplyMarkup(getInlineKeyboardMarkup(option));
        return message;
    }

    private static InlineKeyboardMarkup getInlineKeyboardMarkup(Option option) {
        List<InlineKeyboardButton> rowButton1 = Stream.of("ПриватБанк")
                .map(it -> InlineKeyboardButton.builder().text(it + " " + (option.getChosenBank().name().equals("PRIVAT") ? "\u2705" : " ")).callbackData(it).build())
                .collect(Collectors.toList());
        List<InlineKeyboardButton> rowButton2 = Stream.of("МоноБанк")
                .map(it -> InlineKeyboardButton.builder().text(it + " " + (option.getChosenBank().name().equals("MONO") ? "\u2705" : " ")).callbackData(it).build())
                .collect(Collectors.toList());
        List<InlineKeyboardButton> rowButton3 = Stream.of("НБУ")
                .map(it -> InlineKeyboardButton.builder().text(it + " " + (option.getChosenBank().name().equals("NBU") ? "\u2705" : " ")).callbackData(it).build())
                .collect(Collectors.toList());
        List<InlineKeyboardButton> rowButton4 = Stream.of( "Головне меню")
                .map(it -> InlineKeyboardButton.builder().text(it).callbackData(it).build())
                .collect(Collectors.toList());
        return InlineKeyboardMarkup
                .builder()
                .keyboard(Collections.singleton(rowButton1))
                .keyboard(Collections.singleton(rowButton2))
                .keyboard(Collections.singleton(rowButton3))
                .keyboard(Collections.singleton(rowButton4))
                .build();
    }
}