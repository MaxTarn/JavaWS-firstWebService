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


    public static void main(String[] args) {


        client.init(6969);







        while(true){
            System.out.println("Do you wish to send a Request to the server?");
            String answer = client.getInput();

            if(answer.equalsIgnoreCase("yes") || answer.equalsIgnoreCase("y")){
                sendNewRequest();

                JSONObject response = getResponse();

            }


        }

        //client.close();


    }
}
