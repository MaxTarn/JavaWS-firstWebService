package JavaClasses;


import org.json.simple.JSONObject;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class MakeRequest {
    JSONObject request = new JSONObject();

    String fileToAccess;
    Scanner in = new Scanner(System.in);

    String getInputAsString(){
        String temp;
        do {
            temp = in.nextLine();
        }while (temp == null);
        return temp;
    }

    int getInputAsInt(){
        return Integer.parseInt(getInputAsString());
    }
    Json json = new Json();
    public void makeRequest() {
        System.out.println("There are currently " + json.getAllPersonsPath().length + " different entries.");
        System.out.println("What entry do you want to access?");

        try {
            int input = getInputAsInt() + 1;
            fileToAccess = json.getPersonPath(input);
        }catch (Exception ex){
            System.out.println("invalid input: only numbers within specified range allowed");
            System.out.println();
            System.out.println();
            makeRequest();
        }




    }

}
