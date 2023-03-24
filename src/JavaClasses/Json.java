package JavaClasses;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

public class Json {
    final String person1Path= "src/JSON/person1.json";
    final String person3Path= "src/JSON/person3.json";
    final String person2Path= "src/JSON/person2.json";

    public String getPerson1Path() {
        return person1Path;
    }

    public String getPerson2Path() {
        return person2Path;
    }

    public String getPerson3Path() {
        return person3Path;
    }


    public boolean fileExists(String filePath){
        File path = new File(filePath);
        return path.exists();
    }

    public String getStringFromFile(String filePath){

        try {
            if(fileExists(filePath)){
                FileReader path = new FileReader(filePath);
                BufferedReader reader = new BufferedReader(path);
                String content  = null;
                String temp;

                while(  (temp = reader.readLine())  !=   null  ){
                    content += temp;
                }
                return content;

            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public JSONObject convertToJson(String jsonString){
        JSONParser parser = new JSONParser();
        Object obj = null;
        try {
            obj = parser.parse(jsonString);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return (JSONObject) obj;

    }
}
