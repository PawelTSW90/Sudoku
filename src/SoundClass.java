

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;


public class SoundClass {

    public void tick(){
new Thread(() -> {
    try {
        Clip clip = AudioSystem.getClip();
        File file = new File("./Sounds/tick.wav");
        AudioInputStream inputStream = AudioSystem.getAudioInputStream(file);
        clip.open(inputStream);
        clip.start();
    } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
        e.printStackTrace();
    }

}).start();
    }

    public void boardCompletedCorrectly(){
        new Thread(() -> {
            try {
                Clip clip = AudioSystem.getClip();
                File file = new File("./Sounds/_completed.wav");
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(file);
                clip.open(inputStream);
                clip.start();
            } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
                e.printStackTrace();
            }

        }).start();
    }

    public void boardCompletedWrong(){
        new Thread(() -> {
            try {
                Clip clip = AudioSystem.getClip();
                File file = new File("./Sounds/board_completed_wrong.wav");
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(file);
                clip.open(inputStream);
                clip.start();
            } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
                e.printStackTrace();
            }

        }).start();
    }

    }

