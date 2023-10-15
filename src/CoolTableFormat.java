import java.util.ArrayList;

public class CoolTableFormat {

    //instansvariabler
    private char horisontalChar;
    private char cornerChar;
    private char verticalChar;
    private int longestEntry;
    private int extendPaddingBy;
    private int rightPadding;
    private int leftPadding;
    private ArrayList<String> stringEntries = new ArrayList<>();

    //constructor med 5 parametrar
    public CoolTableFormat(ArrayList<String> stringEntries, char horisontalChar, char verticalChar, char cornerChar, int extendPaddingBy){
        this.stringEntries = stringEntries;
        this.horisontalChar = horisontalChar;
        this.verticalChar = verticalChar;
        this.cornerChar = cornerChar;
        this.extendPaddingBy = extendPaddingBy;
    }

    //metod som formaterar och skriver ut en ArrayList
    public void formatAndPrint() {
        for (String element : stringEntries) {
            //skapar padding utifrån längden på den längsta strängen i vår lista
            createRightPadding(element);
            createLeftPadding(element);

            //formaterar och skriver ut skiljelinjer
            //skriver ut den formatterade menyn
            System.out.println(createDevitionLine());
            System.out.println(centerString(element));
        }
        System.out.println(createDevitionLine());
    }

    //metod som skapar höger padding från längden på strängen
    public int createRightPadding (String entry){
        findLongestEntry();
        rightPadding =  (longestEntry / 2) + extendPaddingBy - (entry.length()/2);
        //om entry skulle innehålla jämnt antal karaktärer, lägg till 1 till höger padding
        // se*
        if (entry.length() % 2 == 0){
            rightPadding++;
        }
        return rightPadding;
    }
    
    //metod som skapar vänster padding från längden på strängen
    public int createLeftPadding (String entry){
        findLongestEntry();
        leftPadding =  (longestEntry / 2) + extendPaddingBy - (entry.length()/2);
        return leftPadding;
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

    //metod som skapar en horisontell skiljelinje med hjälp av angedd karaktär och längd
    public String createDevitionLine (){
        //skapar devisionLine av lika många tecken som longestEntry + den tillagda paddingen
        String devitionLine = "";
        for (int i = 0; i < longestEntry + (extendPaddingBy * 2) ; i++){
            devitionLine = devitionLine + horisontalChar;
        }

        //lägger till en extra karaktär om longestEntry är jämnt
        //se *
        if (longestEntry % 2 == 0){
            devitionLine = devitionLine + horisontalChar; 
        }
        return cornerChar +  devitionLine + cornerChar;
    }

    //metod som gör en sträng centrerad mellan två vertikala karaktärer med hjälp av angedd padding
    public String centerString (String nonCenteredString){
        //i sammanhanget: "s" är "", och "%s" är noncenteredstring
        //"%5s" skulle lägga till 5 mellanslag innan s skrivs ut
        String centeredString = String.format("%" + (rightPadding) + "s" + "%s" + "%" + (leftPadding) + "s", "", nonCenteredString, "");
        return verticalChar + centeredString + verticalChar;
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
man vill inte ta bort en från den ojämna strängen för då riskerar paddingen bli för kort för longestEntry

samma problem uppstår när vi skapar devisionline

*/