package pawelDyjak.sudoku;
import pawelDyjak.sudoku.Components.*;
import javax.swing.*;
import java.awt.event.KeyEvent;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;


public class MainMenu {
    MainMenuComponents mainMenuComponents = new MainMenuComponents(this);
    private final JPanel mainMenuPanel = new JPanel();
    private final JFrame mainMenuFrame = new JFrame();


    public MainMenu() {
        setMainMenuPanel();
    }

        //method prepares main menu panel to display
    public void setMainMenuPanel() {
        mainMenuPanel.setLayout(null);
        mainMenuPanel.addKeyListener(new KeyListenerClass(){

            @Override
            public void keyPressed(KeyEvent e){
                int keyCode = e.getKeyCode();
                int escapeButton = 27;
                if(keyCode == escapeButton){
                    System.exit(0);
                }
            }
        });
        mainMenuPanel.add(mainMenuComponents.titleLabel());
        mainMenuPanel.add(mainMenuComponents.startButton());
        mainMenuPanel.add(mainMenuComponents.exitButton());
        mainMenuPanel.add(mainMenuComponents.highScoresButton());
        mainMenuPanel.add(mainMenuComponents.createdByLabel());
        mainMenuPanel.add(mainMenuComponents.backgroundLabel());
        mainMenuPanel.setFocusable(true);
        setMainMenuFrame();
    }
        //method prepares main frame
    public void setMainMenuFrame(){
        mainMenuFrame.add(mainMenuPanel);
        mainMenuFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        mainMenuFrame.setUndecorated(true);
        mainMenuFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainMenuFrame.setVisible(true);
    }
        //method generates new sudoku board
    public void generateNewBoard() {

        if (mainMenuFrame.getContentPane().getComponents().length > 1) {
            while (mainMenuFrame.getContentPane().getComponents().length>1)
                mainMenuFrame.getContentPane().remove(mainMenuFrame.getContentPane().getComponents().length-1);
        }
        SudokuBoard sudokuBoard = new SudokuBoard(mainMenuFrame);
        mainMenuFrame.add(sudokuBoard.createSudokuBoard());
        mainMenuFrame.getContentPane().getComponent(0).setVisible(false);
    }

    public JFrame getMainMenuFrame(){
        return mainMenuFrame;
    }

}




