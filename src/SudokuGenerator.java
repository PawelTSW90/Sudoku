import java.util.Random;

public class SudokuGenerator {




    public static void generateNewBoard(){

for(int x = 0; x<Buttons.column1.size(); x++){
    Random random = new Random();
    int randomNumber = random.nextInt(10);
    Buttons.column1.get(x).setLabel(String.valueOf(randomNumber));
}
        for(int x = 0; x<Buttons.column2.size(); x++){
            Random random = new Random();
            int randomNumber = random.nextInt(10);
            Buttons.column2.get(x).setLabel(String.valueOf(randomNumber));
        }

        for(int x = 0; x<Buttons.column3.size(); x++){
            Random random = new Random();
            int randomNumber = random.nextInt(10);
            Buttons.column3.get(x).setLabel(String.valueOf(randomNumber));
        }

    }
}
