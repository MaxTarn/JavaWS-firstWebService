import JavaClasses.Json;
import JavaClasses.MakeRequest;
import org.json.simple.JSONObject;

public class Main {
    public static void main(String[] args) {
        Json json = new Json();
        MakeRequest makeRequest = new MakeRequest();
        String contentOfFile = json.getStringFromFile(json.getPerson1Path());

        makeRequest.makeRequest();
    }
}
