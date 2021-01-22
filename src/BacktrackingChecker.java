import java.awt.*;

public class BacktrackingChecker {
    Buttons buttons = new Buttons();
    int square;
    int column;
    int row;

    public void checkIfSolvable() {
        BacktrackingChecker checker = new BacktrackingChecker();
        boolean increaseCurrentCell = false;
        boolean changePreviousCell = false;
        int numberToInput = 1;
        int currentValue;


        for (int x = 0; x < 81; x++) {


            if (changePreviousCell) {
                currentValue = Integer.parseInt(buttons.getValue(Buttons.buttonsValues.get(x)));

                if (buttons.getName(Buttons.buttonsValues.get(x)).contains("N")) {
                    x--;
                } else if (currentValue == 9) {
                    Buttons.buttonsValues.get(x).setValue("");
                    x--;
                } else {
                    numberToInput = currentValue + 1;
                    Buttons.buttonsValues.get(x).setValue("");
                    changePreviousCell = false;
                    increaseCurrentCell = false;
                }


            } else if (buttons.getName(Buttons.buttonsValues.get(x)).contains("N")) {
                x++;
            }


            int square = getButtonPosition2(Buttons.buttonsValues.get(x)).square;
            int column = getButtonPosition2(Buttons.buttonsValues.get(x)).column;
            int row = getButtonPosition2(Buttons.buttonsValues.get(x)).row;
            for (int y = 0; y < 9; y++) {


                if (buttons.getValue(Buttons.squaresList1.get(square-1).get(y)).contains(String.valueOf(numberToInput))) {
                    x--;
                    increaseCurrentCell = true;
                    break;
                } else if (buttons.getValue(Buttons.columnsList1.get(column-1).get(y)).contains(String.valueOf(numberToInput))) {
                    x--;
                    increaseCurrentCell = true;
                    break;
                } else if (buttons.getValue(Buttons.rowsList1.get(row-1).get(y)).contains(String.valueOf(numberToInput))) {
                    x--;
                    increaseCurrentCell = true;
                    break;
                }
            }
            if (!increaseCurrentCell) {
                Buttons.buttonsValues.get(x).setValue(String.valueOf(numberToInput));
                checker.displayCells();
                numberToInput = 1;
            } else {


                if (numberToInput == 9) {
                    x--;
                    changePreviousCell = true;


                } else {
                    numberToInput = numberToInput + 1;
                    increaseCurrentCell = false;
                }


            }


        }
    }


    public BacktrackingChecker getButtonPosition(Button button) {
        BacktrackingChecker checker = new BacktrackingChecker();
        String buttonName = button.getName();
        int square = Integer.parseInt(buttonName.substring(1, 2));
        int column = Integer.parseInt(buttonName.substring(4, 5));
        int row = Integer.parseInt(buttonName.substring(7, 8));
        checker.square = square;
        checker.column = column;
        checker.row = row;
        return checker;
    }

    public BacktrackingChecker getButtonPosition2(Buttons buttons){
        BacktrackingChecker checker2 = new BacktrackingChecker();
        int square = buttons.getSquare();
        int column = buttons.getColumn();
        int row = buttons.getRow();
        checker2.square = square;
        checker2.column = column;
        checker2.row = row;
        return checker2;
    }

    public void displayCells(){
        for(int x = 0; x<81; x++){
            String square = String.valueOf(Buttons.buttonsValues.get(x).getSquare());
            String column = String.valueOf(Buttons.buttonsValues.get(x).getColumn());
            String row = String.valueOf(Buttons.buttonsValues.get(x).getRow());
            String buttonLocation = "S"+square+" "+"C"+column+" "+"R"+row;
            String buttonValue = buttons.getValue(Buttons.buttonsValues.get(x));
            for(int y = 0; y<81; y++){
                if(Buttons.boardButtons.get(y).getName().contains(buttonLocation)){
                    Buttons.boardButtons.get(y).setLabel(buttonValue);
                    break;
                }
            }

        }



    }

    public void lookForButtonToIgnore(){
        }

    }


