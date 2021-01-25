public class BacktrackingChecker {

    //back tracking main method, checking if current board is solvable
    public void checkIfSolvable(ButtonsTemplateCreator creator) {
        boolean increaseCurrentCell = false;
        boolean changePreviousCell = false;
        boolean skipButton = false;
        int numberToInput = 1;
        int currentValue;

        for (int x = 0; x < 81; x++) {

            if (changePreviousCell) {
                currentValue = Integer.parseInt(creator.getButtonsTemplateList().get(x).getValue());
                //if button is not editable, skip it going back
                if (creator.getButtonsTemplateList().get(x).getName().contains("N")) {
                    x--;
                    skipButton = true;
                    //if you cant increase value anymore, empty cell, and go back one more cell
                } else if (currentValue == 9) {
                    creator.getButtonsTemplateList().get(x).setValue("");
                    numberToInput = Integer.parseInt(creator.getButtonsTemplateList().get(x-1).getValue());
                    x--;
                    skipButton = false;
                    //empty current cell, and try again with value increased by 1
                } else {
                    numberToInput = currentValue + 1;
                    creator.getButtonsTemplateList().get(x).setValue("");
                    changePreviousCell = false;
                    increaseCurrentCell = false;
                    skipButton = false;
                }
                //if button is not editable, skip it going forward
            } else if (creator.getButtonsTemplateList().get(x).getName().contains("N")) {
                skipButton = true;
            }

            //if value is not allowed, start increase current cell process
            if (!isNumberAllowed(x, numberToInput, creator) && !skipButton) {
                x--;
                increaseCurrentCell = true;
            }
            //if value is allowed, entry value, jump to next cell, and reset number to input to 1
            if (!increaseCurrentCell && !skipButton) {
                creator.getButtonsTemplateList().get(x).setValue(String.valueOf(numberToInput));
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

    //checking if value is allowed in current cell
    public boolean isNumberAllowed(int buttonIndex, int value, ButtonsTemplateCreator creator) {
        boolean numberAllowed = true;


        for (int x = 0; x < 81; x++) {
            int square = creator.getButtonsTemplateList().get(buttonIndex).getSquare();
            int column = creator.getButtonsTemplateList().get(buttonIndex).getColumn();
            int row = creator.getButtonsTemplateList().get(buttonIndex).getRow();
            if (creator.getButtonsTemplateList().get(x).getSquare() == square) {
                if (creator.getButtonsTemplateList().get(x).getValue().equals(String.valueOf(value))) {
                    numberAllowed = false;
                }
            } else if (creator.getButtonsTemplateList().get(x).getColumn() == column) {
                if (creator.getButtonsTemplateList().get(x).getValue().equals(String.valueOf(value))) {
                    numberAllowed = false;
                }
            } else if (creator.getButtonsTemplateList().get(x).getRow() == row) {
                if (creator.getButtonsTemplateList().get(x).getValue().equals(String.valueOf(value))) {
                    numberAllowed = false;
                }
            }
        }
        return numberAllowed;
    }
}


