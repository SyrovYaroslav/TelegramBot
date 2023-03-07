package bot.buttons;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class ReturnButton  {
    public static SendMessage getMessage (String chatId){
        String text = "Головне меню";
        SendMessage message = new SendMessage();
        message.setText(text);
        message.setChatId(chatId);


        List<InlineKeyboardButton> rowButton1 = Stream.of("Отримати інформацію по курсу валют")
                .map(it -> InlineKeyboardButton.builder().text(it).callbackData(it).build())
                .collect(Collectors.toList());
        List<InlineKeyboardButton> rowButton2 = Stream.of("Налаштування")
                .map(it -> InlineKeyboardButton.builder().text(it).callbackData(it).build())
                .collect(Collectors.toList());


        InlineKeyboardMarkup keyboard = InlineKeyboardMarkup
                .builder()
                .keyboard(Collections.singleton(rowButton1))
                .keyboard(Collections.singleton(rowButton2))
                .build();

        message.setReplyMarkup(keyboard);

        return message;

    }
}