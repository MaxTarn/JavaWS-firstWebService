import JavaClasses.Client;


public class MainClient {
    public static void main(String[] args) {
        Client client = new Client();

        client.init(6969);

        String messege;


        do {
            messege = client.getMessegeFromConsole();
            client.sendMessege(messege);
            System.out.println(client.getMessege());
        }while(!messege.equals("quit"));

        client.close();


    }
}
