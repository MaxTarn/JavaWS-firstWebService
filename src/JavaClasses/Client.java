package JavaClasses;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
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

    public BufferedWriter getBufferedWriter() {
        return bufferedWriter;
    }

    public Scanner getScanner() {
        return scanner;
    }

    //initializes the things needed
    public boolean init(int portNumber){
        try {
            initSockets(portNumber);
            initCommunication();
        }catch (Exception ex){
            return false;
        }

        return true;
    }
    public void initSockets(int portNumber){
        try {
            socket = new Socket("localhost", portNumber);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

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

    public String getMessege(){
        try {
            messege = bufferedReader.readLine();
            return messege;
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }
    public String getInput(){
        return scanner.nextLine();
    }

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

    public void reset(){
        this.close();
        socket = null;
        inputStreamReader = null;
        bufferedReader = null;
        outputStreamWriter = null;
        bufferedWriter = null;
        scanner = null;
    }

}
