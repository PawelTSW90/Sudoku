public class BacktrackingChecker {

    //back tracking main method, checking if current board is solvable
    public boolean checkIfSolvable(ButtonsTemplateCreator creator) {
        boolean isSolvable = true;
        boolean increaseCurrentCell = false;
        boolean changePreviousCell = false;
        boolean skipButton = false;
        int numberToInput = 1;
        int currentValue;
        setButtonsToSkip(creator);
        try {


            for (int x = 0; x < 81; x++) {


                if (changePreviousCell) {
                    currentValue = Integer.parseInt(creator.getBoardButtonsTemplateList().get(x).getValue());
                    //if button is not editable, skip it going back
                    if (creator.getBoardButtonsTemplateList().get(x).getButton().getName().contains("N")) {
                        x = x - 2;
                        skipButton = true;
                        //if you cant increase value anymore, empty cell, and go back one more cell
                    } else if (currentValue == 9) {
                        creator.getBoardButtonsTemplateList().get(x).setValue("");
                        numberToInput = Integer.parseInt(creator.getBoardButtonsTemplateList().get(x - 1).getValue());
                        x--;
                        skipButton = false;
                        //empty current cell, and try again with value increased by 1
                    } else {
                        numberToInput = currentValue + 1;
                        creator.getBoardButtonsTemplateList().get(x).setValue("");
                        displayNumbers(creator);
                        changePreviousCell = false;
                        increaseCurrentCell = false;
                        skipButton = false;
                    }
                    //if button is not editable, skip it going forward
                } else if (creator.getBoardButtonsTemplateList().get(x).getButton().getName().contains("N")) {
                    skipButton = true;
                }

                //if value is not allowed, start increase current cell process
                if (!skipButton && !isNumberAllowed(x, numberToInput, creator)) {
                    x--;
                    increaseCurrentCell = true;
                }
                //if value is allowed, entry value, jump to next cell, and reset number to input to 1
                if (!increaseCurrentCell && !skipButton) {
                    creator.getBoardButtonsTemplateList().get(x).setValue(String.valueOf(numberToInput));
                    displayNumbers(creator);
                    numberToInput = 1;
                    //if value is not allowed,
                } else if (skipButton) {
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
        } catch (IndexOutOfBoundsException e) {
            isSolvable = false;
        }
        return isSolvable;
    }

    //method is copying value from button to its template, so back tracker will skip it
    public void setButtonsToSkip(ButtonsTemplateCreator creator) {
        for (int x = 0; x < 81; x++) {
            if (creator.getBoardButtonsTemplateList().get(x).getButton().getName().contains("N")) {
                String number = creator.getBoardButtonsTemplateList().get(x).getButton().getLabel();
                creator.getBoardButtonsTemplateList().get(x).setValue(number);
            }
        }
    }


    //method is passing value from buttons template to buttons and displaying it
    public void displayNumbers(ButtonsTemplateCreator creator) {
        for (int x = 0; x < 81; x++) {
            String number = creator.getBoardButtonsTemplateList().get(x).getValue();
            creator.getBoardButtonsTemplateList().get(x).getButton().setLabel(number);
        }
    }


    //checking if value is allowed in current cell
    public boolean isNumberAllowed(int buttonIndex, int value, ButtonsTemplateCreator creator) {
        boolean numberAllowed = true;


        for (int x = 0; x < 81; x++) {
            int square = creator.getBoardButtonsTemplateList().get(buttonIndex).getSquare();
            int column = creator.getBoardButtonsTemplateList().get(buttonIndex).getColumn();
            int row = creator.getBoardButtonsTemplateList().get(buttonIndex).getRow();
            if (creator.getBoardButtonsTemplateList().get(x).getSquare() == square) {
                if (creator.getBoardButtonsTemplateList().get(x).getValue().equals(String.valueOf(value))) {
                    numberAllowed = false;
                }
            } else if (creator.getBoardButtonsTemplateList().get(x).getColumn() == column) {
                if (creator.getBoardButtonsTemplateList().get(x).getValue().equals(String.valueOf(value))) {
                    numberAllowed = false;
                }
            } else if (creator.getBoardButtonsTemplateList().get(x).getRow() == row) {
                if (creator.getBoardButtonsTemplateList().get(x).getValue().equals(String.valueOf(value))) {
                    numberAllowed = false;
                }
            }
        }
        return numberAllowed;
    }
}


