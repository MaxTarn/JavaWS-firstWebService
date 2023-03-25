import JavaClasses.Json;
import org.json.simple.JSONObject;

public class Main {
    public static void main(String[] args) {
        Json json = new Json();
        String contentOfFile = json.getStringFromFile(json.getPerson1Path());

        System.out.println(contentOfFile);



        JSONObject jsonObj = json.convertToJson(contentOfFile);


    }
}
