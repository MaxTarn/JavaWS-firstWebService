import JavaClasses.Json;
import JavaClasses.Server;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class MainServer {

    //The server that takes in the GET and POST requests and deals accordingly with them
    public static void main(String[] args) throws ParseException {

        //creates a server object from the Server class
        Server server = new Server();

        //object that deals with json, the json file, and alterations of that json file
        Json json = new Json();

        JSONParser parser = new JSONParser();
        JSONObject allPersons;
        String messege;

        //initiates server object
        server.init(7070);

        //the loop that continually takes in request and deals with thme
        while(true){

            //the string that MainClient sends
            messege = server.getMessege();

            //converts to JSON format
            JSONObject request = (JSONObject)parser.parse(messege);
            String parameters = request.get("URLParameters").toString();

            //splits the URL so that the switch case can deal with it
            String[] parametersArray = parameters.split("/");

            //get the intended method of the request
            String method = request.get("HTTPMethod").toString();

            //gets the data from the JSON file, and if it is a POST request it alters the JSON file
            switch (method){

                //alters the JSON file
                case "POST":

                    //gets the body from the request
                    JSONObject requestBody = (JSONObject) request.get("body");

                    //gets the personID that corresponds to what person in the JSON file that will be altered
                    JSONObject alteredPerson = (JSONObject) requestBody.get(parametersArray[1]);

                    //alters the JSON file
                    json.alterPerson(parametersArray[1], alteredPerson);

                //runs when request is POST and when its GET
                //fetches the desired data from the local JSON file
                case "GET":
                    //fetches the entire local file and converts it into a JSONOject
                    allPersons = json.getJsonObjFromFile(json.getJsonPath());

                    //gets the wanted person, according to the request sent by MainClient
                    JSONObject person = json.getPerson(allPersons,parametersArray[1]);

                    //adds a response code to show that all is OK
                    person.put("ResponseCode", "200");

                    //sends the JSONObject as a String, to MainClient
                    server.sendMessege(person.toJSONString());
                    break;
            }
        }
    }
}