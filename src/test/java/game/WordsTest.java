package game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class WordsTest {
    private Words testWord;

    @BeforeEach
    void setUp() throws Exception {
        testWord = new Words(new File("src/test/resources/testWords.txt"));
    }
    @Test
    void testConstructor() throws Exception {
        Words word = new Words(new File("src/test/resources/testWords.txt"));
        String selectedWord = word.getWord();
        Scanner scanner = new Scanner(new File("src/test/resources/testWords.txt"));
        boolean flag = false;
        while(scanner.hasNextLine()){
            String w = scanner.nextLine();
            if(w.contains(selectedWord)){
                flag = true;
            }
        }
        assertTrue(flag);
        assertEquals(7, selectedWord.length() );
    }

    @Test
    void testConstructorThrowsFileNotFoundException(){
        String filename = "";
        File file = new File(filename);
        assertThrows(FileNotFoundException.class, () -> new Words(file));
    }

    @Test
    void testConstructorThrowsNullPointerException(){
        assertThrows(NullPointerException.class, () -> new Words(null));
    }

    @Test
    void testToString() {
        assertEquals("- - - - - - - " , testWord.toString());
        testWord.guess('t');
        assertEquals("T - - t - - - " , testWord.toString());
    }

    @Test
    void testGuessLowerLetter() {
        assertTrue(testWord.guess('t'));
    }

    @Test
    void testGuessUpperLetter() {
        assertTrue(testWord.guess('T'));
    }

    @Test
    void testGuessWrongLetter() {
        assertFalse(testWord.guess('p'));
    }

    @Test
    void testIsGuessedRight() {
        testWord.guess('t');
        testWord.guess('e');
        testWord.guess('s');
        testWord.guess('i');
        testWord.guess('n');
        testWord.guess('g');
        assertTrue(testWord.isGuessedRight());
    }

    @Test
    void testIsGuessedFalse() {
        testWord.guess('t');
        testWord.guess('i');
        testWord.guess('n');
        assertFalse(testWord.isGuessedRight());
    }

    @Test
    void testGetWord(){
        assertEquals("Testing", testWord.getWord());
    }
}