package pawelDyjak.sudoku;
import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;


public class SoundClass {
    //method plays tick sound
    public void tick(SudokuBoard sudokuBoard) {
        if (sudokuBoard == null || sudokuBoard.isSoundOn()) {

            try {
                Clip clip = AudioSystem.getClip();
                URL tickFile = getClass().getClassLoader().getResource("tick.wav");
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(tickFile);
                clip.open(inputStream);
                clip.start();
            } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
                e.printStackTrace();
            }
        }


    }

    //method plays board completed correctly sound
    public void boardCompletedCorrectly(SudokuBoard sudokuBoard) {
        if (sudokuBoard.isSoundOn()) {

            try {
                Clip clip = AudioSystem.getClip();
                URL boardCompletedFile = getClass().getClassLoader().getResource("board_completed.wav");
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(boardCompletedFile);
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
                URL boardCompletedWrongFile = getClass().getClassLoader().getResource("board_completed_wrong.wav");
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(boardCompletedWrongFile);
                clip.open(inputStream);
                clip.start();
            } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
                e.printStackTrace();
            }


        }
    }

    //method plays error sound
    public void error(SudokuBoard sudokuBoard) {
        if (sudokuBoard.isSoundOn()) {

            try {
                Clip clip = AudioSystem.getClip();
                URL errorFile = getClass().getClassLoader().getResource("error.wav");
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(errorFile);
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
                URL eraseFile = getClass().getClassLoader().getResource("erase.wav");
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(eraseFile);
                clip.open(inputStream);
                clip.start();
            } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
                e.printStackTrace();
            }
        }
    }

}

