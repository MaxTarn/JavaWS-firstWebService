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
                json.display(response);

            }
            System.out.println("- - - - - - - - - - - - - - - - - ");
            System.out.println();

        }

        //client.close();


    }
}
