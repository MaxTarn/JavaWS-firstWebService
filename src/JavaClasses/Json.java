package JavaClasses;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;

public class Json {
    final String jsonPath = "src/JSON/allPersons.json";

    public String getJsonPath(){return jsonPath;}

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

    public JSONObject convertToJsonObject(String jsonString){
        JSONParser parser = new JSONParser();
        Object obj = null;
        try {
            obj = parser.parse(jsonString);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return (JSONObject) obj;

    }

    public JSONObject getJsonObjFromFile(String filePath){
        return convertToJsonObject(getStringFromFile(filePath));
    }
}
