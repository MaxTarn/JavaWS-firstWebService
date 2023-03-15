import JavaClasses.Server;
public class Main {
    public static void main(String[] args) {
        Server server = new Server();

        server.initSockets(6969);
        server.initCommunication();

        String messege = null;
        do {
            messege = server.getMessege();

        } while (!messege.equalsIgnoreCase("quit"));

        server.close();
    }
}