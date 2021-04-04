package pawelDyjak.sudoku;

import pawelDyjak.sudoku.Components.SudokuBoardComponents;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


public class SudokuBoard {
    JFrame mainFrame;
    MainMenu menu;
    private boolean helpOn = false;
    private boolean soundOn = true;
    private boolean erase = false;
    private final JPanel sudokuBoardPanel = new JPanel();

    ButtonCreator buttonCreator = new ButtonCreator();
    private SoundClass soundClass = new SoundClass();
    private TimerClass timerClass = new TimerClass(this);
    private BoardChecker boardChecker = new BoardChecker();
   private SudokuGenerator sudokuGenerator = new SudokuGenerator(boardChecker);
    HighScoresCreator highScoresCreator = new HighScoresCreator(this);
    SudokuBoardComponents sudokuBoardComponents = new SudokuBoardComponents(this);
    private ButtonsTemplateCreator buttonsTemplateCreator = new ButtonsTemplateCreator(this, soundClass);
    ButtonInteract buttonInteract = new ButtonInteract(buttonsTemplateCreator, sudokuGenerator, soundClass, this, boardChecker);
    private Thread errorLabelThread = new Thread(new ErrorLabelThread(this, 0));
    private StringBuffer timeCounter;


    public SudokuBoard(JFrame mainFrame, MainMenu menu) {
        this.mainFrame = mainFrame;
        this.menu = menu;

    }


    public JPanel createSudokuBoard() {

        sudokuBoardPanel.setLayout(null);
        sudokuBoardPanel.add(drawExitQuestion());
        sudokuBoardPanel.add(sudokuBoardComponents.drawBoardCompletedWrongMessage());
        sudokuBoardPanel.add(drawSudokuKeypad());
        sudokuBoardPanel.add(drawSudokuBoard());
        sudokuBoardPanel.add(drawTimerLabel());
        sudokuBoardPanel.add(drawSoundLabel());
        sudokuBoardPanel.add(sudokuBoardComponents.drawHelpLabel());
        sudokuBoardPanel.add(sudokuBoardComponents.drawEraseButton());
        sudokuBoardPanel.add(sudokuBoardComponents.drawErrorCounterLabel());
        sudokuBoardPanel.add(sudokuBoardComponents.drawExitButton());
        sudokuBoardPanel.add(background());
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

    //method is setting background
    public JLabel background() {

        JLabel backgroundLabel = new JLabel();
        backgroundLabel.setBounds(0, 0, UtilityClass.getScreenWidth(), UtilityClass.getScreenHeight());
        backgroundLabel.setIcon(new ImageIcon("./Visuals/sudoku-background.jpg"));


        return backgroundLabel;

    }

    //method is creating keypad
    public JPanel drawSudokuKeypad() {
        JPanel sudokuKeypad = new JPanel();
        sudokuKeypad.setLayout(new GridLayout(3, 3));
        sudokuKeypad.setBounds(1600, 100, 300, 300);
        for (int x = 0; x < 9; x++) {
            sudokuKeypad.add(buttonCreator.createKeypadButtons());
        }
        return sudokuKeypad;
    }

    //creating sudoku board JPanel
    public JPanel drawSudokuBoard() {

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 3));
        mainPanel.setBounds(140, 90, 1000, 900);

