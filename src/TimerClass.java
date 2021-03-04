import java.util.Timer;
import java.util.TimerTask;

public class TimerClass {
    int seconds = 0;
    int minutes = 0;
    int hours = 0;
    StringBuffer time = new StringBuffer();
    SudokuBoard board;

    public TimerClass(SudokuBoard board){
        this.board = board;
    }




    public StringBuffer setTimer(){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {

                counter();
                time.append(String.format("%02d", hours)).append(":").append(String.format("%02d", minutes)).append(":").append(String.format("%02d", seconds));
                board.setTimerButton(board.sudokuBoardPanel.getComponent(3), String.valueOf(time));
                time.delete(0, time.capacity());


            }
        }, 0, 1000);
        return time;
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

    public StringBuffer getTime(){
        return time;
    }


}
