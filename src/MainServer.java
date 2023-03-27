import JavaClasses.Json;
import JavaClasses.Server;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class MainServer {
    public static void main(String[] args) throws ParseException {

        //creates a server object from the Server class
        Server server = new Server();

        Json json = new Json();

        JSONParser parser = new JSONParser();
        String messege;

        //initiates server object
        server.init(6969);

        //will contain messege from client
        while(true){
            messege = server.getMessege();
            JSONObject request = (JSONObject)parser.parse(messege);

        }


        //closes all open writers/ reader / sockets
        //server.close();
    }
}