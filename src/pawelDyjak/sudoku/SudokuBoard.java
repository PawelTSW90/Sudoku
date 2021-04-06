package pawelDyjak.sudoku;

import pawelDyjak.sudoku.Components.SudokuBoardComponents;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


public class SudokuBoard {
    private JFrame mainFrame;
    private MainMenu mainMenu;
    private boolean helpOn = false;
    private boolean soundOn = true;
    private boolean erase = false;
    private final JPanel sudokuBoardPanel = new JPanel();
    private final ButtonCreator buttonCreator = new ButtonCreator();
    private final SoundClass soundClass = new SoundClass();
    private TimerClass timerClass = new TimerClass(this);
    private final BoardChecker boardChecker = new BoardChecker();
    private final SudokuGenerator sudokuGenerator = new SudokuGenerator(boardChecker);
    private HighScoresCreator highScoresCreator = new HighScoresCreator(this);
    private SudokuBoardComponents sudokuBoardComponents = new SudokuBoardComponents(this);
    private final ButtonsTemplateCreator buttonsTemplateCreator = new ButtonsTemplateCreator(this, soundClass);
    private final ButtonInteract buttonInteract = new ButtonInteract(buttonsTemplateCreator, sudokuGenerator, soundClass, this, boardChecker);
    private final Thread errorLabelThread = new Thread(new ErrorLabelThread(this, 0));
    private StringBuffer timeCounter;


    public SudokuBoard(JFrame mainFrame, MainMenu mainMenu) {
        this.mainFrame = mainFrame;
        this.mainMenu = mainMenu;

    }

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
                if (code == 81) {
                    BoardCompletedJPanel boardCompletedJPanel = new BoardCompletedJPanel(SudokuBoard.this, getHighScoresCreator());
                    soundClass.boardCompletedCorrectly(SudokuBoard.this);
                    getTimerClass().pauseThread();
                    getMainFrame().add(boardCompletedJPanel.boardCompletedMessage());
                    boardCompletedJPanel.setUserNameLabel();
                    getMainFrame().getContentPane().getComponent(1).setVisible(false);
                }
                if (code == 27) {
                    if (erase) {
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

            if (erase) {

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

            erase = false;
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
        return erase;
    }

    public void setErase(boolean erase) {
        this.erase = erase;
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

    public Thread getErrorLabelThread() {
        return errorLabelThread;
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

    public MainMenu getMainMenu() {
        return mainMenu;
    }

    public HighScoresCreator getHighScoresCreator() {
        return highScoresCreator;
    }

    public SudokuBoardComponents getSudokuBoardComponents() {
        return sudokuBoardComponents;
    }
}



