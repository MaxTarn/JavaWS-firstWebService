import JavaClasses.Json;
import JavaClasses.Request;
import org.json.simple.JSONObject;

public class Main {

    public static void display(JSONObject person){
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
    public static void main(String[] args) {
        Json json = new Json();
        Request request = new Request();

        JSONObject obj = json.getJsonObjFromFile(json.getJsonPath());
        display(json.getPerson(obj, "1"));
    }
}
