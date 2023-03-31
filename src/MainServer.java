import JavaClasses.Json;
import JavaClasses.Server;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class MainServer {
    public static void main(String[] args) throws ParseException {
        //TODO add logic that takes and handles the post request, the request to alter/ adds a person in the JSON file
        //creates a server object from the Server class
        Server server = new Server();

        Json json = new Json();

        JSONParser parser = new JSONParser();
        JSONObject allPersons;
        String messege;

        //initiates server object
        server.init(7070);

        //will contain messege from client



        while(true){


            messege = server.getMessege();

            JSONObject request = (JSONObject)parser.parse(messege);
            String parameters = request.get("URLParameters").toString();
            String[] parametersArray = parameters.split("/");

            String method = request.get("HTTPMethod").toString();

            switch (method){

                case "POST":
                    JSONObject requestBody = (JSONObject) request.get("body");
                    JSONObject alteredPerson = (JSONObject) requestBody.get(parametersArray[1]);
                    json.alterPerson(parametersArray[1], alteredPerson);


                case "GET":
                    allPersons = json.getJsonObjFromFile(json.getJsonPath());
                    JSONObject person = json.getPerson(allPersons,parametersArray[1]);
                    person.put("ResponseCode", "200");
                    server.sendMessege(person.toJSONString());
                    break;
            }




        }


        //closes all open writers/ reader / sockets
        //server.close();
    }
}