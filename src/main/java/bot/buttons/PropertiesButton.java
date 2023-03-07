package bot.buttons;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class PropertiesButton  {
    public static SendMessage getMessage (String chatId){
        String text = "Тут ви можете налаштувати сповіщення так як вам заманеться!";
        SendMessage message = new SendMessage();
        message.setText(text);
        message.setChatId(chatId);

        List<InlineKeyboardButton> rowButton1 = Stream.of("Кількість знаків після коми")
                .map(it -> InlineKeyboardButton.builder().text(it).callbackData(it).build())
                .collect(Collectors.toList());
        List<InlineKeyboardButton> rowButton2 = Stream.of( "Банк")
                .map(it -> InlineKeyboardButton.builder().text(it).callbackData(it).build())
                .collect(Collectors.toList());
        List<InlineKeyboardButton> rowButton3 = Stream.of( "Валюти")
                .map(it -> InlineKeyboardButton.builder().text(it).callbackData(it).build())
                .collect(Collectors.toList());
        List<InlineKeyboardButton> rowButton4 = Stream.of( "Час сповіщення")
                .map(it -> InlineKeyboardButton.builder().text(it).callbackData(it).build())
                .collect(Collectors.toList());
        List<InlineKeyboardButton> rowButton5 = Stream.of( "Головне меню")
                .map(it -> InlineKeyboardButton.builder().text(it).callbackData(it).build())
                .collect(Collectors.toList());


        InlineKeyboardMarkup keyboard = InlineKeyboardMarkup
                .builder()
                .keyboard(Collections.singleton(rowButton1))
                .keyboard(Collections.singleton(rowButton2))
                .keyboard(Collections.singleton(rowButton3))
                .keyboard(Collections.singleton(rowButton4))
                .keyboard(Collections.singleton(rowButton5))
                .build();

        message.setReplyMarkup(keyboard);

        return message;

    }
}
