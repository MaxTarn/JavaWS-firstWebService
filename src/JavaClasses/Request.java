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
        String temp;
        do {
            temp = in.nextLine();
        }while (temp == null);
        return temp;
    }

    public void make() {
        JSONObject jsonObj = json.getJsonObjFromFile(json.getJsonPath());
        System.out.println("There are currently " + jsonObj.get("length") + " different entries.");
        System.out.println("What entry do you want to access? (accepted inputs: 1 through " + jsonObj.get("length") + ")");







    }

}
