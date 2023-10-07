import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class GuessingGame {
    
    //initieras här för att komma åt dem i flera metoder
    private int randomNumber;
    private int turn = 1;
    private int userGuess;
    private String addToLowScore;
    private String choiceInMenu;
    private Scanner gameScanner = new Scanner(System.in);

    //borde den ligga nån annanstans?

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
        //kan man göra på ett annat sätt?/varför?
        randomNumber = new Random().nextInt(100) +1;
        //+1 för annars blir det 0-99
        return randomNumber;
    }
    
    //tar in userGuess
    //borde det göras nån annanstans och isf hur?
    //kontrollerar om userGuess är en int
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
        }
    }

    //skapar objekt av ScoreEntry
    ScoreEntry entry = new ScoreEntry();

    //metod där antal gissningar kontrolleras 
    //och du får välja att lägga till på lowscore-listan om du klassar in
    //+felhantering om ej anger ja eller nej
    private void addToLowScore(){

        if (entry.getLowScore().size() < 5 || turn < entry.getLowScore().get(4)){

            System.out.println("Vill du lägga till ditt resultat på lowscore-listan (Ja/Nej)? ");
            addToLowScore = gameScanner.nextLine();

            while ((!addToLowScore.equalsIgnoreCase("ja")) && (!addToLowScore.equalsIgnoreCase("nej"))){
                System.out.println("Vänligen ange Ja eller Nej: ");
                addToLowScore = gameScanner.nextLine();
            }
    
            if (addToLowScore.equalsIgnoreCase("ja")){
                entry.lowScore(turn);
                menu();

            } else if (addToLowScore.equalsIgnoreCase("nej")){
                menu();
            }
            
        } else {
            menu();
        }
    }

    //metod för meny
    private void menu(){
        System.out.println("Vad vill du göra?");
        
        do {
            System.out.println("1. Spela igen");
            System.out.println("2. Se Low Score-listan");
            System.out.println("3. Avsluta spelet");

            choiceInMenu = gameScanner.nextLine();

                if (choiceInMenu.equals("1")){
                    turn = 1;
                    startGame();
                    break;
                    //för att stänga pågående spel
                } else if (choiceInMenu.equals("2")){
                    System.out.println("Low Scores: ");
                    for (int score : entry.getLowScore()) {
                        System.out.println(score);
                    }
                } else if (choiceInMenu.equals("3")){
                    System.out.println("Tack för den här gången!");
                    break;
                } else {
                    System.out.println("Vänligen ange ett av följande alternativ: ");
                }

            } while (!choiceInMenu.equals("1") || !choiceInMenu.equals("3"));
            //ingen equals 2 så den fortsätter do while-loopen och skriver ut menyn igen efter lowscore
    }
}
