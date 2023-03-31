import JavaClasses.Client;
import JavaClasses.Json;
import JavaClasses.Request;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class MainClient {

    static Client client = new Client();
    static Request requestMaker = new Request();
    static JSONParser parser = new JSONParser();
    static String messege;
    static JSONObject request;


    //makes the get request and sends it to the server
    //returns the ID of the person
    static String makeAndSendRequest(){
        request = requestMaker.makeRequest();
        String requestString = request.toJSONString();
        client.sendMessege(requestString);
        return getUserIdFromURL(request.get("URLParameters").toString());
    }

    //returns only the ID of the URL
    static String getUserIdFromURL(String url){
        String[] temp = url.split("/");
        return temp[1];
    }

    //prints out the attributes of the JSONObject
    public static void display(JSONObject person){
        JSONObject features = (JSONObject) person.get("Features");

        String firstName= person.get("firstName").toString();
        String lastName = person.get("lastName").toString();
        String age = person.get("age").toString();
        String species = person.get("species").toString();
        String gender = person.get("gender").toString();
        String hairColor = features.get("hairColor").toString();
        String eyeColor = features.get("eyeColor").toString();
        String nose = features.get("nose").toString();


        System.out.println("1. Name: " + firstName + " " + lastName);
        System.out.println("2. Age: " + age);
        System.out.println("3. Species: " + species);
        System.out.println("4. Gender: " + gender);
        System.out.println("5. Hair color: " + hairColor);
        System.out.println("6. Eye color: " + eyeColor);
        System.out.println("7. Nose: " + nose);

    }

    //gets the response from MainServer
    static JSONObject getResponse(){
        System.out.println("Response loading...");
        JSONObject response = null;
        boolean goodMessege = false;
        while(!goodMessege){

            try {
                messege = client.getMessege();
                response = (JSONObject) parser.parse(messege);
                goodMessege = true;
            }catch (Exception ex){

            }
        }

        System.out.println("Response Loaded");
        return response;
    }




    public static void main(String[] args)  {

        System.out.println("Connecting to server.");

        //connects to the server
        while(!client.connectionIsGood()){
            client.init(7070);
        }
        System.out.println("Connected to server.");



        System.out.println();
        System.out.println("- - - - - - - - - - - - - - - - - ");

        //the main loop that build the request form user input,
        // and sends that request to MainServer,
        // and then displays it in an orderly fashion
        while(true){

            //asks if user wants to continue
            System.out.print("Do you wish to send a Request to the server? (accepted inputs:  yes / quit)  :");

            //gets input from the MainClient console
            String answer = client.getInput();
            System.out.println();


            //exits the main loop if user wants to quit
            if (answer.equalsIgnoreCase("quit")){
                break;
            }

            //if user doesn't want to quit, they will be asked a series of questions that will build a request,
            // and send that to MainServer
            if(answer.equalsIgnoreCase("yes") || answer.equalsIgnoreCase("y")){

                //makes, and sends a request to MainServer
                //made from user inputs from console
                String userID = makeAndSendRequest();


                //gets the response from MainServer
                JSONObject response = getResponse();

                //prints out that response in an orderly fashion
                System.out.println();
                System.out.println("Response:");
                display(response);
                System.out.println();
                System.out.println();


                //bool that when false will exit the do-while-loop
                boolean wantsToEdit = true;

                //lets the user edit the values in the file
                do {
                    System.out.println("- - - -");
                    System.out.print("Do you want to edit the values?  (accepted inputs: yes / no)    :");
                    System.out.println();

                    //gets a userinput from console
                    //the input can ONLY be    "yes" or  "y"  or  "no"  or  "n"   caps are ignored
                    //will not move on until input is accepted
                    String input = requestMaker.getYesOrNoFromConsole();

                    //when they want to edit the values
                    if(input.equalsIgnoreCase("yes") || input.equalsIgnoreCase("y")){

                        //makes, and send the alter request to the server
                        client.sendMessege(requestMaker.makeAlterRequest(response, userID).toString());

                        //gets the response from MainServer
                        JSONObject newResponse = getResponse();


                        //Displays the response
                        System.out.println();
                        System.out.println("Response:");
                        display(newResponse);
                        System.out.println();
                        System.out.println();



                        //when they do not want to edit the values
                    }else{

                        //this bool controls the do-while-loop,
                        // when it is false, application will move on to making a completely new request
                        wantsToEdit = false;
                    }
                }while(wantsToEdit);






            }
            System.out.println("- - - - - - - - - - - - - - - - - ");
            System.out.println();

        }
    }
}
