import JavaClasses.Server;


public class MainServer {
    public static void main(String[] args) {

        //creates a server object from the Server class
        Server server = new Server();

        //initiates server object
        server.init(6969);

        //will contain messege from client
        String messege;
        do {
            messege = server.getMessege();
        } while (!messege.equalsIgnoreCase("quit") );


        //closes all open writers/ reader / sockets
        server.close();
    }
}