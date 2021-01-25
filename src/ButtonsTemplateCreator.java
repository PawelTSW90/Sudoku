import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ButtonsTemplateCreator {
    int square;
    int column;
    int row;
    String name;
    String value;
    static List<Button> keypadButtons = new ArrayList<>();
    List<Button> boardButtons = new ArrayList<>();
    static List<ButtonsTemplateCreator> buttonsValues = new ArrayList<>();

    List<ButtonCreator> buttonsTemplateList = new ArrayList<>();


    public void createBoardTemplate() {
        int columnNumber = 1;
        int rowNumber = 1;
        for (int x = 0; x < 9; x++) {

            ButtonCreator creator = new ButtonCreator();
            creator.setButton(boardButtons.get(x));
            creator.setValue("");
            creator.setName("");
            creator.setSquare(1);
            if (columnNumber == 4) {
                columnNumber = 1;
            }
            creator.setColumn(columnNumber);
            columnNumber++;
            if (x == 3) {
                rowNumber = 2;
            } else if (x == 6) {
                rowNumber = 3;
            }
            creator.setRow(rowNumber);
            buttonsTemplateList.add(creator);

        }
        columnNumber = 4;
        rowNumber = 1;
        for (int x = 9; x < 18; x++) {
            ButtonCreator creator = new ButtonCreator();
            creator.setButton(boardButtons.get(x));
            creator.setValue("");
            creator.setName("");
            creator.setSquare(2);
            if (columnNumber == 7){
                columnNumber = 4;

            }
            creator.setColumn(columnNumber);
            columnNumber++;
            if(x == 12){
                rowNumber = 2;
            } else if(x == 15){
                rowNumber = 3;
            }
            creator.setRow(rowNumber);
            buttonsTemplateList.add(creator);
        }





        for (int x = 18; x < 27; x++) {
            ButtonCreator creator = new ButtonCreator();
            creator.setButton(boardButtons.get(x));
            creator.setValue("");
            creator.setName("");
            creator.setSquare(3);
        }
        for (int x = 27; x < 36; x++) {
            ButtonCreator creator = new ButtonCreator();
            creator.setButton(boardButtons.get(x));
            creator.setValue("");
            creator.setName("");
            creator.setSquare(4);
        }
        for (int x = 36; x < 45; x++) {
            ButtonCreator creator = new ButtonCreator();
            creator.setButton(boardButtons.get(x));
            creator.setValue("");
            creator.setName("");
            creator.setSquare(5);
        }
        for (int x = 45; x < 54; x++) {
            ButtonCreator creator = new ButtonCreator();
            creator.setButton(boardButtons.get(x));
            creator.setValue("");
            creator.setName("");
            creator.setSquare(6);
        }
        for (int x = 54; x < 63; x++) {
            ButtonCreator creator = new ButtonCreator();
            creator.setButton(boardButtons.get(x));
            creator.setValue("");
            creator.setName("");
            creator.setSquare(7);
        }
        for (int x = 63; x < 72; x++) {
            ButtonCreator creator = new ButtonCreator();
            creator.setButton(boardButtons.get(x));
            creator.setValue("");
            creator.setName("");
            creator.setSquare(8);
        }
        for (int x = 72; x < 81; x++) {
            ButtonCreator creator = new ButtonCreator();
            creator.setButton(boardButtons.get(x));
            creator.setValue("");
            creator.setName("");
            creator.setSquare(9);
        }

    }


    public void refactorButtonList() {
        //setting buttonsValues row positions
        for (int x = 1; x < 10; x++) {
            ButtonsTemplateCreator button = new ButtonsTemplateCreator();
            button.setRow(1);
            button.setValue("");
            button.setName("");
            ButtonsTemplateCreator.buttonsValues.add(button);
        }

        for (int x = 10; x < 19; x++) {
            ButtonsTemplateCreator button = new ButtonsTemplateCreator();
            button.setRow(2);
            button.setValue("");
            button.setName("");
            ButtonsTemplateCreator.buttonsValues.add(button);

        }
        for (int x = 19; x < 28; x++) {
            ButtonsTemplateCreator button = new ButtonsTemplateCreator();
            button.setRow(3);
            button.setValue("");
            button.setName("");
            ButtonsTemplateCreator.buttonsValues.add(button);

        }
        for (int x = 28; x < 37; x++) {
            ButtonsTemplateCreator button = new ButtonsTemplateCreator();
            button.setRow(4);
            button.setValue("");
            button.setName("");
            ButtonsTemplateCreator.buttonsValues.add(button);

        }
        for (int x = 37; x < 46; x++) {
            ButtonsTemplateCreator button = new ButtonsTemplateCreator();
            button.setRow(5);
            button.setValue("");
            button.setName("");
            ButtonsTemplateCreator.buttonsValues.add(button);

        }
        for (int x = 46; x < 55; x++) {
            ButtonsTemplateCreator button = new ButtonsTemplateCreator();
            button.setRow(6);
            button.setValue("");
            button.setName("");
            ButtonsTemplateCreator.buttonsValues.add(button);

        }
        for (int x = 55; x < 64; x++) {
            ButtonsTemplateCreator button = new ButtonsTemplateCreator();
            button.setRow(7);
            button.setValue("");
            button.setName("");
            ButtonsTemplateCreator.buttonsValues.add(button);

        }
        for (int x = 64; x < 73; x++) {
            ButtonsTemplateCreator button = new ButtonsTemplateCreator();
            button.setRow(8);
            button.setValue("");
            button.setName("");
            ButtonsTemplateCreator.buttonsValues.add(button);

        }
        for (int x = 73; x < 82; x++) {
            ButtonsTemplateCreator button = new ButtonsTemplateCreator();
            button.setRow(9);
            button.setValue("");
            button.setName("");
            ButtonsTemplateCreator.buttonsValues.add(button);

        }
        //setting buttonsValues column positions
        for (int x = 0; x < 81; x++) {
            for (int y = 1; y < 10; y++) {
                ButtonsTemplateCreator.buttonsValues.get(x).setColumn(y);
                if (x < 80) {
                    x++;
                    if (y == 9) {
                        x--;
                    }
                }

            }
        }

        //setting buttonsValues square positions
        for (int x = 0; x < 21; x++) {
            ButtonsTemplateCreator.buttonsValues.get(x).setSquare(1);
            if (x == 2 || x == 11) {
                x += 6;
            }

        }
        for (int x = 3; x < 24; x++) {
            ButtonsTemplateCreator.buttonsValues.get(x).setSquare(2);
            if (x == 5 || x == 14) {
                x += 6;
            }

        }
        for (int x = 6; x < 27; x++) {
            ButtonsTemplateCreator.buttonsValues.get(x).setSquare(3);
            if (x == 8 || x == 17) {
                x += 6;
            }

        }
        for (int x = 27; x < 48; x++) {
            ButtonsTemplateCreator.buttonsValues.get(x).setSquare(4);
            if (x == 29 || x == 38) {
                x += 6;
            }
        }
        for (int x = 30; x < 51; x++) {
            ButtonsTemplateCreator.buttonsValues.get(x).setSquare(5);
            if (x == 32 || x == 41) {
                x += 6;
            }

        }
        for (int x = 33; x < 54; x++) {
            ButtonsTemplateCreator.buttonsValues.get(x).setSquare(6);
            if (x == 35 || x == 44) {
                x += 6;
            }

        }
        for (int x = 54; x < 75; x++) {
            ButtonsTemplateCreator.buttonsValues.get(x).setSquare(7);
            if (x == 56 || x == 65) {
                x += 6;
            }

        }
        for (int x = 57; x < 78; x++) {
            ButtonsTemplateCreator.buttonsValues.get(x).setSquare(8);
            if (x == 59 || x == 68) {
                x += 6;
            }

        }
        for (int x = 60; x < 81; x++) {
            ButtonsTemplateCreator.buttonsValues.get(x).setSquare(9);
            if (x == 62 || x == 71) {
                x += 6;
            }

        }
        //setting boardButtons locations
        for (int x = 0; x < 25; x = x + 3) {
            boardButtons.get(x).setName(boardButtons.get(x).getName() + " C1");
        }
        for (int x = 1; x < 26; x = x + 3) {
            boardButtons.get(x).setName(boardButtons.get(x).getName() + " C2");
        }
        for (int x = 2; x < 27; x = x + 3) {
            boardButtons.get(x).setName(boardButtons.get(x).getName() + " C3");
        }
        for (int x = 27; x < 52; x = x + 3) {
            boardButtons.get(x).setName(boardButtons.get(x).getName() + " C4");
        }
        for (int x = 28; x < 53; x = x + 3) {
            boardButtons.get(x).setName(boardButtons.get(x).getName() + " C5");
        }
        for (int x = 29; x < 54; x = x + 3) {
            boardButtons.get(x).setName(boardButtons.get(x).getName() + " C6");
        }
        for (int x = 54; x < 79; x = x + 3) {
            boardButtons.get(x).setName(boardButtons.get(x).getName() + " C7");
        }
        for (int x = 55; x < 80; x = x + 3) {
            boardButtons.get(x).setName(boardButtons.get(x).getName() + " C8");
        }
        for (int x = 56; x < 81; x = x + 3) {
            boardButtons.get(x).setName(boardButtons.get(x).getName() + " C9");
        }
        for (int y = 0; y < 57; y++) {
            boardButtons.get(y).setName(boardButtons.get(y).getName() + " R1");
            if (y == 2 || y == 29) {
                y = y + 24;
            } else if (y == 56) {
                break;
            }
        }
        for (int y = 3; y < 60; y++) {
            boardButtons.get(y).setName(boardButtons.get(y).getName() + " R2");
            if (y == 5 || y == 32) {
                y = y + 24;
            } else if (y == 59) {
                break;
            }
        }
        for (int y = 6; y < 63; y++) {
            boardButtons.get(y).setName(boardButtons.get(y).getName() + " R3");
            if (y == 8 || y == 35) {
                y = y + 24;
            } else if (y == 62) {
                break;
            }
        }
        for (int y = 9; y < 66; y++) {
            boardButtons.get(y).setName(boardButtons.get(y).getName() + " R4");
            if (y == 11 || y == 38) {
                y = y + 24;
            } else if (y == 65) {
                break;
            }
        }
        for (int y = 12; y < 69; y++) {
            boardButtons.get(y).setName(boardButtons.get(y).getName() + " R5");
            if (y == 14 || y == 41) {
                y = y + 24;
            } else if (y == 68) {
                break;
            }
        }
        for (int y = 15; y < 72; y++) {
            boardButtons.get(y).setName(boardButtons.get(y).getName() + " R6");
            if (y == 17 || y == 44) {
                y = y + 24;
            } else if (y == 71) {
                break;
            }
        }
        for (int y = 18; y < 75; y++) {
            boardButtons.get(y).setName(boardButtons.get(y).getName() + " R7");
            if (y == 20 || y == 47) {
                y = y + 24;
            } else if (y == 74) {
                break;
            }
        }
        for (int y = 21; y < 78; y++) {
            boardButtons.get(y).setName(boardButtons.get(y).getName() + " R8");
            if (y == 23 || y == 50) {
                y = y + 24;
            } else if (y == 77) {
                break;
            }
        }
        for (int y = 24; y < 81; y++) {
            boardButtons.get(y).setName(boardButtons.get(y).getName() + " R9");
            if (y == 26 || y == 53) {
                y = y + 24;
            } else if (y == 80) {
                break;
            }
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName(ButtonsTemplateCreator button) {
        return button.name;
    }

    public String getValue(ButtonsTemplateCreator button) {
        return button.value;
    }

    public int getSquare() {
        return square;
    }

    public void setSquare(int square) {
        this.square = square;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }
}
