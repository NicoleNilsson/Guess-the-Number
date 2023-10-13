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

    //metod som skapar padding
    public int createPadding (){
        int padding = (longestEntry + 2) / 2;

        return padding;
    }

    //metod som gör en sträng centrerad mellan två vertikala karaktärer med hjälp av angedd padding
    public String centerString (String nonCenteredString, int padding){

        if (nonCenteredString.length() % 2 == 0){
            String centeredString = String.format("%" + (padding - (nonCenteredString.length()/2)) + "s%s%" + ((padding+1) - (nonCenteredString.length()/2)) + "s", "", nonCenteredString, "");
        return verticalChar + centeredString + verticalChar;
        }

        String centeredString = String.format("%" + (padding - (nonCenteredString.length()/2)) + "s%s%" + (padding - (nonCenteredString.length()/2)) + "s", "", nonCenteredString, "");
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
