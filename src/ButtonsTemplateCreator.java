import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ButtonsTemplateCreator {

    private List<ButtonCreator> buttonsTemplateList = new ArrayList<>();


//method is creating template button list, each cell have button and position on board assigned
    public void createBoardTemplate(List<Button> buttonList) {


        int columnNumber = 1;
        int rowNumber = 1;
        for (int x = 0; x < 9; x++) {
            ButtonCreator creator = new ButtonCreator();
            creator.setButton(buttonList.get(x));
            creator.getButton().addActionListener(new ButtonInteract(creator.getButton(),this, null));
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

            creator.setButton(buttonList.get(x));
            creator.getButton().addActionListener(new ButtonInteract(creator.getButton(), this, null));
            creator.setValue("");
            creator.setName("");
            creator.setSquare(2);
            if (columnNumber == 7) {
                columnNumber = 4;

            }
            creator.setColumn(columnNumber);
            columnNumber++;
            if (x == 12) {
                rowNumber = 2;
            } else if (x == 15) {
                rowNumber = 3;
            }
            creator.setRow(rowNumber);
            buttonsTemplateList.add(creator);
        }


        columnNumber = 7;
        rowNumber = 1;
        for (int x = 18; x < 27; x++) {
            ButtonCreator creator = new ButtonCreator();
            creator.setButton(buttonList.get(x));
            creator.getButton().addActionListener(new ButtonInteract(creator.getButton(), this, null));
            creator.setValue("");
            creator.setName("");
            creator.setSquare(3);
            if (columnNumber == 10) {
                columnNumber = 7;
            }
            creator.setColumn(columnNumber);
            columnNumber++;

            if (x == 21) {
                rowNumber = 2;
            } else if (x == 24) {
                rowNumber = 3;
            }
            creator.setRow(rowNumber);
            buttonsTemplateList.add(creator);
        }

        columnNumber = 1;
        rowNumber = 4;
        for (int x = 27; x < 36; x++) {
            ButtonCreator creator = new ButtonCreator();
            creator.setButton(buttonList.get(x));
            creator.getButton().addActionListener(new ButtonInteract(creator.getButton(), this, null));
            creator.setValue("");
            creator.setName("");
            creator.setSquare(4);
            if (columnNumber == 4) {
                columnNumber = 1;
            }
            creator.setColumn(columnNumber);
            columnNumber++;
            if (x == 30) {
                rowNumber = 5;
            } else if (x == 33) {
                rowNumber = 6;
            }
            creator.setRow(rowNumber);
            buttonsTemplateList.add(creator);
        }
        columnNumber = 4;
        rowNumber = 4;
        for (int x = 36; x < 45; x++) {
            ButtonCreator creator = new ButtonCreator();
            creator.setButton(buttonList.get(x));
            creator.getButton().addActionListener(new ButtonInteract(creator.getButton(), this, null));
            creator.setValue("");
            creator.setName("");
            creator.setSquare(5);
            if (columnNumber == 7) {
                columnNumber = 4;
            }
            creator.setColumn(columnNumber);
            columnNumber++;
            if (x == 39) {
                rowNumber = 5;
            } else if (x == 42) {
                rowNumber = 6;
            }
            creator.setRow(rowNumber);
            buttonsTemplateList.add(creator);
        }
        columnNumber = 7;
        rowNumber = 4;
        for (int x = 45; x < 54; x++) {
            ButtonCreator creator = new ButtonCreator();
            creator.setButton(buttonList.get(x));
            creator.getButton().addActionListener(new ButtonInteract(creator.getButton(), this, null));
            creator.setValue("");
            creator.setName("");
            creator.setSquare(6);
            if (columnNumber == 10) {
                columnNumber = 7;
            }
            creator.setColumn(columnNumber);
            columnNumber++;
            if (x == 48) {
                rowNumber = 5;
            } else if (x == 51) {
                rowNumber = 6;
            }
            creator.setRow(rowNumber);
            buttonsTemplateList.add(creator);
        }

        columnNumber = 1;
        rowNumber = 7;
        for (int x = 54; x < 63; x++) {
            ButtonCreator creator = new ButtonCreator();
            creator.setButton(buttonList.get(x));
            creator.getButton().addActionListener(new ButtonInteract(creator.getButton(), this, null));
            creator.setValue("");
            creator.setName("");
            creator.setSquare(7);
            if (columnNumber == 4) {
                columnNumber = 1;
            }
            creator.setColumn(columnNumber);
            columnNumber++;
            if (x == 57) {
                rowNumber = 8;
            } else if (x == 60) {
                rowNumber = 9;
            }
            creator.setRow(rowNumber);
            buttonsTemplateList.add(creator);
        }
        columnNumber = 4;
        rowNumber = 7;
        for (int x = 63; x < 72; x++) {
            ButtonCreator creator = new ButtonCreator();
            creator.setButton(buttonList.get(x));
            creator.getButton().addActionListener(new ButtonInteract(creator.getButton(), this, null));
            creator.setValue("");
            creator.setName("");
            creator.setSquare(8);
            if (columnNumber == 7) {
                columnNumber = 4;
            }
            creator.setColumn(columnNumber);
            columnNumber++;
            if (x == 66) {
                rowNumber = 8;
            } else if (x == 69) {
                rowNumber = 9;
            }
            creator.setRow(rowNumber);
            buttonsTemplateList.add(creator);
        }
        columnNumber = 7;
        rowNumber = 7;
        for (int x = 72; x < 81; x++) {
            ButtonCreator creator = new ButtonCreator();
            creator.setButton(buttonList.get(x));
            creator.getButton().addActionListener(new ButtonInteract(creator.getButton(), this, null));
            creator.setValue("");
            creator.setName("");
            creator.setSquare(9);
            if (columnNumber == 10) {
                columnNumber = 7;
            }
            creator.setColumn(columnNumber);
            columnNumber++;
            if (x == 75) {
                rowNumber = 8;
            } else if (x == 78) {
                rowNumber = 9;
            }
            creator.setRow(rowNumber);
            buttonsTemplateList.add(creator);
        }
    }

    public List<ButtonCreator> getButtonsTemplateList() {
        return buttonsTemplateList;
    }
}
