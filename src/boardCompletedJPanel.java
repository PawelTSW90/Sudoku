import javax.swing.*;
import java.awt.*;



public class boardCompletedJPanel {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int screenHeight = screenSize.height;
    int screenWidth = screenSize.width;
    JPanel boardCompleted = new JPanel();

    public JPanel boardCompletedMessage(){
        boardCompleted.setLayout(null);

        JLabel background = new JLabel();
        background.setBounds(0, 0, screenWidth, screenHeight);
        background.setIcon(new ImageIcon("./Visuals/sudoku-background.jpg"));

        boardCompleted.setFocusable(true);
        JLabel congratulations = new JLabel();

        congratulations.setBounds(screenWidth/2-870/2,screenHeight/2-1000/2, 870, 150);
        congratulations.setFont(new Font(null, Font.ITALIC, 80));
        congratulations.setText("CONGRATULATIONS!!!");
        boardCompleted.add(congratulations);
        boardCompleted.add(background);


        return boardCompleted;
    }

}
