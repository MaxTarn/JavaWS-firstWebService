import JavaClasses.Client;

import java.util.Scanner;


public class MainClient {
    public static void main(String[] args) {
        Client client = new Client();

        client.init(6969);

        String messege;


        do {
            messege = client.getInput();
            client.sendMessege(messege);
            System.out.println(client.getMessege());
        }while(!messege.equalsIgnoreCase("quit"));

        client.close();


    }
}
