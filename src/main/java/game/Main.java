package game;

public class Main {
    public static void main(String[] args) {

        String easyLevel = "src/main/java/game/easy.json";
        String mediumLevel = "src/main/java/game/medium.json";
        String hardLevel = "src/main/java/game/hard.json";
        try{
            Settings settings = new Settings(mediumLevel);
            GuessTheGame game = new GuessTheGame(settings);

            game.start();
            game.end();
        }catch( Exception e){
            e.printStackTrace();
        }
    }
}