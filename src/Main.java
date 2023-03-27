import JavaClasses.Json;
import JavaClasses.Request;
import org.json.simple.JSONObject;

public class Main {
    public static void main(String[] args) {
        Json json = new Json();
        Request request = new Request();

        JSONObject obj = json.getJsonObjFromFile(json.getJsonPath());
        json.display(json.getPerson(obj, "1"));
    }
}
