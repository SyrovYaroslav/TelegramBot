package bot;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BotConstants {
    private String botName;
    private String botToken;

    private FileInputStream fis;
    private Properties property = new Properties();


    public void propertiesReader(){
        try {
            fis = new FileInputStream("src/main/resources/applications.properties");
            property.load(fis);

            setBotName(property.getProperty("userName"));
            setBotToken(property.getProperty("botToken"));

        } catch (IOException e) {
            System.err.println("ОШИБКА: Файл свойств отсуствует!");
        }
    }


    public FileInputStream getFis() {
        return fis;
    }

    public void setFis(FileInputStream fis) {
        this.fis = fis;
    }

    public Properties getProperty() {
        return property;
    }

    public void setProperty(Properties property) {
        this.property = property;
    }

    public String getBotName() {
        return botName;
    }

    public void setBotName(String botName) {
        this.botName = botName;
    }

    public String getBotToken() {
        return botToken;
    }

    public void setBotToken(String botToken) {
        this.botToken = botToken;
    }
}
