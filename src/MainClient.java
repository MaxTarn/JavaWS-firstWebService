import JavaClasses.Client;

import java.util.Scanner;

public class MainClient {
    public static void main(String[] args) {
        Client client = new Client();

        client.init(6969);

        String messege;
        Scanner scan = new Scanner(System.in);

        do {
            messege = scan.nextLine();
            client.sendMessege(messege);
        }while(messege.equalsIgnoreCase("quit"));

        client.close();


    }
}
