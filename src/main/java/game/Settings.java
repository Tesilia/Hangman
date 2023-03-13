package game;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Settings {

    private int rounds;
    private File file;
    private boolean punishDoubleInput;

    public Settings(String filename) throws Exception {
        File file = new File((filename));
        if (!file.exists()) throw new FileNotFoundException("File not found.");
        if(file.length()==0) throw new Exception("File is empty.");
        readFromJson(file);
    }

    private void readFromJson(File file){
        JSONParser jsonParser = new JSONParser();
        try(FileReader reader = new FileReader(file)){
            Object obj = jsonParser.parse(reader);

            JSONArray settings = (JSONArray) obj;

            settings.forEach(setting -> {
                try {
                    parseSettingObject((JSONObject) setting);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void parseSettingObject(JSONObject setting) throws Exception {
        try {
            // get setting object within list
            JSONObject settingObject = (JSONObject) setting.get("setting");

            // get setting-attribute round count
            rounds = Math.toIntExact((long) settingObject.get("rounds"));

            // get setting-attribute filename
            String filename = (String) settingObject.get("file");
            setFile(new File(filename));

            // get setting-attribute punishment for double input
            punishDoubleInput = (boolean) settingObject.get("punishment");
        }catch(Exception e){
            throw new Exception("Json-File is not correctly formatted.");
        }
    }

    int getRounds(){
        return rounds;
    }

    File getFile(){
        return file;
    }

    private void setFile(File file){
        this.file = file;
    }

    boolean getPunishDoubleInput(){
        return punishDoubleInput;
    }

}
