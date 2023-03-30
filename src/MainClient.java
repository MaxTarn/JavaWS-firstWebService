import JavaClasses.Client;
import JavaClasses.Json;
import JavaClasses.Request;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class MainClient {

    static Client client = new Client();
    static Request requestMaker = new Request();
    static JSONParser parser = new JSONParser();
    static Json json = new Json();
    static String messege;


    static void sendNewRequest(){
        JSONObject request = requestMaker.makeRequest();
        String requestString = request.toJSONString();
        client.sendMessege(requestString);
    }
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

    static JSONObject getResponse(){
        System.out.println("Response loading...");
        JSONObject response = null;
        boolean goodMessege = false;
        while(!goodMessege){

            try {
                messege = client.getMessege();
                response = (JSONObject) parser.parse(messege);
                goodMessege = true;
            }catch (Exception ex){

            }
        }

        System.out.println("Response Loaded");
        return response;
    }




    public static void main(String[] args)  {

        System.out.println("Connecting to server.");


        while(!client.connectionIsGood()){
            client.init(6969);
        }
        System.out.println("Connected to server.");


        System.out.println();
        System.out.println("- - - - - - - - - - - - - - - - - ");
        while(true){
            System.out.print("Do you wish to send a Request to the server? (accepted inputs:  yes / quit)  :");
            String answer = client.getInput();
            System.out.println();


            if (answer.equalsIgnoreCase("quit")){
                break;
            }

            if(answer.equalsIgnoreCase("yes") || answer.equalsIgnoreCase("y")){
                sendNewRequest();

                JSONObject response = getResponse();
                System.out.println();
                System.out.println("Response:");
                display(response);

            }
            System.out.println("- - - - - - - - - - - - - - - - - ");
            System.out.println();

        }

        //client.close();


    }
}
