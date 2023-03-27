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
        JSONObject allPersons = json.getJsonObjFromFile(json.getJsonPath());
        String messege;

        //initiates server object
        server.init(6969);

        //will contain messege from client



        while(true){
            messege = server.getMessege();
            JSONObject request = (JSONObject)parser.parse(messege);
            String parameters = request.get("URLParameters").toString();
            String[] parametersArray = parameters.split("/");

            switch (parametersArray[0]){

                case "person":
                    JSONObject person = json.getPerson(allPersons,parametersArray[1]);
                    person.put("ResponseCode", "200");
                    server.sendMessege(person.toJSONString());
                    break;

                default:
                    System.out.println("invalid");
                    break;
            }
            server.sendMessege("gdgdfgdfgd");

            server.sendMessege("sf");




        }


        //closes all open writers/ reader / sockets
        //server.close();
    }
}