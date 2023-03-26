package JavaClasses;


import org.json.simple.JSONObject;

import java.util.Scanner;

public class Request {
    JSONObject request = new JSONObject();

    String fileToAccess;
    String GetOrPost;
    Scanner in = new Scanner(System.in);
    Json json = new Json();

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

    public void make() {
        JSONObject jsonObj = json.getJsonObjFromFile(json.getJsonPath());
        System.out.println("There are currently " + jsonObj.get("length") + " different entries.");
        System.out.println("What entry do you want to access? (accepted inputs: 1 through " + jsonObj.get("length") + ")");







    }

}
