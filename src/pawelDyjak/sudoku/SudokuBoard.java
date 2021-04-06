package pawelDyjak.sudoku;

import pawelDyjak.sudoku.Components.BoardCompletedComponents;
import pawelDyjak.sudoku.Components.HighScoresComponents;
import pawelDyjak.sudoku.Components.SudokuBoardComponents;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


public class SudokuBoard {
    private final JFrame mainFrame;
    private boolean helpOn = false;
    private boolean soundOn = true;
    private boolean eraseOn = false;
    private HighScoresJPanel highScoresJPanel;
    private HighScoresComponents highScoresComponents;
    private StringBuffer timeCounter;
    private final Thread errorLabelThread = new Thread(new ErrorLabelThread(this, 0));
    private final JPanel sudokuBoardPanel = new JPanel();
    private final ButtonCreator buttonCreator = new ButtonCreator();
    private final SoundClass soundClass = new SoundClass();
    private final HighScoresCreator highScoresCreator = new HighScoresCreator();
    private TimerClass timerClass = new TimerClass(this);
    private final SudokuBoardComponents sudokuBoardComponents = new SudokuBoardComponents(this);
    private final BoardChecker boardChecker = new BoardChecker(this, soundClass, timerClass, errorLabelThread);
    private final ButtonsTemplateCreator buttonsTemplateCreator = new ButtonsTemplateCreator(this, boardChecker);
    private final BoardCreator boardCreator = new BoardCreator(buttonsTemplateCreator);
    private final SudokuGenerator sudokuGenerator = new SudokuGenerator(this, boardCreator, boardChecker);
    private final ButtonInteract buttonInteract = new ButtonInteract(buttonsTemplateCreator, soundClass, this, boardChecker);
    private final BoardCompletedJPanel boardCompletedJPanel = new BoardCompletedJPanel(this, highScoresCreator, highScoresJPanel, highScoresComponents);
    private final BoardCompletedComponents boardCompletedComponents = new BoardCompletedComponents(this, boardCompletedJPanel, highScoresComponents);


    public SudokuBoard(JFrame mainFrame, HighScoresJPanel highScoresJPanel, HighScoresComponents highScoresComponents) {
        this.mainFrame = mainFrame;
        this.highScoresJPanel = highScoresJPanel;
        this.highScoresComponents = highScoresComponents;

    }

