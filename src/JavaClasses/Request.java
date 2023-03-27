package JavaClasses;


import org.json.simple.JSONObject;

import java.util.Scanner;

public class Request {
    JSONObject request = new JSONObject();

    Scanner in = new Scanner(System.in);
    Json json = new Json();
    String input = null;


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
                System.out.println("Not valid input. Try again.");
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

    Boolean isYes(String input){
        if (isYesOrNo(input)){
            return input.equalsIgnoreCase("yes") || input.equalsIgnoreCase("y");
        }else {
            return null;
        }
    }

    String getYesOrNo(){
        boolean goodInput = false;
        String temp = null;
        do {

            try {

                temp = getInput();

                if (isYesOrNo(temp)){
                    goodInput = true;
                }

            }catch (Exception ex){
                System.out.println(ex.getMessage());
            }



        }while(!goodInput);
        return temp;
    }



    public JSONObject makeRequest() {
        String temp;
        JSONObject jsonObj = json.getJsonObjFromFile(json.getJsonPath());
        int numberOfEntries = Integer.parseInt(jsonObj.get("length").toString());

        System.out.println("There are currently " + numberOfEntries + " different entries.");
        System.out.println("What entry do you want to access? (accepted inputs: 1 through " + numberOfEntries + ")");

        chosenEntry = getInputBetween(1,numberOfEntries);

        System.out.println();
        System.out.println();
        System.out.println("Do you wish to alter the entry? (yes / no)");

        temp = getYesOrNo();
        if(isYes(temp)){
            method = "POST";
        }else {
            method = "GET";
        }
        request.put("HTTPMethod", method);
        request.put("ContentType", contentType);
        request.put("URLParameters", "person/" + (chosenEntry));

        return request;






    }

}
