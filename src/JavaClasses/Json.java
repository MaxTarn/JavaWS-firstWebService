package JavaClasses;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

public class Json {
    final String[] paths = {
            "src/JSON/person1.json",
            "src/JSON/person2.json",
            "src/JSON/person3.json"};


    public String getPerson1Path() {return paths[0];}

    public String getPerson2Path() {
        return paths[1];
    }

    public String getPerson3Path() {
        return paths[2];
    }

    public String[] getAllPersonsPath(){
        return paths;
    }

    public String getPersonPath(int personNumber){
        return paths[personNumber];
    }


    public boolean fileExists(String filePath){
        File path = new File(filePath);
        return path.exists();
    }

    //reads the file at the given path and returns file content as a String
    public String getStringFromFile(String filePath){

        if (!fileExists(filePath)){
            return null;
        }
        try {
            FileReader path = new FileReader(filePath);
            BufferedReader reader = new BufferedReader(path);
            StringBuilder content  = new StringBuilder();
            String temp;

            do {
                temp = reader.readLine();
                if(temp != null) content.append(temp);

            }while(temp != null);


            return content.toString().replaceAll("\\s", "");
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

    public JSONObject getJsonFromFile(String filePath){
        return convertToJson(getStringFromFile(filePath));
    }
}
