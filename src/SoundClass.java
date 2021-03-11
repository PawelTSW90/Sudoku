

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;


public class SoundClass {
        //method is playing tick sound
    public void tick(SudokuBoard board) {
        if(board.isSoundOn()) {

            try {
                Clip clip = AudioSystem.getClip();
                File file = new File("./Sounds/tick.wav");
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(file);
                clip.open(inputStream);
                clip.start();
            } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
                e.printStackTrace();
            }
        } else{

        }


    }
        //method is playing board completed correctly sound
    public void boardCompletedCorrectly(SudokuBoard board) {
        if(board.isSoundOn()) {

            try {
                Clip clip = AudioSystem.getClip();
                File file = new File("./Sounds/board_completed.wav");
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(file);
                clip.open(inputStream);
                clip.start();
            } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
                e.printStackTrace();
            }
        }else{

        }


    }
        //method is playing board completed incorrectly sound
    public void boardCompletedWrong(SudokuBoard board) {
        if (board.isSoundOn()) {

            try {
                Clip clip = AudioSystem.getClip();
                File file = new File("./Sounds/board_completed_wrong.wav");
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(file);
                clip.open(inputStream);
                clip.start();
            } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
                e.printStackTrace();
            }


        }else{

        }
    }

}

