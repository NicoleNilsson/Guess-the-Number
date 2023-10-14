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
    ScoreBoard lowScore = new ScoreBoard("Low Scores", 5);

    //constructor
    public GuessingGame(){
    }

    //metod som initierar och loopar spelet medan playagain är true
    public void startGame(){
        do { 
            createRandomNumber(1, 100);
            //för test:
            //System.out.println(randomNumber);            
            System.out.println("Gissa ett tal mellan 1 och 100.");

            while (userGuess != randomNumber){
                System.out.print("Gissning " + turn + ": ");
                tryTheNumber();
                guessTheNumber();
            }  
        } while (playagain); 
    }

    //metod som skapar ett slumpmässigt tal med angett bound och add
    private int createRandomNumber(int from, int to){
        randomNumber = new Random().nextInt(to) + from;
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
        if (lowScore.getScoreList().size() < lowScore.getMaxSize() || turn < lowScore.getScoreList().get(lowScore.getListIndex())){
            System.out.println("Vill du lägga till ditt resultat på lowscore-listan (Ja/Nej)? ");
            addToLowScore = gameScanner.nextLine();

            while ((!addToLowScore.equalsIgnoreCase("ja")) && (!addToLowScore.equalsIgnoreCase("nej"))){
                System.out.println("Vänligen ange Ja eller Nej: ");
                addToLowScore = gameScanner.nextLine();
            }    
            
            if (addToLowScore.equalsIgnoreCase("ja")){
                lowScore.scoreList(turn);
            }
        }
        menu();
    }

    //metod för att skapa meny och dess funktioner
    private void menu(){
        //skapar en lista med våra menyalternativ
        ArrayList<String> menuEntries = new ArrayList<>(Arrays.asList("Vad vill du göra?",
                                                                        "1. Spela igen",
                                                                        "2. Se Low Score-listan",
                                                                        "3. Avsluta spelet"));
        //skapar våra objekt gameMenu och lowScoreBoard av typen CoolTableFormat
        //med paramterar ArrayList<String>, horisontalChar, verticalChar, cornerChar, extendPaddingBy
        CoolTableFormat gameMenu = new CoolTableFormat (menuEntries, '-', '|', '+', 1);
        CoolTableFormat lowScoreBoard = new CoolTableFormat (lowScore.getScoreListAsString(), '-', '|', '+', 1); 

        loop: while (true) {                       
            gameMenu.formatAndPrint();
            choiceInMenu = gameScanner.nextLine();   
            switch (choiceInMenu){
                case "1":
                    turn = 1;
                    playagain = true;
                    break loop; 
                case "2":
                    lowScoreBoard.formatAndPrint();
                    //behövs för om man tidigare har angett fel alternativ
                    menuEntries.set(0, "Vad vill du göra?");
                    break;
                case "3":
                    System.out.println("Tack för den här gången!");    
                    //avslutar spelet
                    playagain = false;
                    break loop;
                default:
                    //kör om menyn med felmeddelande
                    menuEntries.set(0, "Vänligen ange ett av följande alternativ!");
            }
        }
    }
}

