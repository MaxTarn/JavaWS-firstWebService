package JavaClasses;

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
        Object obj;
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

    public JSONObject getPerson(JSONObject jsonObj, String personId){
        int length;
        int personIdInt;
        try {
            length = Integer.parseInt(jsonObj.get("length").toString());
            personIdInt = Integer.parseInt(personId);
        }catch (Exception ex){
            return null;
        }


        if(personIdInt <= length && personIdInt >= 1){
            return (JSONObject) jsonObj.get(personId);
        }else{
            return null;
        }
    }

    public void display(JSONObject person){
        JSONObject features = (JSONObject) person.get("Features");

        String firstName= person.get("firstName").toString();
        String lastName = person.get("lastName").toString();
        String age = person.get("age").toString();
        String species = person.get("species").toString();
        String gender = person.get("gender").toString();
        String hairColor = features.get("hairColor").toString();
        String eyeColor = features.get("eyeColor").toString();
        String nose = features.get("nose").toString();


        System.out.println("Name: " + firstName + " " + lastName);
        System.out.println("Age: " + age);
        System.out.println("Species: " + species);
        System.out.println("Gender: " + gender);
        System.out.println("Hair color: " + hairColor);
        System.out.println("Eye color: " + eyeColor);
        System.out.println("Nose: " + nose);

    }

}
