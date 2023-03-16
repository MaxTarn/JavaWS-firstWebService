import JavaClasses.Server;


public class MainServer {
    public static void main(String[] args) {

        //creates a server object from the Server class
        Server server = new Server();

        //initializes the server socket
        server.initSockets(6969);

        //opens two-way communication between server and client
        server.initCommunication();

        //will contain messege from client
        String messege;
        do {
            messege = server.getMessege();
        } while (!messege.equalsIgnoreCase("quit") );


        //closes all open writers/ reader / sockets
        server.close();
    }
}