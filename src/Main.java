import JavaClasses.Json;
import JavaClasses.Request;
import org.json.simple.JSONObject;

//ONLY used for testing new methods and new code snippets

//IF YOU WANT TO LAUNCH THE APPLICATION:
//1. START MainServer
//2. START MainClient
//3. ANSWER QUESTIONS IN THE CONSOLE OF MainClient

public class Main {

    public static void main(String[] args) {
        Json json = new Json();
        Request requestMaker = new Request();

        JSONObject allPersons = json.getJsonObjFromFile(json.getJsonPath());
        requestMaker.makeAlterRequest(json.getPerson(allPersons, "2"), "2");




    }
}
