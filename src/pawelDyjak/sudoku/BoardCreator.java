package pawelDyjak.sudoku;


public class BoardCreator {
    private final ButtonsTemplateCreator buttonsTemplateCreator;

    public BoardCreator(ButtonsTemplateCreator buttonsTemplateCreator){
        this.buttonsTemplateCreator = buttonsTemplateCreator;
    }


    //back tracking main method, checking if current board is solvable
    public boolean checkBoard() {
        boolean increaseCurrentCell = false;
        boolean changePreviousCell = false;
        boolean skipButton = false;
        int numberToInput = 1;
        int currentValue;
        boolean boardSolvable = true;
        try {

            for (int x = 0; x < 81; x++) {

                if (changePreviousCell) {
                    currentValue = Integer.parseInt(buttonsTemplateCreator.getBoardButtonsTemplateList().get(x).getValue());
                    //if button is not editable, skip it by going back
                    if (buttonsTemplateCreator.getBoardButtonsTemplateList().get(x).getButton().getName().contains("N")) {
                        x = x - 2;
                        skipButton = true;
                        //if you cant increase value anymore, empty cell, and go back one more cell
                    } else if (currentValue == 9) {
                        buttonsTemplateCreator.getBoardButtonsTemplateList().get(x).setValue("");
                        numberToInput = Integer.parseInt(buttonsTemplateCreator.getBoardButtonsTemplateList().get(x - 1).getValue());
                        x--;
                        skipButton = false;
                        //empty current cell, and try again with value increased by 1
                    } else {
                        numberToInput = currentValue + 1;
                        buttonsTemplateCreator.getBoardButtonsTemplateList().get(x).setValue("");
                        changePreviousCell = false;
                        increaseCurrentCell = false;
                        skipButton = false;
                    }

                    //if button is not editable, skip it going forward
                } else if (buttonsTemplateCreator.getBoardButtonsTemplateList().get(x).getButton().getName().contains("N")) {
                    skipButton = true;
                }

                //if value is not allowed, start increase current cell process
                if (!skipButton && !isNumberAllowed(x, numberToInput)) {
                    x--;
                    increaseCurrentCell = true;
                }
                //if value is allowed, entry value, jump to next cell, and reset number to input to 1
                if (!increaseCurrentCell && !skipButton) {
                    buttonsTemplateCreator.getBoardButtonsTemplateList().get(x).setValue(String.valueOf(numberToInput));
                    numberToInput = 1;
                    if (isBoardCompleted()) {
                        break;
                    }
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
            boardSolvable = false;

        }
        return boardSolvable;
    }


    public boolean multipleSolvingChecker() {
        boolean goBackward = true;
        boolean multipleSolving = false;
        boolean goToPreviousButton;

        try {
            for (int x = 80; x < 81; x++) {
                goToPreviousButton = false;


                //if button is not editable, skip it by going back
                if (buttonsTemplateCreator.getBoardButtonsTemplateList().get(x).getButton().getName().contains("N")) {
                    if (goBackward) {
                        goToPreviousButton = true;

                    }

                }
                //if button has no value, try to enter new one starting by 1
                else if (buttonsTemplateCreator.getBoardButtonsTemplateList().get(x).getValue().equals("")) {
                    for (int y = 1; y < 10; y++) {
                        //if new value is allowed, entry value,
                        if (isNumberAllowed(x, y)) {
                            buttonsTemplateCreator.getBoardButtonsTemplateList().get(x).setValue(String.valueOf(y));

                            //if board is completed, start everything from beginning
                            if (isBoardCompleted()) {
                                multipleSolving = true;
                                break;
                                //if not, continue with next button
                            } else {
                                goBackward = false;
                            }
                            break;

                        }
                        //if none of the values are allowed, leave button empty and go to previous button
                        if (y == 9) {
                            goToPreviousButton = true;
                            goBackward = true;
                            break;
                        }
                    }
                }

                //if button value is different than 9, clear it, increase its value by one and check if it's allowed here

                else if (Integer.parseInt(buttonsTemplateCreator.getBoardButtonsTemplateList().get(x).getValue()) != 9) {
                    for (int y = Integer.parseInt(buttonsTemplateCreator.getBoardButtonsTemplateList().get(x).getValue()) + 1; y < 10; y++) {
                        buttonsTemplateCreator.getBoardButtonsTemplateList().get(x).setValue("");

                        //if new value is allowed, entry value,
                        if (isNumberAllowed(x, y)) {
                            buttonsTemplateCreator.getBoardButtonsTemplateList().get(x).setValue(String.valueOf(y));

                            //if board is completed, start everything from beginning
                            if (isBoardCompleted()) {
                                multipleSolving = true;
                                break;
                                //if not, continue with next button
                            } else {
                                goBackward = false;

                            }
                            break;

                        }
                        //if none of the values are allowed, leave button empty and go to previous button
                        if (y == 9) {
                            goToPreviousButton = true;
                            goBackward = true;
                            break;
                        }

                    }
                }
                //if button value equals 9, clear it and go to previous button
                else {
                    buttonsTemplateCreator.getBoardButtonsTemplateList().get(x).setValue("");
                    goToPreviousButton = true;
                    goBackward = true;

                }
                if (goToPreviousButton) {
                    x = x - 2;
                }
            }
        } catch (IndexOutOfBoundsException ignored) {

        }

        return multipleSolving;

    }

    public boolean isBoardCompleted() {


        for (int x = 0; x < 81; x++) {
            if (buttonsTemplateCreator.getBoardButtonsTemplateList().get(x).getValue().equals("")) {
                return false;

            }
        }
        return true;

    }


    public void clearBoard(ButtonsTemplateCreator creator) {
        for (int x = 0; x < 81; x++) {
            creator.getBoardButtonsTemplateList().get(x).setValue("");
            creator.getBoardButtonsTemplateList().get(x).getButton().setLabel("");
            creator.getBoardButtonsTemplateList().get(x).getButton().setName("");
        }

    }


    //checking if value is allowed in current cell
    public boolean isNumberAllowed(int buttonIndex, int value) {
        boolean numberAllowed = true;


        for (int x = 0; x < 81; x++) {
            int square = buttonsTemplateCreator.getBoardButtonsTemplateList().get(buttonIndex).getSquare();
            int column = buttonsTemplateCreator.getBoardButtonsTemplateList().get(buttonIndex).getColumn();
            int row = buttonsTemplateCreator.getBoardButtonsTemplateList().get(buttonIndex).getRow();
            if (buttonsTemplateCreator.getBoardButtonsTemplateList().get(x).getSquare() == square) {
                if (buttonsTemplateCreator.getBoardButtonsTemplateList().get(x).getValue().equals(String.valueOf(value))) {
                    numberAllowed = false;
                }
            } else if (buttonsTemplateCreator.getBoardButtonsTemplateList().get(x).getColumn() == column) {
                if (buttonsTemplateCreator.getBoardButtonsTemplateList().get(x).getValue().equals(String.valueOf(value))) {
                    numberAllowed = false;
                }
            } else if (buttonsTemplateCreator.getBoardButtonsTemplateList().get(x).getRow() == row) {
                if (buttonsTemplateCreator.getBoardButtonsTemplateList().get(x).getValue().equals(String.valueOf(value))) {
                    numberAllowed = false;
                }
            }
        }
        return numberAllowed;
    }



}


