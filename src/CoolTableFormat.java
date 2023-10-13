import java.util.ArrayList;

public class CoolTableFormat {

    //instansvariabler
    private char horisontalChar;
    private char cornerChar;
    private char verticalChar;
    private int longestEntry;

    //constructor
    public CoolTableFormat(ArrayList<String> stringEntries, char horisontalChar, char verticalChar, char cornerChar){
        this.horisontalChar = horisontalChar;
        this.cornerChar = cornerChar;
        this.verticalChar = verticalChar;
    }
    
    //metod som hittar längsta strängen i en lista
    public int findLongestEntry(ArrayList<String> stringEntries){
        for (String entry : stringEntries) {

            if (longestEntry < entry.length()){
                longestEntry = entry.length();
            }
        }
        
        return longestEntry;
    }

    //skapar höger padding från längsta strängen
    public int createRightPadding (String nonCenteredString){
        int padding = (longestEntry + 2) / 2;
        int rightPadding = padding - (nonCenteredString.length()/2);

        return rightPadding;
    }
    //metod som skapar vänster padding från längsta
    public int createLeftPadding (String nonCenteredString){
        int padding = (longestEntry + 2) / 2;
        int leftPadding = padding - (nonCenteredString.length()/2);

        return leftPadding;
    }

    //metod som gör en sträng centrerad mellan två vertikala karaktärer med hjälp av angedd padding
    public String centerString (String nonCenteredString, int rightPadding, int leftPadding){

        if (nonCenteredString.length() % 2 == 0){
            //i sammanhanget: "s" är "", och "%s" är noncenteredstring
            //"%5s" skulle lägga till 5 mellanslag innan s skrivs ut

            //om noncenteredstring skulle innehålla ojämnt antal karaktärer, lägg till 1 till vänster padding
            String centeredString = String.format("%" + (rightPadding) + "s" + "%s" + "%" + (leftPadding+1) + "s", "", nonCenteredString, "");
            return verticalChar + centeredString + verticalChar;
        }

        String centeredString = String.format("%" + (rightPadding) + "s" + "%s" + "%" + (leftPadding) + "s", "", nonCenteredString, "");

        return verticalChar + centeredString + verticalChar;
    }

    //metod som skapar en horisontell skiljelinje med hjälp av angedd karaktär och längd
    public String createDevitionLine (int amountOfHorisontalChar){
        String devitionLine = "";

        for (int i = 0; i < amountOfHorisontalChar; i++){
            devitionLine = devitionLine + horisontalChar;
        }

        if (amountOfHorisontalChar % 2 == 0){
            devitionLine = devitionLine + horisontalChar; 
        }

        return cornerChar +  devitionLine + cornerChar;
    }
}
