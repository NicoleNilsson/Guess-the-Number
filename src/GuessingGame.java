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
    private Scanner gameScanner = new Scanner(System.in);

    //skapar objektet lowscore av scoreboard och ger lowscorelistan längden 5
    ScoreBoard lowScore = new ScoreBoard(5);

    //constructor
    public GuessingGame(){
    }

    //metod som initierar spelet
    public void startGame(){
        createRandomNumber(100, 1);
        System.out.println(randomNumber);
        //bara så jag kan se det slumpade numret
        
        System.out.println("Gissa ett tal mellan 1 och 100.");

        while (userGuess != randomNumber){
            System.out.print("Gissning " + turn + ": ");
            tryTheNumber();
            guessTheNumber();
        }
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

    //metod för att lägga till lowscore + felhantering
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
                                                                        "2. Se Low Score-lista",
                                                                        "3. Avsluta spelet"));
        
        //skapar ett objekt av typen CoolTableFormat
        CoolTableFormat gameMenu = new CoolTableFormat (menuEntries, '-', '|', '+');

        while (true) {
            //formaterar separationslinjer
            String menuDevitionLine = gameMenu.createDevitionLine(gameMenu.findLongestEntry(menuEntries) + 2);
            
            //formaterar och skriver ut menyn
            for (String entry : menuEntries) {
                int menuRightPadding = gameMenu.createRightPadding(entry);
                int menuLeftPadding = gameMenu.createLeftPadding(entry);

                System.out.println(menuDevitionLine);
                System.out.println(gameMenu.centerString(entry, menuRightPadding, menuLeftPadding));
            }
            System.out.println(menuDevitionLine);
            
            //tar in val i menyn och utvärderar input
            choiceInMenu = gameScanner.nextLine();  
              
            if (choiceInMenu.equals("1")){
                turn = 1;
                //kan bli ett problem om spelet startas för många gånger
                startGame();
                break;
                
            } else if (choiceInMenu.equals("2")){ 
                //skriver ut lowscore och kör om menyn
                printScoreBoard();

            } else if (choiceInMenu.equals("3")){
                System.out.println("Tack för den här gången!");
                break;

            } else {
                menuEntries.set(0, "Vänligen ange ett av följande alternativ!");
                //kör om menyn med felmeddelande
            }
        }
    }

    private void printScoreBoard (){
        CoolTableFormat lowScoreBoard = new CoolTableFormat (lowScore.getScoreListAsString(), '-', '|', '+');
        
        lowScore.scoreListAsString();
        String lowScoreDevisionLine = lowScoreBoard.createDevitionLine(lowScoreBoard.findLongestEntry(lowScore.getScoreListAsString()) + 2);

        for (String score : lowScore.getScoreListAsString()) {
            int lowScoreRightPadding = lowScoreBoard.createRightPadding(score);
            int lowScoreLeftPadding = lowScoreBoard.createLeftPadding(score);

            System.out.println(lowScoreDevisionLine);
            System.out.println(lowScoreBoard.centerString(score, lowScoreRightPadding, lowScoreLeftPadding));
        }
        System.out.println(lowScoreDevisionLine);
    }
}

