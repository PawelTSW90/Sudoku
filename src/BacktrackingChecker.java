public class BacktrackingChecker {
    ButtonsTemplateCreator management;

    public BacktrackingChecker(ButtonsTemplateCreator management){
        this.management = management;
    }

    //back tracking main method, checking if current board is solvable
    public void checkIfSolvable() {
        boolean increaseCurrentCell = false;
        boolean changePreviousCell = false;
        boolean skipButton = false;
        int numberToInput = 1;
        int currentValue;

        for (int x = 0; x < 81; x++) {

            if (changePreviousCell) {
                currentValue = Integer.parseInt(management.getValue(ButtonsTemplateCreator.buttonsValues.get(x)));
                //if button is not editable, skip it going back
                if (management.getName(ButtonsTemplateCreator.buttonsValues.get(x)).contains("N")) {
                    x--;
                    skipButton = true;
                    //if you cant increase value anymore, empty cell, and go back one more cell
                } else if (currentValue == 9) {
                    ButtonsTemplateCreator.buttonsValues.get(x).setValue("");
                    displayCells();
                    numberToInput = Integer.parseInt(management.getValue(ButtonsTemplateCreator.buttonsValues.get(x - 1)));
                    x--;
                    skipButton = false;
                    //empty current cell, and try again with value increased by 1
                } else {
                    numberToInput = currentValue + 1;
                    ButtonsTemplateCreator.buttonsValues.get(x).setValue("");
                    displayCells();
                    changePreviousCell = false;
                    increaseCurrentCell = false;
                    skipButton = false;
                }
                //if button is not editable, skip it going forward
            } else if (management.getName(ButtonsTemplateCreator.buttonsValues.get(x)).contains("N")) {
                skipButton = true;
            }

            //if value is not allowed, start increase current cell process
            if (!isNumberAllowed(x, numberToInput) && !skipButton) {
                x--;
                increaseCurrentCell = true;
            }
            //if value is allowed, entry value, jump to next cell, and reset number to input to 1
            if (!increaseCurrentCell && !skipButton) {
                ButtonsTemplateCreator.buttonsValues.get(x).setValue(String.valueOf(numberToInput));
                displayCells();
                numberToInput = 1;
                //if value is not allowed,
            } else if (skipButton) {
                numberToInput = 1;
                skipButton = false;
            } else {
                //and you cant increase number anymore, start change previous cell process
                if (numberToInput == 9) {
                    x--;
                    changePreviousCell = true;
                } else {
                    //increase value by 1 and try again
                    numberToInput = numberToInput + 1;
                    increaseCurrentCell = false;
                }
            }


        }
    }

    //passing values to buttons, and displaying them
    public void displayCells() {
        for (int x = 0; x < 81; x++) {
            String square = String.valueOf(ButtonsTemplateCreator.buttonsValues.get(x).getSquare());
            String column = String.valueOf(ButtonsTemplateCreator.buttonsValues.get(x).getColumn());
            String row = String.valueOf(ButtonsTemplateCreator.buttonsValues.get(x).getRow());
            String buttonLocation = "S" + square + " " + "C" + column + " " + "R" + row;
            String buttonValue = management.getValue(ButtonsTemplateCreator.buttonsValues.get(x));
            for (int y = 0; y < 81; y++) {
                if (management.boardButtons.get(y).getName().contains(buttonLocation)) {
                    management.boardButtons.get(y).setLabel(buttonValue);
                    break;
                }
            }
        }
    }

    //checking if value is allowed in current cell
    public boolean isNumberAllowed(int buttonIndex, int value) {
        boolean numberAllowed = true;


        for (int x = 0; x < 81; x++) {
            int square = ButtonsTemplateCreator.buttonsValues.get(buttonIndex).getSquare();
            int column = ButtonsTemplateCreator.buttonsValues.get(buttonIndex).getColumn();
            int row = ButtonsTemplateCreator.buttonsValues.get(buttonIndex).getRow();
            if (ButtonsTemplateCreator.buttonsValues.get(x).getSquare() == square) {
                if (management.getValue(ButtonsTemplateCreator.buttonsValues.get(x)).equals(String.valueOf(value))) {
                    numberAllowed = false;
                }
            } else if (ButtonsTemplateCreator.buttonsValues.get(x).getColumn() == column) {
                if (management.getValue(ButtonsTemplateCreator.buttonsValues.get(x)).equals(String.valueOf(value))) {
                    numberAllowed = false;
                }
            } else if (ButtonsTemplateCreator.buttonsValues.get(x).getRow() == row) {
                if (management.getValue(ButtonsTemplateCreator.buttonsValues.get(x)).equals(String.valueOf(value))) {
                    numberAllowed = false;
                }
            }
        }
        return numberAllowed;
    }
}


