package game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class GuessTheGame {
    private boolean play = true;
    private final Words randomWord;
    private final Scanner scanner = new Scanner((System.in));
    private int rounds;
    private final boolean punishDoubleInput;
    private char lastRound;
    private final StringBuilder triedGuesses = new StringBuilder().append(" ");

    public GuessTheGame(Settings setting) throws Exception {
        rounds = setting.getRounds();
        punishDoubleInput = setting.getPunishDoubleInput();
        File file = setting.getFile();
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
        if(getRandomWord() == null){
            throw new FileNotFoundException();
        }else {
            System.out.println(getRounds() == 1 ? "You have " + getRounds() + " chance left." : "You have " + getRounds() + " chances left.");
            System.out.println(getRandomWord());
        }
    }

    boolean getInput(){
        System.out.println("Enter a letter to guess the word: ");
        String userGuess = scanner.nextLine();
        if(userGuess.length()==1){
            lastRound = userGuess.charAt(0);
            return true;
        } else {
            System.out.println("'" + userGuess + "'" + " is not a valid input. Please enter a letter or a digit.");
            return false;
        }
    }
    void checkInput(){
        if(lastRound == '\u0000') throw new NoSuchElementException();
        else {
            boolean isGuessedRight = getRandomWord().guess(lastRound);

            if (isGuessedRight) {
                if (getRandomWord().isGuessedRight()) {
                    System.out.println("Congrats, you won!");
                    System.out.println("The word is: " + getRandomWord());
                    play = false;
                }
            } else {
                if (isPunishDoubleInput()) {
                    setRounds(getRounds() - 1);
                } else {
                    if (triedGuesses.toString().indexOf(lastRound) == -1) {
                        setRounds(getRounds() - 1);
                    }
                }
                if (!triedGuesses.isEmpty() && triedGuesses.toString().indexOf(lastRound) == -1) {
                    triedGuesses.append(lastRound).append(" ");
                }
                if (getRounds() == 0) {
                    System.out.println("Game OVER!");
                    // remove the comment slashes if you want the word to be shown when the game is over.
                    System.out.println("The word was: " + getRandomWord().getWord());
                    play = false;
                }
            }
            System.out.println("You already tried: " + triedGuesses);
        }
    }

    public void end() {
        scanner.close();
    }

    public int getRounds(){
        return rounds;
    }

    private void setRounds(int rounds){
        this.rounds = rounds;
    }

    public Words getRandomWord() {
        return randomWord;
    }

    public boolean isPunishDoubleInput() {
        return punishDoubleInput;
    }
}
