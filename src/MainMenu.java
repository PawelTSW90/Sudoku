import javax.swing.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;


public class MainMenu {
    MainMenuComponents mainMenuComponents = new MainMenuComponents(this);
    private final JPanel mainMenuPanel = new JPanel();
    private final JFrame mainMenuFrame = new JFrame();


    public MainMenu() {
        setMainMenu();
    }

    public JFrame getMainMenuFrame(){
        return mainMenuFrame;
    }
    public void setMainMenu() {
        mainMenuPanel.setLayout(null);
        mainMenuPanel.add(mainMenuComponents.titleLabel());
        mainMenuPanel.add(mainMenuComponents.startButton());
        mainMenuPanel.add(mainMenuComponents.exitButton());
        mainMenuPanel.add(mainMenuComponents.highScoresButton());
        mainMenuPanel.add(mainMenuComponents.createdByLabel());
        mainMenuPanel.add(mainMenuComponents.backgroundLabel());
        mainMenuPanel.setFocusable(true);
        setMainMenuFrame();
    }

    public void generateNewBoard() {

        if (mainMenuFrame.getContentPane().getComponents().length > 1) {
            while (mainMenuFrame.getContentPane().getComponents().length>1)
            mainMenuFrame.getContentPane().remove(mainMenuFrame.getContentPane().getComponents().length-1);
        }
        SudokuBoard board = new SudokuBoard(mainMenuFrame, this);
        mainMenuFrame.add(board.createSudokuBoard());
        mainMenuFrame.getContentPane().getComponent(0).setVisible(false);
    }

    public void setMainMenuFrame(){
        mainMenuFrame.add(mainMenuPanel);
        mainMenuFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        mainMenuFrame.setUndecorated(true);
        mainMenuFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainMenuFrame.setVisible(true);
    }

}




