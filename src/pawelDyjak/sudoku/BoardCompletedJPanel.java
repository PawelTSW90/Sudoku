package pawelDyjak.sudoku;
import javax.swing.*;
import java.awt.*;


public class BoardCompletedJPanel {
    private final SudokuBoard sudokuBoard;
    private final HighScoresCreator highScoresCreator;
    private final JPanel boardCompleted = new JPanel();
    private String playerName;
    private int playerPlace;


    public BoardCompletedJPanel(SudokuBoard sudokuBoard, HighScoresCreator highScoresCreator) {
        this.sudokuBoard = sudokuBoard;
        this.highScoresCreator = highScoresCreator;
    }

        //method draws board completed panel
    public JPanel boardCompletedMessage() {
        boardCompleted.setLayout(null);
        boardCompleted.setFocusable(true);
        JLabel boardCompleted = new JLabel();
        boardCompleted.setBounds(UtilityClass.getScreenWidth() / 2 - 550 / 2, UtilityClass.getScreenHeight() / 2 - 1100 / 2, 550, 150);
        boardCompleted.setFont(new Font(null, Font.ITALIC, 80));
        boardCompleted.setText("WELL DONE!!!");
        this.boardCompleted.add(sudokuBoard.getBoardCompletedComponents().drawTypeNamePanel());
        this.boardCompleted.add(boardCompleted);
        this.boardCompleted.add(sudokuBoard.getBoardCompletedComponents().drawTime());
        this.boardCompleted.add(highScoresCreator.highScore());
        this.boardCompleted.add(sudokuBoard.getBoardCompletedComponents().drawMainMenuButton());
        this.boardCompleted.add(sudokuBoard.getBoardCompletedComponents().drawExitButton());
        this.boardCompleted.add(sudokuBoard.getBoardCompletedComponents().drawBackground());
        this.boardCompleted.setVisible(true);


        return this.boardCompleted;
    }



    //method display or hide user name entry field depends on player score
    public void setUserNameLabel() {
        playerPlace = highScoresCreator.checkUserTime(sudokuBoard);
        Component enterName = boardCompleted.getComponent(0);

        if (playerPlace <= 10) {
            enterName.setVisible(true);
        } else {
            boardCompleted.getComponent(4).setVisible(true);
            boardCompleted.getComponent(5).setVisible(true);
        }

    }


    public int getPlayerPlace() {
        return playerPlace;
    }

    public void setPlayerPlace(int playerPlace) {
        this.playerPlace = playerPlace;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public JPanel getBoardCompleted() {
        return boardCompleted;
    }
}
