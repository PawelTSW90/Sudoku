import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


public class SudokuBoard {
    JPanel sudokuBoardPanel = new JPanel();
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int screenHeight = screenSize.height;
    int screenWidth = screenSize.width;
    ButtonsTemplateCreator buttonsTemplateCreator = new ButtonsTemplateCreator();
    ButtonCreator buttonCreator = new ButtonCreator();
    SudokuGenerator generator = new SudokuGenerator();
    SoundClass sound = new SoundClass();
    TimerClass time = new TimerClass(this);


    public JPanel createSudokuBoard() {
        sudokuBoardPanel.setLayout(null);
        sudokuBoardPanel.add(exitQuestion());
        sudokuBoardPanel.add(drawSudokuKeypad());
        sudokuBoardPanel.add(drawSudokuBoard());
        sudokuBoardPanel.add(timerLabel());
        sudokuBoardPanel.add(soundLabel());
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

                    disableBackground(0);
                    disableKeypad(sudokuBoardPanel.getComponent(1), 0);
                    sudokuBoardPanel.getComponent(0).setVisible(true);
                    time.pauseThread();
                    sudokuBoardPanel.setFocusable(false);


                }

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        time.setTimer();
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

    //method is creating sudoku board
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

    public JPanel exitQuestion() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
        mainPanel.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                int code = e.getKeyCode();
                if (code == 27) {
                    sudokuBoardPanel.setFocusable(true);

                    disableBackground(1);
                    disableKeypad(sudokuBoardPanel.getComponent(1), 1);
                    time.resumeThread();
                    sudokuBoardPanel.getComponent(0).setVisible(false);


                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });


        JButton question = new JButton("Are you sure?");
        JButton yes = new JButton("Yes");
        JButton no = new JButton("No");
        question.setFont(new Font(null, Font.PLAIN, 40));
        yes.setFont(new Font(null, Font.PLAIN, 40));
        no.setFont(new Font(null, Font.PLAIN, 40));
        JButtonConfigure(no);
        JButtonConfigure(yes);
        JButtonConfigure(question);
        mainPanel.setBounds(screenWidth / 2 - 500 / 2, screenHeight / 2 - 150 / 2, 500, 150);
        mainPanel.add(question);
        mainPanel.add(yes);
        mainPanel.add(no);
        yes.setForeground(new Color(54, 121, 34));
        no.setForeground(new Color(144, 44, 19));
        mainPanel.setFocusable(true);
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        yes.addActionListener(e -> System.exit(0));
        no.addActionListener(e -> {
            sudokuBoardPanel.setFocusable(true);
            disableBackground(1);
            disableKeypad(sudokuBoardPanel.getComponent(1), 1);
            time.resumeThread();
            mainPanel.setVisible(false);

        });
        yes.addMouseListener(new MouseListenerClass(mainPanel){
            @Override
            public void mouseReleased(MouseEvent e) {
                panel.getComponent(1).setForeground(new Color(54, 121, 34));

            }
            @Override
            public void mousePressed(MouseEvent e) {
                panel.getComponent(1).setForeground(Color.BLACK);
                new SoundClass().tick();


            }
        });
        no.addMouseListener(new MouseListenerClass(mainPanel));
        mainPanel.setVisible(false);
        return mainPanel;
    }

    public void JButtonConfigure(JButton button) {
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusable(false);
    }

    public JLabel timerLabel() {


        JLabel timerLabel = new JLabel();
        timerLabel.setFont(new Font(null, Font.PLAIN, 80));
        timerLabel.setBounds((screenWidth / 2) + 600, (screenHeight / 2) + 200, 500, 500);
        timerLabel.setVisible(true);
        return timerLabel;

    }

    public void setTimerButton(Component component, String time) {
        ((JLabel) component).setText(time);
    }

    public JLabel soundLabel() {
        JLabel sound = new JLabel("Sounds");
        sound.setFont(new Font(null, Font.PLAIN, 80));
        sound.setBounds((screenWidth / 2) + 600, (screenHeight / 2) - 200, 400, 400);
        sound.setVisible(true);
        return sound;
    }

    public void disableBackground(int tmp) {
        ArrayList<Component> sudokuBoardList = new ArrayList<>();
        ArrayList<Component> sudokuKeypadList = new ArrayList<>();
        Component sudokuBoard = sudokuBoardPanel.getComponent(2);
        Component sudokuKeypad = sudokuBoardPanel.getComponent(1);


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

            for (Component component : sudokuBoardList) {
                component.setEnabled(false);
            }
            for
            (Component component2 : sudokuKeypadList) {
                component2.setEnabled(false);
            }
        } else {
            for (Component component : sudokuBoardList) {
                component.setEnabled(true);
            }
            for (Component component2 : sudokuKeypadList) {
                component2.setEnabled(true);
            }

        }


    }

    public void disableKeypad(Component container, int tmp) {
        ArrayList<Component> list = new ArrayList<>();


        for (int x = 0; x < ((Container) container).getComponents().length; x++) {
            Component currentComponent = ((Container) container).getComponent(x);
            list.add(currentComponent);


        }
        if (tmp == 0) {

            for (Component component : list) {
                component.setEnabled(false);
            }
        } else {
            for (Component component : list) {
                component.setEnabled(true);
            }

        }
    }


}



