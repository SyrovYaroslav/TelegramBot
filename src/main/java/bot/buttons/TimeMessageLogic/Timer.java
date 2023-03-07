package bot.buttons.TimeMessageLogic;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Timer {
    private DateFormat dateFormatNow = new SimpleDateFormat("HH:mm:ss");
    private String dateNow = dateFormatNow.format(new Date());
    private int remaindTime = 15;

    private int timeToSec(){
        String[] TimeArr = dateNow.split(":");
        int result = 0;
        result += Integer.parseInt(TimeArr[0]) * 3600;
        result += Integer.parseInt(TimeArr[1]) * 60;
        result += Integer.parseInt(TimeArr[2]);

        return result;
    }


    public int timeToRemaind(){
        int result = 0;
        int remaindTimeToSec = remaindTime * 3600;
        if (timeToSec() < remaindTimeToSec) {
            result = remaindTimeToSec - timeToSec();
        } else {
            result = 86400 - timeToSec() + remaindTimeToSec;
        }
        return result;
    }

    public void Time(){
        System.out.println(timeToSec());
    }

    public DateFormat getDateNow() {
        return dateFormatNow;
    }

    public void setRemaindTime(int remaindTime) {
        this.remaindTime = remaindTime;
    }


    public DateFormat getDateFormatNow() {
        return dateFormatNow;
    }

    public void setDateFormatNow(DateFormat dateFormatNow) {
        this.dateFormatNow = dateFormatNow;
    }

    public void setDateNow(String dateNow) {
        this.dateNow = dateNow;
    }

    public int getRemaindTime() {
        return remaindTime;
    }


}
