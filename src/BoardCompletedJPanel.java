import javax.swing.*;
import java.awt.*;



public class BoardCompletedJPanel {
    SudokuBoard board;
    HighScoresCreator highScoresCreator;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int screenHeight = screenSize.height;
    int screenWidth = screenSize.width;
    JPanel boardCompleted = new JPanel();

    public BoardCompletedJPanel(SudokuBoard board, HighScoresCreator highScoresCreator){
        this.board = board;
        this.highScoresCreator = highScoresCreator;
    }


    public JPanel boardCompletedMessage(){
        boardCompleted.setLayout(null);
        boardCompleted.setFocusable(true);
        JLabel congratulations = new JLabel();
        congratulations.setBounds(screenWidth/2-550/2,screenHeight/2-1000/2, 550, 150);
        congratulations.setFont(new Font(null, Font.ITALIC, 80));
        congratulations.setText("WELL DONE!!!");
        boardCompleted.add(congratulations);
        boardCompleted.add(time());
        boardCompleted.add(highScore());
        boardCompleted.add(background());



        return boardCompleted;
    }

    public JLabel background(){
        JLabel background = new JLabel();
        background.setBounds(0, 0, screenWidth, screenHeight);
        background.setIcon(new ImageIcon("./Visuals/sudoku-background.jpg"));
        return background;
    }


    public JLabel time(){
        JLabel timeLabel = new JLabel("Total Time:" +"      "+board.timeCounter);
        timeLabel.setBounds(screenWidth/2-1000/2,screenHeight/2-1000/2, 1000, 400);
        timeLabel.setFont(new Font(null, Font.ITALIC, 80));
        return timeLabel;
    }

    public JLabel highScore(){
        JLabel panel = new JLabel();
        GridLayout layout = new GridLayout(10,0);
        layout.setVgap(30);
        panel.setLayout(layout);
        panel.setBounds(250, 350, 500, 700);
        JLabel firstName = new JLabel("1..........");
        JLabel secondName = new JLabel("2..........");
        JLabel thirdName = new JLabel("3..........");
        JLabel fourthName = new JLabel("4..........");
        JLabel fifthName = new JLabel("5..........");
        JLabel sixthName = new JLabel("6..........");
        JLabel seventhName = new JLabel("7..........");
        JLabel eightName = new JLabel("8..........");
        JLabel ninthName = new JLabel("9..........");
        JLabel tenthName = new JLabel("10..........");
        panel.add(firstName);
        panel.add(secondName);
        panel.add(thirdName);
        panel.add(fourthName);
        panel.add(fifthName);
        panel.add(sixthName);
        panel.add(seventhName);
        panel.add(eightName);
        panel.add(ninthName);
        panel.add(tenthName);
        setFont(firstName);
        setFont(secondName);
        setFont(thirdName);
        setFont(fourthName);
        setFont(fifthName);
        setFont(sixthName);
        setFont(seventhName);
        setFont(eightName);
        setFont(ninthName);
        setFont(tenthName);
        highScoresCreator.checkUserTime(board);
        return panel;
    }

    public void setFont(JLabel label){
        label.setFont(new Font(null, Font.ITALIC, 50));
    }







}
