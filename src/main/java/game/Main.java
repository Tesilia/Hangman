package game;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        GuessTheGame game = new GuessTheGame();
        game.start();
        game.end();
    }
}