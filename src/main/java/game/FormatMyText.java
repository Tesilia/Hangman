package game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.PrintWriter;

public class FormatMyText {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("src/main/resources/marvel.txt");
        Scanner scanner = new Scanner(file);
        String text = "";
        while(scanner.hasNextLine()){
            text = scanner.nextLine();
        }
        scanner.close();

        List<String> words = Arrays.asList(text.split(", "));

        PrintWriter out = new PrintWriter("src/main/resources/marvelWords.txt");
        for(String elem : words){
            out.println(elem);
        }
        out.close();
    }
}
