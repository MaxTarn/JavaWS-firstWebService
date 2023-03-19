package JavaClasses;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;

public class Json {
    final String person1Path= "src/JSON/person1.json";
    final String person3Path= "src/JSON/person3.json";
    final String person2Path= "src/JSON/person2.json";

    public String getPerson1Path() {
        return person1Path;
    }

    public void setPerson1Path(String person1Path) throws Exception {
        throw new Exception("You can't change the path of the JSON files");
    }


    public String getPerson2Path() {
        return person2Path;
    }

    public void setPerson2Path(String person2Path) throws Exception {
        throw new Exception("You can't change the path of the JSON files");
    }


    public String getPerson3Path() {
        return person3Path;
    }

    public void setPerson3Path(String person3Path) throws Exception {
        throw new Exception("You can't change the path of the JSON files");
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

    public void convertToJson(String jsonString){


    }
}
