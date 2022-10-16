package game;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class GuessTheGame {
    private boolean play = true;
    private Words randomWord = new Words();
    private Scanner scanner = new Scanner((System.in));
    private int rounds = 10;
    private char lastRound;
    public GuessTheGame() throws FileNotFoundException {
    }

    public void start(){
        do{
            showWord();
            boolean isValid = getInput();
            if (isValid) {
                checkInput();
            }else{
                System.out.println("Enter a letter to guess the word: ");
            }
        }while(play);
    }

    void showWord(){
        System.out.println("You have " + rounds + " left.");
        System.out.println(randomWord);
    }

    boolean getInput(){
        System.out.println("Enter a letter to guess the word: ");
        String userGuess = scanner.nextLine();
        if(!userGuess.isEmpty() && userGuess.length()==1){
            lastRound = userGuess.toLowerCase().charAt(0);
            return true;
        }
        return false;
    }
    void checkInput(){
        boolean isGuessedRight = randomWord.guess(lastRound);

        if (isGuessedRight) {
            if(randomWord.isGuessedRight()){
                System.out.println("Congrats, you won!");
                System.out.println("The word is: " + randomWord);
                play = false;
            }
        }else{
            rounds--;
            if(rounds == 0){
                System.out.println("Game OVER!");
                play = false;
            }
        }

    }

    public void end() {
        scanner.close();
    }
}
