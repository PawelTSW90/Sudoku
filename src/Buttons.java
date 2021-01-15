import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Buttons {
    static List<Button> keypadButtons = new ArrayList<>();
    static List<Button> boardButtons = new ArrayList<>();
        //method is adding name signature to all buttons/ depends on board position (C for column, R for row)
    public static void refactorButtonList() {

        for (int x = 0; x < 25; x=x+3) {
            boardButtons.get(x).setName(boardButtons.get(x).getName()+" C1");
        }

        for(int x = 1; x < 26; x=x+3){
            boardButtons.get(x).setName(boardButtons.get(x).getName()+" C2");

        }
        for(int x = 2; x < 27; x=x+3){
            boardButtons.get(x).setName(boardButtons.get(x).getName()+" C3");

        }
        for(int x = 27; x < 52; x=x+3){
            boardButtons.get(x).setName(boardButtons.get(x).getName()+" C4");

        }
        for(int x = 28; x < 53; x=x+3){
            boardButtons.get(x).setName(boardButtons.get(x).getName()+" C5");

        }
        for(int x = 29; x < 54; x=x+3){
            boardButtons.get(x).setName(boardButtons.get(x).getName()+" C6");

        }
        for(int x = 54; x < 79; x=x+3){
            boardButtons.get(x).setName(boardButtons.get(x).getName()+" C7");

        }
        for(int x = 55; x < 80; x=x+3){
            boardButtons.get(x).setName(boardButtons.get(x).getName()+" C8");

        }
        for(int x = 56; x < 81; x=x+3){
            boardButtons.get(x).setName(boardButtons.get(x).getName()+" C9");

        }

        for(int y = 0; y<57; y++){
            boardButtons.get(y).setName(boardButtons.get(y).getName()+" R1");
            if(y == 2 || y ==29){
                y = y+24;
            } else if(y == 56){
                break;
            }

        }
        for(int y = 3; y<60; y++){
            boardButtons.get(y).setName(boardButtons.get(y).getName()+" R2");
            if(y == 5 || y ==32){
                y = y+24;
            } else if(y == 59){
                break;
            }

        }
        for(int y = 6; y<63; y++){
            boardButtons.get(y).setName(boardButtons.get(y).getName()+" R3");
            if(y == 8 || y ==35){
                y = y+24;
            } else if(y == 62){
                break;
            }

        }
        for(int y = 9; y<66; y++){
            boardButtons.get(y).setName(boardButtons.get(y).getName()+" R4");
            if(y == 11 || y ==38){
                y = y+24;
            } else if(y == 65){
                break;
            }

        }
        for(int y = 12; y<69; y++){
            boardButtons.get(y).setName(boardButtons.get(y).getName()+" R5");
            if(y == 14 || y ==41){
                y = y+24;
            } else if(y == 68){
                break;
            }

        }
        for(int y = 15; y<72; y++){
            boardButtons.get(y).setName(boardButtons.get(y).getName()+" R6");
            if(y == 17 || y ==44){
                y = y+24;
            } else if(y == 71){
                break;
            }

        }
        for(int y = 18; y<75; y++){
            boardButtons.get(y).setName(boardButtons.get(y).getName()+" R7");
            if(y == 20 || y ==47){
                y = y+24;
            } else if(y == 74){
                break;
            }

        }
        for(int y = 21; y<78; y++){
            boardButtons.get(y).setName(boardButtons.get(y).getName()+" R8");
            if(y == 23 || y ==50){
                y = y+24;
            } else if(y == 77){
                break;
            }

        }
        for(int y = 24; y<81; y++){
            boardButtons.get(y).setName(boardButtons.get(y).getName()+" R9");
            if(y == 26 || y ==53){
                y = y+24;
            } else if(y == 80){
                break;
            }

        }




    }
}
