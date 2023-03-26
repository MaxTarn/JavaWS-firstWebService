import JavaClasses.Client;
import JavaClasses.Request;
import org.json.simple.JSONObject;

import java.util.Scanner;


public class MainClient {
    public static void main(String[] args) {
        Client client = new Client();
        Request requestMaker = new Request();

        client.init(6969);

        String messege;


        //do {
            //messege = client.getInput();
            //client.sendMessege(messege);
            //System.out.println(client.getMessege());
        //}while(!messege.equalsIgnoreCase("quit"));

        while(true){
            JSONObject request = requestMaker.makeRequest();
            String requestString = request.toJSONString();
            client.sendMessege(requestString);

        }

        //client.close();


    }
}
