package game;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        try{
            String easy ="src/main/java/game/easy.json";
            String medium = "src/main/java/game/medium.json";
            String hard = "src/main/java/game/hard.json";
            Settings settings = new Settings(easy);
            GuessTheGame game = new GuessTheGame(settings);

            game.start();
            game.end();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}