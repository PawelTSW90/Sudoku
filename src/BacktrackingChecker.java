public class BacktrackingChecker {
    Buttons buttons = new Buttons();

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
                    checker.displayCells();
                    numberToInput = Integer.parseInt(buttons.getValue(Buttons.buttonsValues.get(x-1)));
                    x--;
                } else {
                    numberToInput = currentValue + 1;
                    Buttons.buttonsValues.get(x).setValue("");
                    checker.displayCells();
                    changePreviousCell = false;
                    increaseCurrentCell = false;
                }

            } else if (buttons.getName(Buttons.buttonsValues.get(x)).contains("N")) {
                x++;
            }

            if (!checker.isNumberAllowed(x, numberToInput)) {
                x--;
                increaseCurrentCell = true;
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



    public void displayCells() {
        for (int x = 0; x < 81; x++) {
            String square = String.valueOf(Buttons.buttonsValues.get(x).getSquare());
            String column = String.valueOf(Buttons.buttonsValues.get(x).getColumn());
            String row = String.valueOf(Buttons.buttonsValues.get(x).getRow());
            String buttonLocation = "S" + square + " " + "C" + column + " " + "R" + row;
            String buttonValue = buttons.getValue(Buttons.buttonsValues.get(x));
            for (int y = 0; y < 81; y++) {
                if (Buttons.boardButtons.get(y).getName().contains(buttonLocation)) {
                    Buttons.boardButtons.get(y).setLabel(buttonValue);
                    break;
                }
            }
        }
    }

    public boolean isNumberAllowed(int buttonIndex, int value) {
        boolean numberAllowed = true;


        for (int x = 0; x < 81; x++) {
            int square = Buttons.buttonsValues.get(buttonIndex).getSquare();
            int column = Buttons.buttonsValues.get(buttonIndex).getColumn();
            int row = Buttons.buttonsValues.get(buttonIndex).getRow();
            if (Buttons.buttonsValues.get(x).getSquare() == square) {
                if (buttons.getValue(Buttons.buttonsValues.get(x)).equals(String.valueOf(value))) {
                    numberAllowed = false;
                }
            } else if (Buttons.buttonsValues.get(x).getColumn() == column) {
                if (buttons.getValue(Buttons.buttonsValues.get(x)).equals(String.valueOf(value))) {
                    numberAllowed = false;
                }
            } else if (Buttons.buttonsValues.get(x).getRow() == row) {
                if (buttons.getValue(Buttons.buttonsValues.get(x)).equals(String.valueOf(value))) {
                    numberAllowed = false;
                }
            }
        }
        return numberAllowed;
    }
}


