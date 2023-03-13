package game;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner((System.in));
    public static void main(String[] args) {
        try{
            String filename = "";
            System.out.println("Choose your level: \n(1) easy\n(2) medium\n(3) hard\n Enter number now:");
            do {
                String level = scanner.nextLine();
                switch (level) {
                    case "1" -> {
                        filename = "src/main/resources/easy.json";
                        System.out.println("You choose the easy Strategy!");
                    }
                    case "2" -> {
                        System.out.println("You choose the medium Strategy!");
                        filename = "src/main/resources/medium.json";
                    }
                    case "3" -> {
                        System.out.println("You choose the hard Strategy!");
                        filename = "src/main/resources/hard.json";
                    }
                    default ->
                            System.out.println("Please enter 1 for easy strategy, 2 for medium strategy or 3 for hard strategy.");
                }
            }while(filename.equals(""));

            Settings settings = new Settings(filename);
            GuessTheGame game = new GuessTheGame(settings);

            game.start();
            game.end();
        }catch( Exception e){
            e.printStackTrace();
        }
    }
}