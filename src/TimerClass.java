import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimerClass {
    int seconds = 50;
    int minutes = 59;
    int hours = 0;


    public void setTimer(){
        Timer timer = new Timer();

        timer.schedule(new TimerTask() {




            @Override
            public void run() {
                counter();
                System.out.println(hours+"h" + minutes+"m"+seconds+"s");


            }
        }, 1000, 1000);
    }

    public int counter(){
        seconds++;
        if(seconds == 60){
            minutes++;
            seconds = 0;
        }
        if(minutes == 60){
            hours++;
            minutes = 0;
            seconds = 0;
        }
        return seconds;
    }
}