    //method creates sudoku board
    public JPanel createSudokuBoard() {

        sudokuBoardPanel.setLayout(null);
        sudokuBoardPanel.add(sudokuBoardComponents.drawExitQuestion());
        sudokuBoardPanel.add(sudokuBoardComponents.drawBoardCompletedWrongMessage());
        sudokuBoardPanel.add(sudokuBoardComponents.drawSudokuKeypad());
        sudokuBoardPanel.add(sudokuBoardComponents.drawSudokuBoard());
        sudokuBoardPanel.add(sudokuBoardComponents.drawTimerLabel());
        sudokuBoardPanel.add(sudokuBoardComponents.drawSoundLabel());
        sudokuBoardPanel.add(sudokuBoardComponents.drawHelpLabel());
        sudokuBoardPanel.add(sudokuBoardComponents.drawEraseButton());
        sudokuBoardPanel.add(sudokuBoardComponents.drawErrorCounterLabel());
        sudokuBoardPanel.add(sudokuBoardComponents.drawExitButton());
        sudokuBoardPanel.add(sudokuBoardComponents.drawBackground());
        sudokuBoardPanel.setFocusable(true);
        sudokuGenerator.displayBoard(buttonsTemplateCreator);
        //exit question when pressing escape button
        sudokuBoardPanel.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

                int code = e.getKeyCode();
                int escapeButton = 27;

                //open menu
                if (code == escapeButton) {
                    if (eraseOn) {
                        disableBackground(1);
                    } else {

                        if (!sudokuBoardPanel.getComponent(0).isVisible()) {
                            disableBackground(0);
                            sudokuBoardPanel.getComponent(3).setVisible(false);
                            sudokuBoardPanel.getComponent(0).setVisible(true);
                            timerClass.pauseThread();
                        } else {
                            disableBackground(1);
                            sudokuBoardPanel.getComponent(3).setVisible(true);
                            sudokuBoardPanel.getComponent(0).setVisible(false);
                            timerClass.resumeThread();
                        }


                    }
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        timerClass.setTimer();

        return sudokuBoardPanel;

    }

    //disable background components when displaying menu/using erase
    public void disableBackground(int tmp) {
        ArrayList<Component> sudokuBoardList = new ArrayList<>();
        ArrayList<Component> sudokuKeypadList = new ArrayList<>();
        Component sudokuBoard = sudokuBoardPanel.getComponent(3);
        Component sudokuKeypad = sudokuBoardPanel.getComponent(2);
        Component sounds = sudokuBoardPanel.getComponent(5);
        Component help = sudokuBoardPanel.getComponent(6);
        Component delete = sudokuBoardPanel.getComponent(7);


        for (int x = 0; x < ((Container) sudokuBoard).getComponents().length; x++) {
            Component currentComponent = ((Container) sudokuBoard).getComponent(x);

            for (int y = 0; y < ((Container) currentComponent).getComponents().length; y++) {
                Component currentButton = ((Container) currentComponent).getComponent(y);
                sudokuBoardList.add(currentButton);
            }

        }
        for (int x = 0; x < ((Container) sudokuKeypad).getComponents().length; x++) {
            Component currentComponent = ((Container) sudokuKeypad).getComponent(x);
            sudokuKeypadList.add(currentComponent);
        }


        if (tmp == 0) {

            if (eraseOn) {

                for
                (Component component2 : sudokuKeypadList) {
                    component2.setEnabled(false);
                }
                sounds.setEnabled(false);
                help.setEnabled(false);


            } else {


                for (Component component : sudokuBoardList) {
                    component.setEnabled(false);

                }
                for
                (Component component2 : sudokuKeypadList) {
                    component2.setEnabled(false);
                }
                sounds.setEnabled(false);
                help.setEnabled(false);

                delete.setEnabled(false);
            }
        } else {

            eraseOn = false;
            for (Component component : sudokuBoardList) {
                component.setEnabled(true);
            }

            for (Component component2 : sudokuKeypadList) {
                component2.setEnabled(true);
            }
            sounds.setEnabled(true);
            help.setEnabled(true);

            delete.setEnabled(true);


        }
    }


    public boolean isSoundOn() {
        return soundOn;
    }

    public void setSoundOn(boolean soundOn) {
        this.soundOn = soundOn;
    }

    public boolean isHelpOn() {
        return helpOn;
    }

    public void setHelpOn(boolean helpOn) {
        this.helpOn = helpOn;
    }

    public boolean isEraseOn() {
        return eraseOn;
    }

    public void setEraseOn(boolean eraseOn) {
        this.eraseOn = eraseOn;
    }

    public StringBuffer getTimeCounter() {
        return timeCounter;
    }

    public void setTimeCounter(StringBuffer timeCounter) {
        this.timeCounter = timeCounter;
    }

    public JPanel getSudokuBoardPanel() {
        return sudokuBoardPanel;
    }

    public TimerClass getTimerClass() {
        return timerClass;
    }

    public SoundClass getSoundClass() {
        return soundClass;
    }

    public SudokuGenerator getSudokuGenerator() {
        return sudokuGenerator;
    }

    public ButtonsTemplateCreator getButtonsTemplateCreator() {
        return buttonsTemplateCreator;
    }

    public void setTimerClass(TimerClass timerClass) {
        this.timerClass = timerClass;
    }

    public BoardChecker getBoardChecker() {
        return boardChecker;
    }


    public ButtonInteract getButtonInteract() {
        return buttonInteract;
    }

    public JFrame getMainFrame() {
        return mainFrame;
    }

    public ButtonCreator getButtonCreator() {
        return buttonCreator;
    }

    public HighScoresCreator getHighScoresCreator() {
        return highScoresCreator;
    }

    public SudokuBoard getSudokuBoard() {
        return this;
    }

    public BoardCompletedComponents getBoardCompletedComponents() {
        return boardCompletedComponents;
    }

    public BoardCompletedJPanel getBoardCompletedJPanel() {
        return boardCompletedJPanel;
    }

}



