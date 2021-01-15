import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Buttons {
    static List<Button> keypadButtons = new ArrayList<>();
    static List<Button> boardButtons = new ArrayList<>();
    static List<Button> column1 = new ArrayList<>();
    static List<Button> column2 = new ArrayList<>();
    static List<Button> column3 = new ArrayList<>();
    static List<Button> column4 = new ArrayList<>();
    static List<Button> column5 = new ArrayList<>();
    static List<Button> column6 = new ArrayList<>();
    static List<Button> column7 = new ArrayList<>();
    static List<Button> column8 = new ArrayList<>();
    static List<Button> column9 = new ArrayList<>();
    static List<Button> row1 = new ArrayList<>();
    static List<Button> row2 = new ArrayList<>();
    static List<Button> row3 = new ArrayList<>();
    static List<Button> row4 = new ArrayList<>();
    static List<Button> row5 = new ArrayList<>();
    static List<Button> row6 = new ArrayList<>();
    static List<Button> row7 = new ArrayList<>();
    static List<Button> row8 = new ArrayList<>();
    static List<Button> row9 = new ArrayList<>();
        //method is adding name signature to all buttons/ depends on board position (C for column, R for row)
    public static void refactorButtonList() {

        for (int x = 0; x < 25; x=x+3) {
            boardButtons.get(x).setName(boardButtons.get(x).getName()+" C1");
            column1.add(boardButtons.get(x));
        }

        for(int x = 1; x < 26; x=x+3){
            boardButtons.get(x).setName(boardButtons.get(x).getName()+" C2");
            column2.add(boardButtons.get(x));
        }
        for(int x = 2; x < 27; x=x+3){
            boardButtons.get(x).setName(boardButtons.get(x).getName()+" C3");
            column3.add(boardButtons.get(x));
        }
        for(int x = 27; x < 52; x=x+3){
            boardButtons.get(x).setName(boardButtons.get(x).getName()+" C4");
            column4.add(boardButtons.get(x));
        }
        for(int x = 28; x < 53; x=x+3){
            boardButtons.get(x).setName(boardButtons.get(x).getName()+" C5");
            column5.add(boardButtons.get(x));
        }
        for(int x = 29; x < 54; x=x+3){
            boardButtons.get(x).setName(boardButtons.get(x).getName()+" C6");
            column6.add(boardButtons.get(x));
        }
        for(int x = 54; x < 79; x=x+3){
            boardButtons.get(x).setName(boardButtons.get(x).getName()+" C7");
            column7.add(boardButtons.get(x));
        }
        for(int x = 55; x < 80; x=x+3){
            boardButtons.get(x).setName(boardButtons.get(x).getName()+" C8");
            column8.add(boardButtons.get(x));
        }
        for(int x = 56; x < 81; x=x+3){
            boardButtons.get(x).setName(boardButtons.get(x).getName()+" C9");
            column9.add(boardButtons.get(x));
        }

        for(int y = 0; y<57; y++){
            boardButtons.get(y).setName(boardButtons.get(y).getName()+" R1");
            row1.add(boardButtons.get(y));
            if(y == 2 || y ==29){
                y = y+24;
            } else if(y == 56){
                break;
            }

        }
        for(int y = 3; y<60; y++){
            boardButtons.get(y).setName(boardButtons.get(y).getName()+" R2");
            row2.add(boardButtons.get(y));
            if(y == 5 || y ==32){
                y = y+24;
            } else if(y == 59){
                break;
            }

        }
        for(int y = 6; y<63; y++){
            boardButtons.get(y).setName(boardButtons.get(y).getName()+" R3");
            row3.add(boardButtons.get(y));
            if(y == 8 || y ==35){
                y = y+24;
            } else if(y == 62){
                break;
            }

        }
        for(int y = 9; y<66; y++){
            boardButtons.get(y).setName(boardButtons.get(y).getName()+" R4");
            row4.add(boardButtons.get(y));
            if(y == 11 || y ==38){
                y = y+24;
            } else if(y == 65){
                break;
            }

        }
        for(int y = 12; y<69; y++){
            boardButtons.get(y).setName(boardButtons.get(y).getName()+" R5");
            row5.add(boardButtons.get(y));
            if(y == 14 || y ==41){
                y = y+24;
            } else if(y == 68){
                break;
            }

        }
        for(int y = 15; y<72; y++){
            boardButtons.get(y).setName(boardButtons.get(y).getName()+" R6");
            row6.add(boardButtons.get(y));
            if(y == 17 || y ==44){
                y = y+24;
            } else if(y == 71){
                break;
            }

        }
        for(int y = 18; y<75; y++){
            boardButtons.get(y).setName(boardButtons.get(y).getName()+" R7");
            row7.add(boardButtons.get(y));
            if(y == 20 || y ==47){
                y = y+24;
            } else if(y == 74){
                break;
            }

        }
        for(int y = 21; y<78; y++){
            boardButtons.get(y).setName(boardButtons.get(y).getName()+" R8");
            row8.add(boardButtons.get(y));
            if(y == 23 || y ==50){
                y = y+24;
            } else if(y == 77){
                break;
            }

        }
        for(int y = 24; y<81; y++){
            boardButtons.get(y).setName(boardButtons.get(y).getName()+" R9");
            row9.add(boardButtons.get(y));
            if(y == 26 || y ==53){
                y = y+24;
            } else if(y == 80){
                break;
            }

        }



    }
}
