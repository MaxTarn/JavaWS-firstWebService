package JavaClasses;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;

public class Json {
    private final String jsonPath = "src/JSON/allPersons.json";

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

    boolean isJsonPerson(JSONObject person){
       String firstName= person.get("firstName").toString();
       String lastName = person.get("lastName").toString();
       String age = person.get("age").toString();
       String species = person.get("species").toString();
       String gender = person.get("gender").toString();

       JSONObject features = (JSONObject) person.get("Features");
       String hairColor = features.get("hairColor").toString();
       String eyeColor = features.get("eyeColor").toString();
       String nose = features.get("nose").toString();

       if (firstName != null &&
               lastName != null &&
               age != null &&
               species != null &&
               gender != null &&
               hairColor != null &&
               eyeColor != null &&
               nose != null ){

          return true;

       }else{
          return false;
       }


    }



    //reads the json file and changes
    public String alterPerson(String personId, String valueName, String value){
       JSONObject allPersons = getJsonObjFromFile(jsonPath);


       int length = Integer.parseInt(allPersons.get("length").toString());
       int personIdINT = Integer.parseInt(personId);


       //exits method when personId is out of bounds
       if (length < personIdINT || personIdINT <= 0){
          return "invalid personId";
       }

       JSONObject personToAlter = getPerson(allPersons, personId);

       personToAlter.replace(valueName, value);

       allPersons.replace(personId, personToAlter);

       File file = new File(getJsonPath());
       FileWriter writer;
       try {
          writer= new FileWriter(file);
          writer.write(allPersons.toString());
          writer.flush();
          writer.close();
       }catch (Exception ex){
          System.out.println(ex.getMessage());
          return "could not write to file";
       }
       return null;
    }
    public void alterPerson(String personId, JSONObject alteredPerson){
       JSONObject allPersons = getJsonObjFromFile(jsonPath);

       int length = Integer.parseInt(allPersons.get("length").toString());
       int personIdINT = Integer.parseInt(personId);

       if (length < personIdINT || personIdINT <= 0){
          return;
       }

       allPersons.replace(personId, alteredPerson);

       File file = new File(getJsonPath());
       FileWriter writer;
       try {
          writer= new FileWriter(file);
          writer.write(allPersons.toString());
          writer.flush();
          writer.close();
       }catch (Exception ex){
          System.out.println(ex.getMessage());
       }

    }

    public void addPerson(JSONObject personToAdd){
       if (!isJsonPerson(personToAdd)){
          System.out.println("The attempt to save the person failed because the person added did not fit the criteria.");
       }

       JSONObject allPersons = getJsonObjFromFile(getJsonPath());
       int length = Integer.parseInt(allPersons.get("length").toString());
       length += 1;

       allPersons.put(length, personToAdd);
       allPersons.replace("length", length);



       File file;
       FileWriter writer;
       try {
          file = new File(getJsonPath());
          writer = new FileWriter(file);
          writer.write(allPersons.toJSONString());
          writer.flush();
          writer.close();
       }catch (Exception ex){
          System.out.println("Failed to save the added person");
       }

    }

}
