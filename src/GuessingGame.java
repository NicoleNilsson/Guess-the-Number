import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class GuessingGame {
    
    //instansvariabler
    private int randomNumber;
    private int turn = 1;
    private int userGuess;
    private String addToLowScore;
    private String choiceInMenu;
    private boolean playagain;
    private Scanner gameScanner = new Scanner(System.in);

    //skapar objektet lowScore av typen ScoreBoard och gör listLength till 5
    ScoreBoard lowScore = new ScoreBoard(5);

    //constructor
    public GuessingGame(){
    }

    //metod som initierar och loopar spelet medan playagain är true
    public void startGame(){
        do { 
            createRandomNumber(100, 1);
            System.out.println(randomNumber);
            //bara så jag kan se det slumpade numret
            
            System.out.println("Gissa ett tal mellan 1 och 100.");

            while (userGuess != randomNumber){
                System.out.print("Gissning " + turn + ": ");
                tryTheNumber();
                guessTheNumber();
            }  
        } while (playagain); 
    }

    //metod som skapar ett slumpmässigt tal med angett bound och add
    private int createRandomNumber(int bound, int add){
        randomNumber = new Random().nextInt(bound) + add;
        return randomNumber;
    }
    
    //metod som tar in och testar att userGuess är en int + felhantering
    private int tryTheNumber(){
        while (true){
            try {
            userGuess = gameScanner.nextInt();
            return userGuess;
            } catch (InputMismatchException e) {
                System.out.println("Du kan bara skriva ett tal med siffror. Försök igen!");
                System.out.print("Gissning " + turn + ": ");
                gameScanner.nextLine();
            }
        } 
    }

    //metod som jämför userGuess mot randomNumber
    private void guessTheNumber(){
        if (userGuess < randomNumber){
            System.out.println("Talet är större.");
            turn++;            
        } else if (userGuess > randomNumber){
            System.out.println("Talet är mindre.");
            turn++;
        } else if (userGuess == randomNumber) {
            System.out.println("Rätt! Du gissade rätt på " + turn + " försök.");
            gameScanner.nextLine();
            addToLowScore();
        }
    }

    //metod för att lägga till antal gissningar i lowScore + felhantering
    private void addToLowScore(){
        if (lowScore.getScoreList().size() < lowScore.getListLength() || turn < lowScore.getScoreList().get(lowScore.getListIndex())){
            System.out.println("Vill du lägga till ditt resultat på lowscore-listan (Ja/Nej)? ");
            addToLowScore = gameScanner.nextLine();
            while ((!addToLowScore.equalsIgnoreCase("ja")) && (!addToLowScore.equalsIgnoreCase("nej"))){
                System.out.println("Vänligen ange Ja eller Nej: ");
                addToLowScore = gameScanner.nextLine();
            }    
            if (addToLowScore.equalsIgnoreCase("ja")){
                lowScore.scoreList(turn);
                menu();
            } else if (addToLowScore.equalsIgnoreCase("nej")){
                menu();
            }          
        } else {
            menu();
        }
    }

    //metod för att skapa meny och dess funktioner
    private void menu(){
        //skapar en lista med våra menyalternativ
        ArrayList<String> menuEntries = new ArrayList<>(Arrays.asList("Vad vill du göra?",
                                                                        "1. Spela igen",
                                                                        "2. Se Low Score-listan",
                                                                        "3. Avsluta spelet"));
        
        //skapar objekt gameMenu av typen CoolTableFormat
        //med paramterar ArrayList<String>, horisontalChar, verticalChar, cornerChar, extendPaddingBy(antal extra karaktärer i padding)
        CoolTableFormat gameMenu = new CoolTableFormat (menuEntries, '-', '|', '+', 2);

        while (true) {
            //gameMenu.findLongestEntry();
           
            for (String entry : menuEntries) {
                //skapar padding utifrån längden på längsta strängen i vår lista
                gameMenu.createRightPadding(entry);
                gameMenu.createLeftPadding(entry);

                //formaterar och skriver ut skiljelinjer
                //skriver ut den formatterade menyn
                System.out.println(gameMenu.createDevitionLine());
                System.out.println(gameMenu.centerString(entry));
            }
            System.out.println(gameMenu.createDevitionLine());
            
            //tar in val i menyn och utvärderar input
            choiceInMenu = gameScanner.nextLine();   
            if (choiceInMenu.equals("1")){
                turn = 1;
                playagain = true;
                break;                
            } else if (choiceInMenu.equals("2")){ 
                //skriver ut lowscore och kör om menyn
                printScoreBoard();
                //behövs om man tidigare har angett fel alternativ
                menuEntries.set(0, "Vad vill du göra?");

            } else if (choiceInMenu.equals("3")){
                System.out.println("Tack för den här gången!");
                //avslutar spelet
                playagain = false;
                break;
            } else {
                //kör om menyn med felmeddelande
                menuEntries.set(0, "Vänligen ange ett av följande alternativ!");                
            }
        }
    }

    private void printScoreBoard (){
        //anropar metoden som konverterar vår scorelista från int till String
        lowScore.scoreListAsString();
        
        //skapar objektet lowScoreBoard av typen CoolTableFormat, som formaterar vår lowscore-lista 
        //med paramterar ArrayList<String>, horisontalChar, verticalChar, cornerChar, extendPaddingBy(antal extra karaktärer i padding)
        CoolTableFormat lowScoreBoard = new CoolTableFormat (lowScore.getScoreListAsString(), '-', '|', '+', 2);  
        
        //anropar metod som tar fram längden på den längsta strängen i en lista
        //lowScoreBoard.findLongestEntry();
        for (String score : lowScore.getScoreListAsString()) {
            //skapar padding utifrån längden på den längsta strängen i vår lista
            lowScoreBoard.createRightPadding(score);
            lowScoreBoard.createLeftPadding(score);

            //formaterar och skriver ut skiljelinjer
            //skriver ut den formatterade listan
            System.out.println(lowScoreBoard.createDevitionLine());
            System.out.println(lowScoreBoard.centerString(score));
        }
        System.out.println(lowScoreBoard.createDevitionLine());
    }
}

