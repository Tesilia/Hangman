package game;


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

    public Words(File file) throws NullPointerException, FileNotFoundException {
        if(file == null || !file.exists()){
            throw new NullPointerException();
        }else {
            Scanner scanner = new Scanner(file);
            words = new ArrayList<>();
            while (scanner.hasNextLine()) {
                words.add(scanner.nextLine().toString());
            }
            selectedWord = words.get(random.nextInt(words.size()));
            scanner.close();
            letters = new char[selectedWord.length()];
        }
    }
    public String toString() throws NullPointerException{
        if(selectedWord == null) {
            throw new NullPointerException();
        }else{
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
    }

    public boolean guess(char letter) throws NullPointerException {
        if( selectedWord == null) {
            throw new NullPointerException();
        }else{
            boolean guessedRight = false;
            for (int i = 0; i < selectedWord.length(); i++) {
                if (letter == selectedWord.charAt(i)) {
                    letters[i] = letter;
                    guessedRight = true;
                }
                if (Character.toUpperCase(letter) == selectedWord.charAt(i)) {
                    letters[i] = Character.toUpperCase(letter);
                    guessedRight = true;
                }
            }
            return guessedRight;
        }
    }

    public boolean isGuessedRight() throws NullPointerException{
        if( selectedWord == null){
            throw new NullPointerException();
        }else {
            String[] letters2 = selectedWord.split("");
            for (int i = 0; i < letters.length; i++) {
                if (letters[i] == '\u0000' && letters2[i].charAt(0) != ' ') {
                    return false;
                }
            }
            return true;
        }
    }
}
