package game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GuessTheGame {
    private boolean play = true;
    private Words randomWord;
    private final Scanner scanner = new Scanner((System.in));
    private int rounds;
    private boolean punishDoubleInput;
    private File file;
    private char lastRound;
    private final StringBuilder triedGuesses = new StringBuilder().append(" ");

    public GuessTheGame(Settings setting) throws NullPointerException, FileNotFoundException, Exception {
        rounds = setting.getRounds();
        punishDoubleInput = setting.getPunishDoubleInput();
        file = setting.getFile();
        randomWord = new Words(file);
    }

    public void start() throws FileNotFoundException {
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

    void showWord() throws FileNotFoundException {
        if(randomWord == null){
            throw new FileNotFoundException();
        }else {
            System.out.println(rounds == 1 ? "You have " + rounds + " chance left." : "You have " + rounds + " chances left.");
            System.out.println(randomWord);
        }
    }

    boolean getInput(){
        System.out.println("Enter a letter to guess the word: ");
        String userGuess = scanner.nextLine();
        if(userGuess.length()==1){
            lastRound = userGuess.toLowerCase().charAt(0);
            return true;
        } else {
            System.out.println("\'" + userGuess + "\'" + " is not a valid input. Please enter a letter or a digit.");
            return false;
        }
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
            if( punishDoubleInput){
                rounds--;
            }else{
                if(triedGuesses.toString().indexOf(lastRound) == -1){
                    rounds--;
                }
            }
            if(!triedGuesses.isEmpty() && triedGuesses.toString().indexOf(lastRound) == -1) {
                triedGuesses.append(lastRound + " ");
            }
            if(rounds == 0){
                System.out.println("Game OVER!");
                play = false;
            }
        }
        System.out.println("You already tried: " + triedGuesses.toString());
    }

    public void end() {
        scanner.close();
    }
}
