package pawelDyjak.sudoku;

import pawelDyjak.sudoku.Components.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Enumeration;
import java.util.jar.JarFile;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;


public class MainMenu {
    private final MainMenuComponents mainMenuComponents = new MainMenuComponents(this);
    private final HighScoresComponents highScoresComponents = new HighScoresComponents(this);
    private final JPanel mainMenuPanel = new JPanel();
    private final JFrame mainMenuFrame = new JFrame();


    public MainMenu() {
        setMainMenuPanel();
        createHighScoreFile();
        createEncryptedListFile();


    }

    //method prepares main menu panel to display
    public void setMainMenuPanel() {
        mainMenuPanel.setLayout(new GridBagLayout());
        mainMenuPanel.setBounds(0, 0, UtilityClass.getScreenWidth(), UtilityClass.getScreenHeight());
        mainMenuPanel.addKeyListener(new KeyListenerClass() {

            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                int escapeButton = 27;
                if (keyCode == escapeButton) {
                    System.exit(0);
                }
            }
        });

        mainMenuPanel.setOpaque(false);
        mainMenuPanel.setFocusable(true);
        configureAndAddMainMenuComponents();
        setMainMenuFrame();
    }

    public void createHighScoreFile() {
        String path = MainMenu.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        path = path.substring(0, path.lastIndexOf("/") + 1);
        try {
            String decodedPath = URLDecoder.decode(path, "UTF-8");
            decodedPath = decodedPath + "high_scores.brd";
            File highScoresBoard = new File(decodedPath);
            if (!highScoresBoard.exists()) {
                highScoresBoard.createNewFile();
                prepareHighScoresFile(decodedPath);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void prepareHighScoresFile(String fileLocation) {
        int position = 1;
        int minutes = 5;
        StringBuilder highScoresFile = new StringBuilder();
        for (int x = 0; x < 10; x++) {
            highScoresFile.append(position);
            highScoresFile.append(".");
            highScoresFile.append(" Empty");
            if (minutes > 9) {
                highScoresFile.append("  *00:" + minutes + ":00");
            } else {
                highScoresFile.append("  *00:0" + minutes + ":00");
            }
            highScoresFile.append("\n");
            position++;
            minutes++;
        }


        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileLocation));
            writer.write(highScoresFile.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createEncryptedListFile() {
        String path = MainMenu.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        path = path.substring(0, path.lastIndexOf("/") + 1);
        try {
            String decodedPath = URLDecoder.decode(path, "UTF-8");
            decodedPath = decodedPath + "encrypted_list.brd";
            File encryptedListFile = new File(decodedPath);
            if (!encryptedListFile.exists()) {
                encryptedListFile.createNewFile();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //method prepares main frame
    public void setMainMenuFrame() {
        JPanel backgroundPanel = new JPanel();
        backgroundPanel.setLayout(null);
        backgroundPanel.add(mainMenuComponents.backgroundLabel());
        mainMenuFrame.add(mainMenuPanel);
        mainMenuFrame.add(backgroundPanel);
        mainMenuFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        mainMenuFrame.setUndecorated(true);
        mainMenuFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainMenuFrame.setVisible(true);
    }

    //method generates new sudoku board
    public void generateNewBoard() {

        if (mainMenuFrame.getContentPane().getComponents().length > 2) {
            while (mainMenuFrame.getContentPane().getComponents().length > 2)
                mainMenuFrame.getContentPane().remove(mainMenuFrame.getContentPane().getComponents().length - 1);
        }
        SudokuBoard sudokuBoard = new SudokuBoard(mainMenuFrame);
        mainMenuFrame.add(sudokuBoard.createSudokuBoard());
        mainMenuFrame.getContentPane().getComponent(0).setVisible(false);
        mainMenuFrame.getContentPane().getComponent(1).setVisible(false);
    }

    public JFrame getMainMenuFrame() {
        return mainMenuFrame;
    }

    public HighScoresComponents getHighScoresComponents() {
        return highScoresComponents;
    }

    public void configureAndAddMainMenuComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.ipadx = 10;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.5;
        gbc.weighty = 0.5;
        mainMenuPanel.add(mainMenuComponents.titleLabel(), gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainMenuPanel.add(mainMenuComponents.startButton(), gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        mainMenuPanel.add(mainMenuComponents.highScoresButton(), gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        mainMenuPanel.add(mainMenuComponents.exitButton(), gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.insets = new Insets(0, UtilityClass.getScreenHeight() / 2 + UtilityClass.getScreenWidth() / 4, 0, 0);
        mainMenuPanel.add(mainMenuComponents.createdByLabel(), gbc);

    }

}




