package pawelDyjak.sudoku;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class TimerClass {
    private int seconds = 0;
    private int minutes = 0;
    private int hours = 0;
    private StringBuffer time;
    private SudokuBoard board;
    volatile boolean pauseThread = false;

    public TimerClass(SudokuBoard sudokuBoard){
        this.board = sudokuBoard;
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
                    setTimerButton(board.getSudokuBoardPanel().getComponent(4), String.valueOf(time));
                    board.setTimeCounter(time);



                }
            }
        }, 0, 1000);
        return time;
    }
        //method is incrementing seconds, minutes and hours
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
        if(hours == 99){
            pauseThread();
        }
        return seconds;
    }


    public void pauseThread(){
        pauseThread = true;
    }

    public void resumeThread(){
        pauseThread = false;
    }
        //method sets time label in sudoku board to display time
    public void setTimerButton(Component component, String time) {
        ((JLabel) component).setText(time);
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }
}
