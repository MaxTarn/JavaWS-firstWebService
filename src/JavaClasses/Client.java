package JavaClasses;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;


//handles the client side os the application
//
public class Client {

    //varios reader / writers / socket that are need for the communication between client and sever
    Socket socket = null;
    InputStreamReader inputStreamReader = null;
    BufferedReader bufferedReader = null;

    OutputStreamWriter outputStreamWriter = null;
    BufferedWriter bufferedWriter = null;
    Scanner scanner = null;
    String messege = null;

    public Socket getSocket() {
        return socket;
    }

    public InputStreamReader getInputStreamReader() {
        return inputStreamReader;
    }

    public BufferedReader getBufferedReader() {
        return bufferedReader;
    }

    public OutputStreamWriter getOutputStreamWriter() {
        return outputStreamWriter;
    }

    public BufferedWriter getBufferedWriter() {return bufferedWriter;}

    public Scanner getScanner() {
        return scanner;
    }

    //initializes the readers / writers /socket
    public void init(int portNumber){
        try {
            initSockets(portNumber);
            initCommunication();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    //initializes the socket
    public void initSockets(int portNumber){
        try {
            socket = new Socket("localhost", portNumber);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    //initializes the readers / writers
    public void initCommunication(){
        try {
            inputStreamReader = new InputStreamReader(socket.getInputStream());
            bufferedReader = new BufferedReader(inputStreamReader);
            outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
            bufferedWriter = new BufferedWriter(outputStreamWriter);
            scanner = new Scanner(System.in);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    //checks if the different readers / writers / socket are assigned a value and not null
    public boolean connectionIsGood(){
        if(     socket != null &&
                inputStreamReader != null &&
                bufferedReader != null &&
                outputStreamWriter != null &&
                bufferedWriter != null &&
                scanner != null){
            return true;

        }else{
            return false;
        }
    }

    //reads the incoming communication coming from server, through socket
    public String getMessege(){
        try {
            messege = bufferedReader.readLine();
            return messege;
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }

    //returns input from console, as a String
    public String getInput(){
        return scanner.nextLine();
    }

    //sends a string to the server, most likely in a JSON format
    public void sendMessege(String sendThis){
        try{
            bufferedWriter.write(sendThis);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }


    // closes all open readers / sockets/ writers
    public void close(){
        try{
            socket.close();
            inputStreamReader.close();
            bufferedReader.close();
            outputStreamWriter.close();
            bufferedWriter.close();
            scanner.close();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

}
