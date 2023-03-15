package JavaClasses;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args){
        Socket socket ;
        ServerSocket serverSocket = null;
        InputStreamReader inputStreamReader ;
        BufferedReader bufferedReader ;

        OutputStreamWriter outputStreamWriter ;
        BufferedWriter bufferedWriter ;



        try {
            serverSocket = new ServerSocket(6969);
        }catch (Exception ex){
            System.out.println(ex);
        }

        while (true){
            try {
                socket = serverSocket.accept();

                inputStreamReader = new InputStreamReader(socket.getInputStream());
                outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
                bufferedReader = new BufferedReader(inputStreamReader);
                bufferedWriter = new BufferedWriter(outputStreamWriter);


                while (true){
                    String messege = bufferedReader.readLine();
                    System.out.println("client: " + messege);

                    bufferedWriter.write("Messege received.");
                    bufferedWriter.newLine();
                    bufferedWriter.flush();

                    if (messege.equalsIgnoreCase("quit")){
                        break;
                    }
                }
                socket.close();
                inputStreamReader.close();
                outputStreamWriter.close();
                bufferedWriter.close();
                bufferedReader.close();


            }catch (Exception ex){
                System.out.println(ex);
            }




        }
    }
}
