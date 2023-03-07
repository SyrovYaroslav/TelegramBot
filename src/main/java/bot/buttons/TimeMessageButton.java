package bot.buttons;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.HashMap;
import java.util.List;

public class TimeMessageButton {
    private static HashMap<String, KeyboardButton> keyboard = new HashMap<>();

    public static SendMessage getMessageCreateNotation(String chatId) {
        String text = "Виберіть час сповіщення";
        SendMessage message = new SendMessage();
        message.setText(text);
        message.setChatId(chatId);

        for (int i = 9; i <= 18; i++) {
            keyboard.put(String.valueOf(i), KeyboardButton.builder().text(String.valueOf(i)).build());
        }
        keyboard.put("Вимкнути повідомлення", KeyboardButton.builder().text("Вимкнути повідомлення").build());
        keyboard.put("На головне меню", KeyboardButton.builder().text("На головне меню").build());

        KeyboardRow row1 = new KeyboardRow(List.of(keyboard.get("9"), keyboard.get("10"), keyboard.get("11")));
        KeyboardRow row2 = new KeyboardRow(List.of(keyboard.get("12"), keyboard.get("13"), keyboard.get("14")));
        KeyboardRow row3 = new KeyboardRow(List.of(keyboard.get("15"), keyboard.get("16"), keyboard.get("17")));
        KeyboardRow row4 = new KeyboardRow(List.of(keyboard.get("18"), keyboard.get("Вимкнути повідомлення"), keyboard.get("На головне меню")));

        message.setReplyMarkup(ReplyKeyboardMarkup
                .builder()
                .keyboardRow(row1)
                .keyboardRow(row2)
                .keyboardRow(row3)
                .keyboardRow(row4)
                .build());

        return message;
    }

    public static SendMessage setSchedule(Update update) {
        String text = "Час сповіщення встановленно!";
        SendMessage message = new SendMessage();
        message.setText(text);
        message.setChatId(update.getMessage().getChatId());


        return message;
    }

    public static SendMessage shutDownSchedule(Update update) {
        String text = "Ви вимкнули оповіщення!";
        SendMessage message = new SendMessage();
        message.setText(text);
        message.setChatId(update.getMessage().getChatId());


        return message;
    }

}