package pawelDyjak.sudoku;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ButtonsTemplateCreator {

    final private List<ButtonCreator> boardButtonsTemplateList = new ArrayList<>();
    final private List<ButtonCreator> keypadButtonsTemplateList = new ArrayList<>();
    final private List<ButtonCreator> boardButtonsTemplateListForSudokuGenerator = new ArrayList<>();
    final private List<ButtonCreator> keypadButtonsTemplateListForSudokuGenerator = new ArrayList<>();
    private String buttonValueHolder;
    private final SudokuBoard sudokuBoard;

    public ButtonsTemplateCreator(SudokuBoard sudokuBoard) {
        this.sudokuBoard = sudokuBoard;
    }

//method is creating template keypad buttons list and template board button list, each cell have button and position assigned

    //creating keypad buttons
    public void createBoardTemplate(List<Button> buttonList, List<Button> keypadButtons, List<Button> buttonListForGenerator, List<Button> keypadListForGenerator) {
        ButtonInteract buttonInteract = sudokuBoard.getButtonInteract();
        List<ButtonCreator> boardButtonCreatorList = new ArrayList<>();
        List<ButtonCreator> boardButtonCreatorListForGenerator = new ArrayList<>();
        List<ButtonCreator> keypadButtonCreatorList = new ArrayList<>();
        //creating object holders for board buttons
        for(int x = 0; x<81; x++){
            boardButtonCreatorList.add(new ButtonCreator());
            boardButtonCreatorListForGenerator.add(new ButtonCreator());
        }
        //creating object holders for keypad buttons
        for(int x = 0; x<9; x++){
            keypadButtonCreatorList.add(new ButtonCreator());
        }

        int columnNumber = 1;
        int rowNumber = 1;


        for (int y = 0; y < 9; y++) {
            ButtonCreator creator = keypadButtonCreatorList.get(y);
            creator.setButton(keypadButtons.get(y));
            creator.getButton().addActionListener(buttonInteract);
            creator.getButton().addKeyListener(new ButtonsKeyListener(sudokuBoard,creator.getButton(), buttonInteract, this, sudokuBoard.getBoardChecker()));

                keypadButtonsTemplateList.add(creator);


        }

        //creating template board buttons

        for (int x = 0; x < 9; x++) {
            ButtonCreator creator = boardButtonCreatorList.get(x);
            ButtonCreator creatorForGenerator = boardButtonCreatorListForGenerator.get(x);
            creator.setValue("");
            creatorForGenerator.setValue("");
            creator.setButton(buttonList.get(x));
            creatorForGenerator.setButton(buttonListForGenerator.get(x));
            creator.getButton().addActionListener(buttonInteract);
            creatorForGenerator.getButton().addActionListener(buttonInteract);
            creator.getButton().addKeyListener(new ButtonsKeyListener(sudokuBoard,creator.getButton(), buttonInteract, this, sudokuBoard.getBoardChecker()));
            creatorForGenerator.getButton().addKeyListener(new ButtonsKeyListener(sudokuBoard,creator.getButton(), buttonInteract, this, sudokuBoard.getBoardChecker()));
            creator.setSquare(1);
            creatorForGenerator.setSquare(1);
            if (columnNumber == 4) {
                columnNumber = 1;
            }
            creator.setColumn(columnNumber);
            creatorForGenerator.setColumn(columnNumber);
            columnNumber++;
            if (x == 3) {
                rowNumber = 2;
            } else if (x == 6) {
                rowNumber = 3;
            }
            creator.setRow(rowNumber);
            creatorForGenerator.setRow(rowNumber);
                boardButtonsTemplateList.add(creator);
                boardButtonsTemplateListForSudokuGenerator.add(creatorForGenerator);

        }
        columnNumber = 4;
        rowNumber = 1;
        for (int x = 9; x < 18; x++) {
            ButtonCreator creator = boardButtonCreatorList.get(x);
            ButtonCreator creatorForGenerator = boardButtonCreatorListForGenerator.get(x);
            creator.setValue("");
            creatorForGenerator.setValue("");
            creator.setButton(buttonList.get(x));
            creatorForGenerator.setButton(buttonListForGenerator.get(x));
            creator.getButton().addActionListener(buttonInteract);
            creatorForGenerator.getButton().addActionListener(buttonInteract);
            creator.getButton().addKeyListener(new ButtonsKeyListener(sudokuBoard,creator.getButton(), buttonInteract, this, sudokuBoard.getBoardChecker()));
            creatorForGenerator.getButton().addKeyListener(new ButtonsKeyListener(sudokuBoard,creator.getButton(), buttonInteract, this, sudokuBoard.getBoardChecker()));
            creator.setSquare(2);
            creatorForGenerator.setSquare(2);
            if (columnNumber == 7) {
                columnNumber = 4;

            }
            creator.setColumn(columnNumber);
            creatorForGenerator.setColumn(columnNumber);
            columnNumber++;
            if (x == 12) {
                rowNumber = 2;
            } else if (x == 15) {
                rowNumber = 3;
            }
            creator.setRow(rowNumber);
            creatorForGenerator.setRow(rowNumber);
            boardButtonsTemplateList.add(creator);
            boardButtonsTemplateListForSudokuGenerator.add(creatorForGenerator);
        }


        columnNumber = 7;
        rowNumber = 1;
        for (int x = 18; x < 27; x++) {
            ButtonCreator creator = boardButtonCreatorList.get(x);
            ButtonCreator creatorForGenerator = boardButtonCreatorListForGenerator.get(x);
            creator.setValue("");
            creatorForGenerator.setValue("");
            creator.setButton(buttonList.get(x));
            creatorForGenerator.setButton(buttonListForGenerator.get(x));
            creator.getButton().addActionListener(buttonInteract);
            creatorForGenerator.getButton().addActionListener(buttonInteract);
            creator.getButton().addKeyListener(new ButtonsKeyListener(sudokuBoard,creator.getButton(), buttonInteract, this, sudokuBoard.getBoardChecker()));
            creatorForGenerator.getButton().addKeyListener(new ButtonsKeyListener(sudokuBoard,creator.getButton(), buttonInteract, this, sudokuBoard.getBoardChecker()));
            creator.setSquare(3);
            creatorForGenerator.setSquare(3);
            if (columnNumber == 10) {
                columnNumber = 7;
            }
            creator.setColumn(columnNumber);
            creatorForGenerator.setColumn(columnNumber);
            columnNumber++;

            if (x == 21) {
                rowNumber = 2;
            } else if (x == 24) {
                rowNumber = 3;
            }
            creator.setRow(rowNumber);
            creatorForGenerator.setRow(rowNumber);
            boardButtonsTemplateList.add(creator);
            boardButtonsTemplateListForSudokuGenerator.add(creatorForGenerator);
        }

        columnNumber = 1;
        rowNumber = 4;
        for (int x = 27; x < 36; x++) {
            ButtonCreator creator = boardButtonCreatorList.get(x);
            ButtonCreator creatorForGenerator = boardButtonCreatorListForGenerator.get(x);
            creator.setValue("");
            creatorForGenerator.setValue("");
            creator.setButton(buttonList.get(x));
            creatorForGenerator.setButton(buttonListForGenerator.get(x));
            creator.getButton().addActionListener(buttonInteract);
            creatorForGenerator.getButton().addActionListener(buttonInteract);
            creator.getButton().addKeyListener(new ButtonsKeyListener(sudokuBoard,creator.getButton(), buttonInteract, this, sudokuBoard.getBoardChecker()));
            creatorForGenerator.getButton().addKeyListener(new ButtonsKeyListener(sudokuBoard,creator.getButton(), buttonInteract, this, sudokuBoard.getBoardChecker()));
            creator.setSquare(4);
            creatorForGenerator.setSquare(4);
            if (columnNumber == 4) {
                columnNumber = 1;
            }
            creator.setColumn(columnNumber);
            creatorForGenerator.setColumn(columnNumber);
            columnNumber++;
            if (x == 30) {
                rowNumber = 5;
            } else if (x == 33) {
                rowNumber = 6;
            }
            creator.setRow(rowNumber);
            creatorForGenerator.setRow(rowNumber);
            boardButtonsTemplateList.add(creator);
            boardButtonsTemplateListForSudokuGenerator.add(creatorForGenerator);
        }
        columnNumber = 4;
        rowNumber = 4;
        for (int x = 36; x < 45; x++) {
            ButtonCreator creator = boardButtonCreatorList.get(x);
            ButtonCreator creatorForGenerator = boardButtonCreatorListForGenerator.get(x);
            creator.setValue("");
            creatorForGenerator.setValue("");
            creator.setButton(buttonList.get(x));
            creatorForGenerator.setButton(buttonListForGenerator.get(x));
            creator.getButton().addActionListener(buttonInteract);
            creatorForGenerator.getButton().addActionListener(buttonInteract);
            creator.getButton().addKeyListener(new ButtonsKeyListener(sudokuBoard,creator.getButton(), buttonInteract, this, sudokuBoard.getBoardChecker()));
            creatorForGenerator.getButton().addKeyListener(new ButtonsKeyListener(sudokuBoard,creator.getButton(), buttonInteract, this, sudokuBoard.getBoardChecker()));
            creator.setSquare(5);
            creatorForGenerator.setSquare(5);
            if (columnNumber == 7) {
                columnNumber = 4;
            }
            creator.setColumn(columnNumber);
            creatorForGenerator.setColumn(columnNumber);
            columnNumber++;
            if (x == 39) {
                rowNumber = 5;
            } else if (x == 42) {
                rowNumber = 6;
            }
            creator.setRow(rowNumber);
            creator.setRow(rowNumber);
            boardButtonsTemplateList.add(creator);
            boardButtonsTemplateListForSudokuGenerator.add(creatorForGenerator);
        }
        columnNumber = 7;
        rowNumber = 4;
        for (int x = 45; x < 54; x++) {
            ButtonCreator creator = boardButtonCreatorList.get(x);
            ButtonCreator creatorForGenerator = boardButtonCreatorListForGenerator.get(x);
            creator.setValue("");
            creatorForGenerator.setValue("");
            creator.setButton(buttonList.get(x));
            creatorForGenerator.setButton(buttonListForGenerator.get(x));
            creator.getButton().addActionListener(buttonInteract);
            creatorForGenerator.getButton().addActionListener(buttonInteract);
            creator.getButton().addKeyListener(new ButtonsKeyListener(sudokuBoard,creator.getButton(), buttonInteract, this, sudokuBoard.getBoardChecker()));
            creatorForGenerator.getButton().addKeyListener(new ButtonsKeyListener(sudokuBoard,creator.getButton(), buttonInteract, this, sudokuBoard.getBoardChecker()));
            creator.setSquare(6);
            creatorForGenerator.setSquare(6);
            if (columnNumber == 10) {
                columnNumber = 7;
            }
            creator.setColumn(columnNumber);
            creatorForGenerator.setColumn(columnNumber);
            columnNumber++;
            if (x == 48) {
                rowNumber = 5;
            } else if (x == 51) {
                rowNumber = 6;
            }
            creator.setRow(rowNumber);
            creatorForGenerator.setRow(rowNumber);
            boardButtonsTemplateList.add(creator);
            boardButtonsTemplateListForSudokuGenerator.add(creatorForGenerator);
        }

        columnNumber = 1;
        rowNumber = 7;
        for (int x = 54; x < 63; x++) {
            ButtonCreator creator = boardButtonCreatorList.get(x);
            ButtonCreator creatorForGenerator = boardButtonCreatorListForGenerator.get(x);
            creator.setValue("");
            creatorForGenerator.setValue("");
            creator.setButton(buttonList.get(x));
            creatorForGenerator.setButton(buttonListForGenerator.get(x));
            creator.getButton().addActionListener(buttonInteract);
            creatorForGenerator.getButton().addActionListener(buttonInteract);
            creator.getButton().addKeyListener(new ButtonsKeyListener(sudokuBoard,creator.getButton(), buttonInteract, this, sudokuBoard.getBoardChecker()));
            creatorForGenerator.getButton().addKeyListener(new ButtonsKeyListener(sudokuBoard,creator.getButton(), buttonInteract, this, sudokuBoard.getBoardChecker()));
            creator.setSquare(7);
            creatorForGenerator.setSquare(7);
            if (columnNumber == 4) {
                columnNumber = 1;
            }
            creator.setColumn(columnNumber);
            creatorForGenerator.setColumn(columnNumber);
            columnNumber++;
            if (x == 57) {
                rowNumber = 8;
            } else if (x == 60) {
                rowNumber = 9;
            }
            creator.setRow(rowNumber);
            creatorForGenerator.setRow(rowNumber);
            boardButtonsTemplateList.add(creator);
            boardButtonsTemplateListForSudokuGenerator.add(creatorForGenerator);
        }
        columnNumber = 4;
        rowNumber = 7;
        for (int x = 63; x < 72; x++) {
            ButtonCreator creator = boardButtonCreatorList.get(x);
            ButtonCreator creatorForGenerator = boardButtonCreatorListForGenerator.get(x);
            creator.setValue("");
            creatorForGenerator.setValue("");
            creator.setButton(buttonList.get(x));
            creatorForGenerator.setButton(buttonListForGenerator.get(x));
            creator.getButton().addActionListener(buttonInteract);
            creatorForGenerator.getButton().addActionListener(buttonInteract);
            creator.getButton().addKeyListener(new ButtonsKeyListener(sudokuBoard,creator.getButton(), buttonInteract, this, sudokuBoard.getBoardChecker()));
            creatorForGenerator.getButton().addKeyListener(new ButtonsKeyListener(sudokuBoard,creator.getButton(), buttonInteract, this, sudokuBoard.getBoardChecker()));
            creator.setSquare(8);
            creatorForGenerator.setSquare(8);
            if (columnNumber == 7) {
                columnNumber = 4;
            }
            creator.setColumn(columnNumber);
            creatorForGenerator.setColumn(columnNumber);
            columnNumber++;
            if (x == 66) {
                rowNumber = 8;
            } else if (x == 69) {
                rowNumber = 9;
            }
            creator.setRow(rowNumber);
            creatorForGenerator.setRow(rowNumber);
            boardButtonsTemplateList.add(creator);
            boardButtonsTemplateListForSudokuGenerator.add(creatorForGenerator);
        }
        columnNumber = 7;
        rowNumber = 7;
        for (int x = 72; x < 81; x++) {
            ButtonCreator creator = boardButtonCreatorList.get(x);
            ButtonCreator creatorForGenerator = boardButtonCreatorListForGenerator.get(x);
            creator.setValue("");
            creatorForGenerator.setValue("");
            creator.setButton(buttonList.get(x));
            creatorForGenerator.setButton(buttonListForGenerator.get(x));
            creator.getButton().addActionListener(buttonInteract);
            creatorForGenerator.getButton().addActionListener(buttonInteract);
            creator.getButton().addKeyListener(new ButtonsKeyListener(sudokuBoard,creator.getButton(), buttonInteract, this, sudokuBoard.getBoardChecker()));
            creatorForGenerator.getButton().addKeyListener(new ButtonsKeyListener(sudokuBoard,creator.getButton(), buttonInteract, this, sudokuBoard.getBoardChecker()));
            creator.setSquare(9);
            creatorForGenerator.setSquare(9);
            if (columnNumber == 10) {
                columnNumber = 7;
            }
            creator.setColumn(columnNumber);
            creatorForGenerator.setColumn(columnNumber);
            columnNumber++;
            if (x == 75) {
                rowNumber = 8;
            } else if (x == 78) {
                rowNumber = 9;
            }
            creator.setRow(rowNumber);
            creatorForGenerator.setRow(rowNumber);
            boardButtonsTemplateList.add(creator);
            boardButtonsTemplateListForSudokuGenerator.add(creatorForGenerator);
        }
    }

    public List<ButtonCreator> getBoardButtonsTemplateList() {
        return boardButtonsTemplateList;
    }

    public List<ButtonCreator> getKeypadButtonsTemplateList() {
        return keypadButtonsTemplateList;
    }

    public void setButtonValueHolder(String buttonValueHolder) {
        this.buttonValueHolder = buttonValueHolder;
    }

    public String getButtonValueHolder() {
        return buttonValueHolder;
    }

    public List<ButtonCreator> getBoardButtonsTemplateListForSudokuGenerator() {
        return boardButtonsTemplateListForSudokuGenerator;
    }

    public List<ButtonCreator> getKeypadButtonsTemplateListForSudokuGenerator() {
        return keypadButtonsTemplateListForSudokuGenerator;
    }
}
