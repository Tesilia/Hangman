package game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import static org.junit.jupiter.api.Assertions.*;

class SettingsTest {
    private File words;
    private String filenameEasy;
    private String filenameMedium;
    private String filenameHard;
    private String fileNotExisting;
    @BeforeEach
    void setUp(){
        words = new File("src/main/resources/marvelWords.txt");
        filenameEasy = "src/main/resources/easy.json";
        filenameMedium = "src/main/resources/medium.json";
        filenameHard = "src/main/resources/hard.json";
        fileNotExisting = "src/main/resources/hrd.json";
    }

    @Test
    void testConstructor1() throws Exception {
        Settings setting = new Settings(filenameEasy);
        assertEquals(words, setting.getFile());
        assertEquals(15, setting.getRounds());
        assertFalse(setting.getPunishDoubleInput());
    }

    @Test
    void testConstructor2() throws Exception {
        String input = "2";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Settings setting = new Settings(filenameMedium);
        assertEquals(words, setting.getFile());
        assertEquals(7, setting.getRounds());
        assertFalse(setting.getPunishDoubleInput());
    }

    @Test
    void testConstructor3() throws Exception {
        Settings setting = new Settings(filenameHard);
        assertEquals(words, setting.getFile());
        assertEquals(10, setting.getRounds());
        assertTrue(setting.getPunishDoubleInput());
    }

    @Test
    void testConstructorFileNotFound(){
        assertThrows(FileNotFoundException.class, () -> new Settings(fileNotExisting));
    }

   @Test
   void testGetRounds() throws Exception {
       String input = "1";
       InputStream in = new ByteArrayInputStream(input.getBytes());
       System.setIn(in);
       Settings setting = new Settings(filenameEasy);
       assertEquals(15, setting.getRounds());
   }

    @Test
    void testGetFile() throws Exception {
        String input = "1";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Settings setting = new Settings(filenameEasy);
        assertEquals(words, setting.getFile());
     }

    @Test
    void TestGetPunishDoubleInput() throws Exception {
        String input = "1";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Settings setting = new Settings(filenameEasy);
        assertFalse(setting.getPunishDoubleInput());
    }
}