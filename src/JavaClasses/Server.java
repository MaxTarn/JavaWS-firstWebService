//I know it is spelled 'message' instead of 'messege'
// but I don't care

package JavaClasses;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;


//class that handles the server side of the application


public class Server {

    ServerSocket serverSocket = null;
    Socket socket = null;
    InputStreamReader inputStreamReader = null;
    BufferedReader bufferedReader = null;
    OutputStreamWriter outputStreamWriter = null;
    BufferedWriter bufferedWriter = null;
    String messege = null;
    public boolean connectionIsGood(){
        if(     serverSocket != null &&
                socket != null &&
                inputStreamReader != null &&
                bufferedReader != null &&
                outputStreamWriter != null &&
                bufferedWriter != null ){
            return true;

        }else{
            return false;
        }
    }

    //initializes the things needed
    public void init(int portNumber){
        initSockets(portNumber);
        initCommunication();
    }

    //initializes the sockets
    public void initSockets(int portNumber){
        try {
            serverSocket = new ServerSocket(portNumber);
            socket = serverSocket.accept();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    //initializes the communication
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

    //sends messege from server, to client
    public void sendMessege(String sendThisString){


        try {
            bufferedWriter.write(sendThisString);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }


    }

    // closes all open readers / sockets/ writers
    public void close(){

        try {
            socket.close();
            serverSocket.close();
            inputStreamReader.close();
            outputStreamWriter.close();
            bufferedWriter.close();
            bufferedReader.close();

        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }


    }

}
