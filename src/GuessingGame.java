import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class GuessingGame {
    
    private int randomNumber;
    private int turn = 1;
    private int userGuess;
    private String addToLowScore;
    private int choiceInMenu;
    private Scanner gameScanner = new Scanner(System.in);

    ScoreEntry entry = new ScoreEntry();

    public GuessingGame(){
    }

    public void startGame(){
        createRandomNumber1To100();
        System.out.println(randomNumber);
        //bara så jag kan se det slumpade numret
        
        System.out.println("Gissa ett tal mellan 1 och 100.");

        while (userGuess != randomNumber){
            System.out.print("Gissning " + turn + ": ");
            tryTheNumber();
            guessTheNumber();
        }
    }

    //metod som skapar ett slumpmässigt tal mellan 1-100
    private int createRandomNumber1To100(){
        randomNumber = new Random().nextInt(100) +1;
        return randomNumber;
    }
    
    //metod som kontrollerar om user input (gissningen) är en int
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

    //metod som kontrollerar gissningen
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
            //turn = 1;
        }
    }

    //metod där antal gissningar kontrolleras 
    //och du får välja att lägga till på lowscore-listan om du klassar in
    private void addToLowScore(){

        if (entry.getLowScore().size() < 5 || turn < entry.getLowScore().get(4)){
            
            do{
            System.out.println("Vill du lägga till ditt resultat på lowscore-listan (Ja/Nej)? ");
            //rensa buffert
            addToLowScore = gameScanner.nextLine();
            } while ((!addToLowScore.equalsIgnoreCase("ja")) && (!addToLowScore.equalsIgnoreCase("nej")));
    
            if (addToLowScore.equalsIgnoreCase("ja")){
                entry.lowScore(turn);
                menu();;

            } else if (addToLowScore.equalsIgnoreCase("nej")){
                menu();;
            }
            
        } else {
            menu();;
        }
    }

    //metod för meny
    private void menu(){
        System.out.println("Vad vill du göra?");
        
        System.out.println("1. Spela igen");
        System.out.println("2. Se lowscore-listan");
        System.out.println("3. Avsluta spelet");

        choiceInMenu = gameScanner.nextInt();

        //lägg till åtgärd om man anger annat än 1 2 3 

            if (choiceInMenu == 1){
                turn = 1;
                startGame();
            } else if (choiceInMenu == 2){
                System.out.println("Lowscores: ");
                for (int sc : entry.getLowScore()) {
                    System.out.println(sc);
                } menu();
            } else if (choiceInMenu == 3){
                System.out.println("Tack för den här gången!");
            }

    }
}
