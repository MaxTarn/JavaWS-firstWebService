package JavaClasses;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    ServerSocket serverSocket = null;
    Socket socket = null;
    InputStreamReader inputStreamReader = null;
    BufferedReader bufferedReader = null;
    OutputStreamWriter outputStreamWriter = null;
    BufferedWriter bufferedWriter = null;
    String messege = null;



    public void initSockets(int portNumber){
        //sets the port of the server, taken as argument
        try {
            serverSocket = new ServerSocket(portNumber);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        //does something
        try {
            socket = serverSocket.accept();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    public void initCommunication(){

        //opens two-way line of communication
        try {
            inputStreamReader = new InputStreamReader(socket.getInputStream());
            outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
            bufferedReader = new BufferedReader(inputStreamReader);
            bufferedWriter = new BufferedWriter(outputStreamWriter);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    //returns the message, null when there isn't one available
    public String getMessege(){
        try {
            messege = bufferedReader.readLine();
            System.out.println("client: " + messege);

            sendMessege("Messege received.");
            return messege;
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }

    }

    public void sendMessege(String sendThisString){
        try {
            bufferedWriter.write(sendThisString);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public void close(){

        //closes the socket
        try {
            socket.close();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        //closes the serverSocket
        try{
            serverSocket.close();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        //closes the inputStreamReader
        try {
            inputStreamReader.close();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        //closes the outputStreamWriter
        try {
            outputStreamWriter.close();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        //closes the bufferedWriter
        try {
            bufferedWriter.close();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        //closes the bufferedReader
        try {
            bufferedReader.close();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }


    }


//    public static void main(String[] args){
//        Socket socket ;
//        ServerSocket serverSocket = null;
//        InputStreamReader inputStreamReader ;
//        BufferedReader bufferedReader ;
//
//        OutputStreamWriter outputStreamWriter ;
//        BufferedWriter bufferedWriter ;
//
//
//
//        try {
//            serverSocket = new ServerSocket(6969);
//        }catch (Exception ex){
//            System.out.println(ex);
//        }
//
//        while (true){
//            try {
//                socket = serverSocket.accept();
//
//                inputStreamReader = new InputStreamReader(socket.getInputStream());
//                outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
//                bufferedReader = new BufferedReader(inputStreamReader);
//                bufferedWriter = new BufferedWriter(outputStreamWriter);
//
//
//                while (true){
//                    String messege = bufferedReader.readLine();
//                    System.out.println("client: " + messege);
//
//                    bufferedWriter.write("Messege received.");
//                    bufferedWriter.newLine();
//                    bufferedWriter.flush();
//
//                    if (messege.equalsIgnoreCase("quit")){
//                        break;
//                    }
//                }
//                socket.close();
//                inputStreamReader.close();
//                outputStreamWriter.close();
//                bufferedWriter.close();
//                bufferedReader.close();
//
//
//            }catch (Exception ex){
//                System.out.println(ex);
//            }
//        }
//    }
}
