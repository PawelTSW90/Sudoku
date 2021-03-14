import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class TimerClass {
    int seconds = 0;
    int minutes = 0;
    int hours = 0;
    private StringBuffer time;
    SudokuBoard board;
    volatile boolean pauseThread = false;

    public TimerClass(SudokuBoard board){
        this.board = board;
    }



        //method is starting timer and passing it as a value to timer JLabel
    public StringBuffer setTimer(){
        time = new StringBuffer();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                if (!pauseThread) {
                    time.delete(0, time.capacity());
                    counter();
                    time.append(String.format("%02d", hours)).append(":").append(String.format("%02d", minutes)).append(":").append(String.format("%02d", seconds));
                    setTimerButton(board.sudokuBoardPanel.getComponent(4), String.valueOf(time));
                    board.timeCounter = time;



                }
            }
        }, 0, 1000);
        return time;
    }
        //method is incrementing seconds, minutes and hours correctly
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


    public void pauseThread(){
        pauseThread = true;
    }

    public void resumeThread(){
        pauseThread = false;
    }

    public void setTimerButton(Component component, String time) {
        ((JLabel) component).setText(time);
    }

    public StringBuffer getTime(){
        return time;
    }

}
