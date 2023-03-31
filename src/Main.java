import JavaClasses.Json;
import JavaClasses.Request;
import org.json.simple.JSONObject;

//used for testing new methods and new code snippets
public class Main {

    public static void main(String[] args) {
        Json json = new Json();
        Request requestMaker = new Request();

        JSONObject allPersons = json.getJsonObjFromFile(json.getJsonPath());
        requestMaker.makeAlterRequest(json.getPerson(allPersons, "2"), "2");




    }
}
