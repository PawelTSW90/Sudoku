package pawelDyjak.sudoku;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;


public class SoundClass {
        //method plays tick sound
    public void tick(SudokuBoard sudokuBoard) {
        if(sudokuBoard==null || sudokuBoard.isSoundOn()) {

            try {
                Clip clip = AudioSystem.getClip();
                File file = new File("./Sounds/tick.wav");
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(file);
                clip.open(inputStream);
                clip.start();
            } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
                e.printStackTrace();
            }
        }


    }
        //method plays board completed correctly sound
    public void boardCompletedCorrectly(SudokuBoard sudokuBoard) {
        if(sudokuBoard.isSoundOn()) {

            try {
                Clip clip = AudioSystem.getClip();
                File file = new File("./Sounds/board_completed.wav");
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(file);
                clip.open(inputStream);
                clip.start();
            } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
                e.printStackTrace();
            }
        }


    }
        //method plays board completed incorrectly sound
    public void boardCompletedWrong(SudokuBoard sudokuBoard) {
        if (sudokuBoard.isSoundOn()) {

            try {
                Clip clip = AudioSystem.getClip();
                File file = new File("./Sounds/board_completed_wrong.wav");
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(file);
                clip.open(inputStream);
                clip.start();
            } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
                e.printStackTrace();
            }


        }
    }
        //method plays error sound
    public void error(SudokuBoard sudokuBoard){
        if (sudokuBoard.isSoundOn()) {

            try {
                Clip clip = AudioSystem.getClip();
                File file = new File("./Sounds/error.wav");
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(file);
                clip.open(inputStream);
                clip.start();
            } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
                e.printStackTrace();
            }


        }
    }
        //method plays erase sound
        public void erase(SudokuBoard sudokuBoard) {
            if (sudokuBoard.isSoundOn()) {

                try {
                    Clip clip = AudioSystem.getClip();
                    File file = new File("./Sounds/erase.wav");
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(file);
                    clip.open(inputStream);
                    clip.start();
                } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
                    e.printStackTrace();
                }
            }
        }

}

