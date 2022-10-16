package game;

import com.github.dhiraj072.randomwordgenerator.RandomWordGenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Words {
    private String selectedWord;
    private List<String> words;
    private Random random = new Random();
    private char[] letters;
    File file = new File("src/main/resources/marvelWords.txt");

    public Words() throws FileNotFoundException {
        words = new ArrayList<>();
        Scanner scanner = new Scanner(file);
        while(scanner.hasNextLine()){
            words.add(scanner.nextLine().toString());
        }
        System.out.println("Größe der Liste: " + words.size());
        selectedWord = words.get(random.nextInt(words.size()));
        scanner.close();
        letters = new char[selectedWord.length()];

    }
    public String toString(){

        StringBuilder text = new StringBuilder();
        String[] letters2 = selectedWord.split("");
        for(int i = 0; i < letters.length; i++){
            if(letters[i] == '\u0000'){
                text.append(letters2[i].charAt(0) == ' ' ? ' ' : '-');
            }else{
                text.append(letters[i]);
            }
            text.append(' ');
        }
        return text.toString();
    }

    public boolean guess(char letter) {
        boolean guessedRight = false;
        for( int i = 0; i < selectedWord.length(); i++){
            if(letter == selectedWord.charAt(i)){
                letters[i] = letter;
                guessedRight = true;
            }if (Character.toUpperCase(letter) == selectedWord.charAt(i) ){
                letters[i] = Character.toUpperCase(letter);
                guessedRight = true;
            }
        }
        return guessedRight;
    }

    public boolean isGuessedRight(){
        for(char letter : letters){
            if(letter == '\u0000'){
                return false;
            }
        }
        return true;
    }
    public String getSelectedWord() {
        return selectedWord;
    }
}
