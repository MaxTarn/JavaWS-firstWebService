package JavaClasses;


import org.json.simple.JSONObject;

import java.util.Scanner;

public class Request {
    JSONObject request = new JSONObject();

    Scanner in = new Scanner(System.in);
    Json json = new Json();


    int chosenEntry;
    String method;
    String contentType = "application/json";



    String getInput(){
        return in.nextLine();
    }

    int getInputBetween(int lower, int higher){

        boolean goodInput = false;
        int temp = -1;
        do {

            try {
                temp = Integer.parseInt(getInput());
            }catch (Exception ex){
                System.out.println();
            }
            if(temp <= higher && temp >= lower) {
                goodInput = true;
            }else{
                System.out.print("Not valid input. Try again.");
            }

        }while(!goodInput);
        return temp;

    }
    boolean isYesOrNo(String input){


        if (input.equalsIgnoreCase("yes")){
            return true;
        }
        if(input.equalsIgnoreCase("y")){
            return true;
        }


        if(input.equalsIgnoreCase("no")){
            return true;
        }
        if(input.equalsIgnoreCase("n")){
            return true;
        }

        return false;

    }

    public Boolean inputFromConsoleIsYes(String input){
        if (isYesOrNo(input)){
            return input.equalsIgnoreCase("yes") || input.equalsIgnoreCase("y");
        }else {
            return null;
        }
    }

    public String getYesOrNoFromConsole(){
        boolean goodInput = false;
        String temp = null;
        do {

            try {

                temp = getInput();

                if (isYesOrNo(temp)){
                    goodInput = true;
                }else{
                    System.out.print("Try again:");
                }

            }catch (Exception ex){
                System.out.println(ex.getMessage());
            }



        }while(!goodInput);
        return temp;
    }



    public JSONObject makeRequest() {

        JSONObject jsonObj = json.getJsonObjFromFile(json.getJsonPath());
        int numberOfEntries = Integer.parseInt(jsonObj.get("length").toString());

        System.out.println("There are currently " + numberOfEntries + " different entries.");
        System.out.print("What entry do you want to access? (accepted inputs: 1 through " + numberOfEntries + ")   :");
        chosenEntry = getInputBetween(1,numberOfEntries);
        System.out.println();


        method = "GET";
        request.put("HTTPMethod", method);
        request.put("ContentType", contentType);
        request.put("URLParameters", "person/" + (chosenEntry));

        return request;
    }

    public JSONObject makeAlterRequest(JSONObject personToAlter, String personID){
        JSONObject personToAlterFeatures = (JSONObject) personToAlter.get("Features");

        System.out.print("What line do you wish to edit?   :");
        int chosenLine = getInputBetween(1, 7);
        System.out.println();

        String newValue = "";
        String newFirstName = "";
        String newLastName = "";

        //on line 1 both first name and last name is printed out
        if (chosenLine == 1){
            System.out.print("Change first name to:   ");
            newFirstName = getInput();
            System.out.println();

            System.out.print("Change last name to:   ");
            newLastName = getInput();
            System.out.println();
        }else {
            System.out.print("It will be changed to:   ");
            newValue = getInput();
            System.out.println();
        }

        String[] keyNames = {"firstName", "lastName", "age", "species", "gender", "hairColor", "eyeColor", "nose"};
        switch (chosenLine){

            case 1:
                personToAlter.replace(keyNames[0], newFirstName);
                personToAlter.replace(keyNames[1], newLastName);
                break;
            case 2:
                personToAlter.replace(keyNames[2], newValue);
                break;
            case 3:
                personToAlter.replace(keyNames[3], newValue);
                break;
            case 4:
                personToAlter.replace(keyNames[4], newValue);
                break;
            case 5:
                personToAlterFeatures.replace(keyNames[5], newValue);
                break;
            case 6:
                personToAlterFeatures.replace(keyNames[6], newValue);
                break;
            case 7:
                personToAlterFeatures.replace(keyNames[7], newValue);
                break;

            default:
                System.out.println("this should not be printed to console, no matter what.");
                break;
        }
        personToAlter.replace("Features", personToAlterFeatures);


        JSONObject request = new JSONObject();
        request.put("HTTPMethod", "POST");
        request.put("ContentType", contentType);
        request.put("URLParameters", "person/" + (personID));

        JSONObject requestBody = new JSONObject();
        requestBody.put(personID, personToAlter);


        request.put("body", requestBody);

        return  request;

    }

}
