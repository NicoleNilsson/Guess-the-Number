import java.util.ArrayList;

public class CoolTableFormat {

    //instansvariabler
    private char horisontalChar;
    private char cornerChar;
    private char verticalChar;
    private int longestEntry;
    private int extendPaddingBy;
    private int padding;
    private int rightPadding;
    private int leftPadding;
    private ArrayList<String> stringEntries = new ArrayList<>();

    //constructor
    public CoolTableFormat(ArrayList<String> stringEntries, char horisontalChar, char verticalChar, char cornerChar, int extendPaddingBy){
        this.horisontalChar = horisontalChar;
        this.cornerChar = cornerChar;
        this.verticalChar = verticalChar;
        this.extendPaddingBy = extendPaddingBy;
        this.stringEntries = stringEntries;
    }

    //metod som tar fram längden på den längsta strängen i en lista
    public int findLongestEntry (){
        //nollställer longestEntry
        longestEntry = 0;
        for (String entry : stringEntries) {
            if (longestEntry < entry.length()){
                longestEntry = entry.length();
            }
        }
        return longestEntry;
    }

    //metod som skapar höger padding från längsta strängen
    public int createRightPadding (String entry){
        findLongestEntry();
        padding = (longestEntry + extendPaddingBy) / 2;
        rightPadding = padding - (entry.length()/2);

        if (longestEntry % 2 == 0 && extendPaddingBy % 2 != 0){
            rightPadding++;
        }
        return rightPadding;
    }
    //metod som skapar vänster padding från längsta strängen
    public int createLeftPadding (String entry){
        findLongestEntry();
        padding = (longestEntry + extendPaddingBy) / 2;
        leftPadding = padding - (entry.length()/2);

        //om noncenteredstring skulle innehålla jämnt antal karaktärer, lägg till 1 till vänster padding
        // se*
        if (entry.length() % 2 == 0){
            leftPadding++;
        }

        return leftPadding;
    }

    //metod som gör en sträng centrerad mellan två vertikala karaktärer med hjälp av angedd padding
    public String centerString (String nonCenteredString){
        //i sammanhanget: "s" är "", och "%s" är noncenteredstring
        //"%5s" skulle lägga till 5 mellanslag innan s skrivs ut
        String centeredString = String.format("%" + (rightPadding) + "s" + "%s" + "%" + (leftPadding) + "s", "", nonCenteredString, "");
        return verticalChar + centeredString + verticalChar;
    }

    //metod som skapar en horisontell skiljelinje med hjälp av angedd karaktär och längd
    public String createDevitionLine (){
        //skapar devisionline av lika många tecken som longestentry + den tillagda paddingen
        String devitionLine = "";
        for (int i = 0; i < longestEntry + extendPaddingBy; i++){
            devitionLine = devitionLine + horisontalChar;
        }
        //lägger till en extra karaktär om longestentry är jämnt, eller den tillagda paddingen är ojämn
        //se *
        if (longestEntry % 2 == 0 || extendPaddingBy % 2 != 0){
            devitionLine = devitionLine + horisontalChar; 
        }

        return cornerChar +  devitionLine + cornerChar;
    }
}



/* 

*
Exempel:
longestEntry = 14  
padding = 14 / 2 = 7
leftPadding = 7 - 7 = 0
rightPadding = 7 - 7 = 0

nonCenteredString = 5
padding = 5 / 2 = 2,5 (avrundas nedåt till 2)
leftPadding = 7 - 2 = 5
rightPadding = 7 - 2 = 5

5 + 5 + 5 = 15 (dvs 1 mer än longestEntry)

därför vill vi lägga till 1 på ena padding (egentligen 0.5 på varje padding men det går ju inte)

samma problem uppstår när vi skapar devisionline
och om extendPaddingBy är ett ojämnt tal

*/