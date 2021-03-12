import java.awt.*;

public class ErrorChecker {




    public void checkIfThereAreErrors(ButtonsTemplateCreator creator, SudokuGenerator generator, SoundClass sound){

        for(int x = 0; x<81;x++){

            String buttonValue = creator.getBoardButtonsTemplateList().get(x).getButton().getLabel();
            if(!buttonValue.equals(String.valueOf(generator.boardSolution[x])) && !buttonValue.equals("")){
                creator.getBoardButtonsTemplateList().get(x).getButton().setForeground(Color.red);
                sound.error(creator.board);
            } else{
                creator.getBoardButtonsTemplateList().get(x).getButton().setForeground(Color.black);
            }
        }

    }

    public void restoreButtonsColors(ButtonsTemplateCreator creator){
        for(int x = 0; x<81; x++){
            creator.getBoardButtonsTemplateList().get(x).getButton().setForeground(Color.black);
        }
    }
}