        for (int panelNr = 0; panelNr < 9; panelNr++) {
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(3, 3));
            panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            for (int buttonNr = 0; buttonNr < 9; buttonNr++) {
                panel.add(buttonCreator.createBoardButtons());
            }
            mainPanel.add(panel);
        }

        // create buttons template
        buttonsTemplateCreator.createBoardTemplate(buttonCreator.getBoardButtons(), buttonCreator.getKeypadButtons());
        return mainPanel;
    }

    //creating exit question JPanel
    public JPanel drawExitQuestion() {
        JPanel exit = new JPanel();
        exit.setLayout(new BoxLayout(exit, BoxLayout.X_AXIS));
        JPanel question = new JPanel();
        question.setBackground(new Color(245, 232, 211));
        question.setLayout(new GridLayout(4, 1));
        question.setBorder(BorderFactory.createEtchedBorder(Color.GRAY, Color.PINK));
        question.setBounds(UtilityClass.getScreenWidth() / 2 - 500 / 2, UtilityClass.getScreenHeight() / 2 - 400 / 2, 500, 400);
        JButton questionButton = new JButton(" Would you like to:");
        questionButton.setBorderPainted(false);
        JButton startOver = new JButton("Start over");
        JButton goBack = new JButton("Continue");
        JButton quit = new JButton("Exit");
        startOver.addMouseListener(new MouseListenerClass(this));
        goBack.addMouseListener(new MouseListenerClass(this));
        quit.addMouseListener(new MouseListenerClass(this));
        quit.setFont(new Font(null, Font.PLAIN, 40));
        startOver.setFont(new Font(null, Font.PLAIN, 40));
        questionButton.setFont(new Font(null, Font.PLAIN, 40));
        goBack.setFont(new Font(null, Font.PLAIN, 40));
        UtilityClass.buttonConfigure(startOver);
        UtilityClass.buttonConfigure(questionButton);
        UtilityClass.buttonConfigure(quit);
        UtilityClass.buttonConfigure(goBack);
        question.add(questionButton);
        question.add(startOver);
        question.add(goBack);
        question.add(quit);
        question.setVisible(false);
        quit.addActionListener(e -> {
            soundClass.tick(this);
            mainFrame.getContentPane().getComponent(1).setVisible(false);
            mainFrame.getContentPane().getComponent(0).setVisible(true);
        });
        goBack.addActionListener(e -> {
            soundClass.tick(this);
            sudokuBoardPanel.setFocusable(true);
            disableBackground(1);
            timerClass.resumeThread();
            sudokuBoardPanel.getComponent(0).setVisible(false);

        });
        startOver.addActionListener(e -> {
            soundClass.tick(this);
            sudokuBoardPanel.setFocusable(true);
            sudokuBoardPanel.getComponent(0).setVisible(false);
            disableBackground(1);
            timerClass = new TimerClass(this);
            timerClass.setTimer();
            sudokuGenerator.resetBoard(buttonsTemplateCreator);
        });
        return question;
    }


    public JLabel drawTimerLabel() {


        JLabel timerLabel = new JLabel();
        timerLabel.setFont(new Font(null, Font.PLAIN, 80));
        timerLabel.setBounds((UtilityClass.getScreenWidth() / 2) + 600, (UtilityClass.getScreenHeight() / 2) + 200, 500, 500);
        timerLabel.setVisible(true);
        return timerLabel;

    }


    public JLabel drawSoundLabel() {
        JLabel sound = new JLabel("Sounds");
        sound.setForeground(new Color(0, 102, 0));
        sound.setBackground(Color.BLACK);
        sound.setFocusable(false);
        sound.setFont(new Font(null, Font.ITALIC, 80));
        sound.setBounds((UtilityClass.getScreenWidth() / 2) + 600, (UtilityClass.getScreenHeight() / 2) + 50, 400, 100);
        sound.setVisible(true);
        sound.addMouseListener(new MouseListenerClass(this) {
            @Override
            public void mousePressed(MouseEvent e) {
                Component component = sudokuBoardPanel.getComponent(5);
                if (soundOn) {
                    component.setForeground(new Color(102, 0, 0));
                    setSoundOn(false);
                } else {
                    sound.setForeground(new Color(0, 102, 0));
                    setSoundOn(true);
                }
            }
            @Override
            public void mouseEntered(MouseEvent e){

            }

            @Override
            public void mouseExited(MouseEvent e){

            }
            @Override
            public void mouseClicked(MouseEvent e){

            }
        });
        return sound;
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

    public TimerClass getTimerClass(){
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

    public BoardChecker getBoardChecker(){
        return boardChecker;
    }

    public Thread getErrorLabelThread(){
        return errorLabelThread;
    }
}



