package game;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        try{
            GuessTheGame game = new GuessTheGame();
            game.start();
            game.end();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}