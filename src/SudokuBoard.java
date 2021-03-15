import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


public class SudokuBoard {
    JFrame mainFrame;
    StringBuffer timeCounter;
    private boolean helpOn = false;
    private boolean soundOn = true;
    private boolean erase = false;
    JPanel sudokuBoardPanel = new JPanel();
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int screenHeight = screenSize.height;
    int screenWidth = screenSize.width;
    ButtonsTemplateCreator buttonsTemplateCreator = new ButtonsTemplateCreator(this);
    ButtonCreator buttonCreator = new ButtonCreator();
    SudokuGenerator generator = new SudokuGenerator();
    SoundClass sound = new SoundClass();
    TimerClass time = new TimerClass(this);
    ErrorChecker error = new ErrorChecker();
    HighScoresCreator highScoresCreator = new HighScoresCreator();

    public SudokuBoard(JFrame mainFrame){
        this.mainFrame = mainFrame;

    }


    public JPanel createSudokuBoard() {
        sudokuBoardPanel.setLayout(null);
        sudokuBoardPanel.add(drawExitQuestion());
        sudokuBoardPanel.add(drawBoardCompletedWrongMessage());
        sudokuBoardPanel.add(drawSudokuKeypad());
        sudokuBoardPanel.add(drawSudokuBoard());
        sudokuBoardPanel.add(drawTimerLabel());
        sudokuBoardPanel.add(drawSoundLabel());
        sudokuBoardPanel.add(drawHelpLabel());
        sudokuBoardPanel.add(drawEraseButton());
        sudokuBoardPanel.add(background());
        sudokuBoardPanel.setFocusable(true);
        generator.displayBoard(buttonsTemplateCreator);

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
                            sudokuBoardPanel.getComponent(0).setVisible(true);
                            time.pauseThread();
                        } else {
                            disableBackground(1);
                            sudokuBoardPanel.getComponent(0).setVisible(false);
                            time.resumeThread();
                        }


                    }
                } if(code == 81){
                    sudokuBoardPanel.getComponent(1).setVisible(true);
                    sudokuBoardPanel.setFocusable(false);
                    BoardCompletedJPanel boardCompletedJPanel = new BoardCompletedJPanel(SudokuBoard.this, highScoresCreator);
                    mainFrame.add(boardCompletedJPanel.boardCompletedMessage());
                    mainFrame.getContentPane().getComponent(1).setVisible(false);
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        time.setTimer();
        highScoresCreator.writeScore("Paweł", "00:13:34", 8);
        return sudokuBoardPanel;

    }

    //method is setting background
    public JLabel background() {

        JLabel backgroundLabel = new JLabel();
        backgroundLabel.setBounds(0, 0, screenWidth, screenHeight);
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
        buttonsTemplateCreator.createBoardTemplate(buttonCreator.getBoardButtons(), buttonCreator.getKeypadButtons(), generator, sound);
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
        question.setBounds(screenWidth / 2 - 500 / 2, screenHeight / 2 - 400 / 2, 500, 400);
        JButton qButton = new JButton(" Would you like to:");
        qButton.setBorderPainted(false);
        JButton startOver = new JButton("Start over");
        JButton goBack = new JButton("Continue");
        JButton quit = new JButton("Exit");
        startOver.addMouseListener(new MouseListenerClass(exit, this));
        goBack.addMouseListener(new MouseListenerClass(exit, this));
        quit.addMouseListener(new MouseListenerClass(exit, this));
        quit.setFont(new Font(null, Font.PLAIN, 40));
        startOver.setFont(new Font(null, Font.PLAIN, 40));
        qButton.setFont(new Font(null, Font.PLAIN, 40));
        goBack.setFont(new Font(null, Font.PLAIN, 40));
        JButtonConfigure(startOver);
        JButtonConfigure(qButton);
        JButtonConfigure(quit);
        JButtonConfigure(goBack);
        question.add(qButton);
        question.add(startOver);
        question.add(goBack);
        question.add(quit);
        question.setVisible(false);
        quit.addActionListener(e -> System.exit(0));
        goBack.addActionListener(e -> {
            sudokuBoardPanel.setFocusable(true);
            disableBackground(1);
            time.resumeThread();
            sudokuBoardPanel.getComponent(0).setVisible(false);

        });
        startOver.addActionListener(e -> {
            sudokuBoardPanel.setFocusable(true);
            sudokuBoardPanel.getComponent(0).setVisible(false);
            disableBackground(1);
            time = new TimerClass(this);
            time.setTimer();
            generator.resetBoard(buttonsTemplateCreator);
        });
        return question;
    }

    public void JButtonConfigure(JButton button) {
        //button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusable(false);
    }

    public JLabel drawTimerLabel() {


        JLabel timerLabel = new JLabel();
        timerLabel.setFont(new Font(null, Font.PLAIN, 80));
        timerLabel.setBounds((screenWidth / 2) + 600, (screenHeight / 2) + 200, 500, 500);
        timerLabel.setVisible(true);
        return timerLabel;

    }



    public JLabel drawSoundLabel() {
        JLabel sound = new JLabel("Sounds");
        sound.setForeground(new Color(0, 102, 0));
        sound.setBackground(Color.BLACK);
        sound.setFocusable(false);
        sound.setFont(new Font(null, Font.ITALIC, 80));
        sound.setBounds((screenWidth / 2) + 600, (screenHeight / 2) + 50, 400, 100);
        sound.setVisible(true);
        sound.addMouseListener(new MouseListenerClass(sudokuBoardPanel, this) {
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
        });
        return sound;
    }

    public JLabel drawHelpLabel() {
        JLabel help = new JLabel("Help");
        help.setFocusable(false);
        help.setForeground(new Color(102, 0, 0));
        help.setFont(new Font(null, Font.ITALIC, 80));
        help.setBounds((screenWidth / 2) + 600, (screenHeight / 2) + 200, 400, 100);
        help.setVisible(true);
        help.addMouseListener(new MouseListenerClass(sudokuBoardPanel, this) {
            @Override
            public void mousePressed(MouseEvent e) {
                Component component = sudokuBoardPanel.getComponent(6);
                if (helpOn) {
                    component.setForeground(new Color(102, 0, 0));
                    setHelpOn(false);
                    error.restoreButtonsColors(buttonsTemplateCreator);
                } else {
                    help.setForeground(new Color(0, 102, 0));
                    setHelpOn(true);
                    error.checkIfThereAreErrors(buttonsTemplateCreator, generator, sound);

                }
            }
        });
        return help;

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


    public JPanel drawBoardCompletedWrongMessage() {
        JPanel wrong = new JPanel();
        wrong.setBackground(new Color(245, 232, 211));
        wrong.setLayout(new GridLayout(4, 1));
        wrong.setBorder(BorderFactory.createEtchedBorder(Color.GRAY, Color.PINK));
        wrong.setBounds(screenWidth / 2 - 500 / 2, screenHeight / 2 - 400 / 2, 500, 400);
        JButton oops = new JButton("Oops! Something went wrong! Would you like to:");
        oops.setBorderPainted(false);
        JButton startOver = new JButton("Start over");
        JButton goBack = new JButton("Continue");
        JButton quit = new JButton("Exit");
        startOver.addMouseListener(new MouseListenerClass(wrong, this));
        goBack.addMouseListener(new MouseListenerClass(wrong, this));
        quit.addMouseListener(new MouseListenerClass(wrong, this));
        quit.setFont(new Font(null, Font.PLAIN, 40));
        startOver.setFont(new Font(null, Font.PLAIN, 40));
        oops.setFont(new Font(null, Font.PLAIN, 40));
        goBack.setFont(new Font(null, Font.PLAIN, 40));
        JButtonConfigure(startOver);
        JButtonConfigure(oops);
        JButtonConfigure(quit);
        JButtonConfigure(goBack);
        wrong.add(oops);
        wrong.add(startOver);
        wrong.add(goBack);
        wrong.add(quit);
        wrong.setVisible(false);
        wrong.setFocusable(true);
        quit.addActionListener(e -> System.exit(0));
        goBack.addActionListener(e -> {
            sudokuBoardPanel.setFocusable(true);
            disableBackground(1);
            time.resumeThread();
            sudokuBoardPanel.getComponent(1).setVisible(false);

        });
        startOver.addActionListener(e -> {
            sudokuBoardPanel.setFocusable(true);
            sudokuBoardPanel.getComponent(1).setVisible(false);
            disableBackground(1);
            time = new TimerClass(this);
            time.setTimer();
            generator.resetBoard(buttonsTemplateCreator);
        });
        return wrong;


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

    public JButton drawEraseButton() {
        JButton erase = new JButton();
        erase.setFocusable(false);
        erase.setBounds((screenWidth / 2) + 838, (screenHeight / 2) - 135, 100, 75);
        erase.setIcon(new ImageIcon("./Visuals/erase.png"));
        erase.setBorderPainted(false);
        erase.setContentAreaFilled(false);
        erase.addActionListener(e -> {

            if (!this.erase) {
                this.erase = true;
                disableBackground(0);
                sound.tick(this);
            } else {
                sound.tick(this);
                disableBackground(1);
                this.erase = false;
            }


        });
        return erase;
    }


    public boolean getIsErase() {
        return erase;
    }

    public void showCompletedMessage(){

        sudokuBoardPanel.getComponent(0).setVisible(false);
        sudokuBoardPanel.getComponent(1).setVisible(false);
        sudokuBoardPanel.getComponent(2).setVisible(false);
        sudokuBoardPanel.getComponent(3).setVisible(false);
        sudokuBoardPanel.getComponent(4).setVisible(false);
        sudokuBoardPanel.getComponent(5).setVisible(false);
        sudokuBoardPanel.getComponent(6).setVisible(false);
        sudokuBoardPanel.getComponent(7).setVisible(false);
        sudokuBoardPanel.getComponent(9).setVisible(false);
        sudokuBoardPanel.getComponent(8).setVisible(true);


    }


}



