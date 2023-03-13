package game;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class GuessTheGameTest {

    private Settings setting;
    private Settings setting2;
    private Settings setting3;
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() throws Exception {
        setting = new Settings("src/test/resources/easy.json");
        setting2 = new Settings ("src/test/resources/easyError.json");
        setting3 = new Settings("src/test/resources/easyPunishment.json");
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    void tearDown(){
        System.setOut(standardOut);
    }

    @Test
    void testConstructor() throws Exception {
        GuessTheGame gtg = new GuessTheGame(setting);
        assertEquals(15, gtg.getRounds());
        assertFalse(gtg.isPunishDoubleInput());
        Scanner scanner = new Scanner(new File("src/test/resources/testWords.txt"));
        boolean flag = false;
        while(scanner.hasNextLine()){
            String w = scanner.nextLine();
            if(w.contains(gtg.getRandomWord().getWord())){
                flag = true;
            }
        }
        assertTrue(flag);
        assertEquals(7, gtg.getRandomWord().getWord().length());
    }

    @Test
    void testConstructor2(){
        assertThrows(FileNotFoundException.class, ()-> new GuessTheGame(setting2));
    }

    @Test
    void testStart(){
    }

    @Test
    void testShowWord() throws Exception {
        GuessTheGame gtg = new GuessTheGame(setting);
        gtg.showWord();
        assertEquals("You have 15 chances left.\n- - - - - - - \n", outputStreamCaptor.toString());

    }

    @Test
    void testGetInputShouldReturnTrue() throws Exception {
        String input = "t\ne\ns\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        GuessTheGame gtg = new GuessTheGame(setting);
        assertTrue(gtg.getInput());
    }

    @Test
    void testGetInputShouldReturnTrue2() throws Exception {
        String input = "#";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        GuessTheGame gtg = new GuessTheGame(setting);
        assertTrue(gtg.getInput());
    }

    @Test
    void testGetInputShouldReturnFalse() throws Exception {
        String input = "hk";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        GuessTheGame gtg = new GuessTheGame(setting);
        assertFalse(gtg.getInput());
    }

    @Test
    void testGetInputShouldReturnFalse2() throws Exception {
        String input = "\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        GuessTheGame gtg = new GuessTheGame(setting);
        assertFalse(gtg.getInput());
    }

    @Test
    void testCheckInputCorrectLetter() throws Exception {
        String input = "t\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        GuessTheGame gtg = new GuessTheGame(setting);
        gtg.getInput();
        outputStreamCaptor.reset();
        gtg.checkInput();
        assertFalse(gtg.isPunishDoubleInput());
        assertEquals(15, gtg.getRounds());
        assertEquals("You already tried:  \n", outputStreamCaptor.toString());
    }

    @Test
    void testCheckInputIncorrectLetter() throws Exception {
        String input = "p\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        GuessTheGame gtg = new GuessTheGame(setting);
        gtg.getInput();
        outputStreamCaptor.reset();
        gtg.checkInput();
        assertFalse(gtg.isPunishDoubleInput());
        assertEquals(14, gtg.getRounds());
        assertEquals("You already tried:  p \n", outputStreamCaptor.toString());
    }

    @Test
    void testCheckInputIncorrectLetterPunishment() throws Exception {
        String input = "p\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        GuessTheGame gtg = new GuessTheGame(setting3);
        gtg.getInput();
        outputStreamCaptor.reset();
        gtg.checkInput();
        assertTrue(gtg.isPunishDoubleInput());
        assertEquals(0, gtg.getRounds());
        assertEquals("Game OVER!\nThe word was: Testing\nYou already tried:  p \n", outputStreamCaptor.toString());
    }

    @Test
    void testCheckInputCorrectLetters() throws Exception {
        String input = "t\ne\ns\ni\nn\ng\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        GuessTheGame gtg = new GuessTheGame(setting3);
        for(int i = 0; i < 6; i++){
            gtg.getInput();
            gtg.checkInput();
        }
        outputStreamCaptor.reset();
        gtg.checkInput();
        assertTrue(gtg.isPunishDoubleInput());
        assertEquals(1, gtg.getRounds());
        assertEquals("Congrats, you won!\nThe word is: T e s t i n g \nYou already tried:  \n", outputStreamCaptor.toString());
    }

    @Test
    void testCheckInputNotLetter() throws Exception {
        GuessTheGame gtg = new GuessTheGame(setting);
        assertThrows(NoSuchElementException.class, gtg::checkInput);
    }

    @Test
    void end() {
    }
}