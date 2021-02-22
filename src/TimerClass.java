import java.util.Timer;
import java.util.TimerTask;

public class TimerClass {


    public void setTimer(){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {


            }
        }, 2000);
    }
}
