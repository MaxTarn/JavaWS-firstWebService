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
    String messege = null;

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
            Scanner scanner = new Scanner(System.in);
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
            socket.close() ;
            inputStreamReader.close();
            bufferedReader.close();
            outputStreamWriter.close();
            bufferedWriter.close();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }



    public static void main(String[] args) {

        Socket socket = null;

        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;

        OutputStreamWriter outputStreamWriter = null;
        BufferedWriter bufferedWriter = null;

        while(true){
            try {
                socket = new Socket("localhost", 6969);
                inputStreamReader = new InputStreamReader(socket.getInputStream());
                outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());

                bufferedReader = new BufferedReader(inputStreamReader);
                bufferedWriter = new BufferedWriter(outputStreamWriter);

                bufferedWriter = new BufferedWriter(outputStreamWriter);
                Scanner scanner = new Scanner(System.in);

                while(true){
                    String messege = scanner.nextLine();
                    bufferedWriter.write(messege);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();

                    System.out.println(bufferedReader.readLine());

                    if (messege.equalsIgnoreCase("quit")){
                        break;
                    }
                }

            }catch (Exception ex){
                System.out.println(ex.getMessage());
            }finally {
                try {
                    if (socket != null) {
                        socket.close();
                    }
                    if (inputStreamReader != null) {
                        inputStreamReader.close();
                    }
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                    if (outputStreamWriter != null) {
                        outputStreamWriter.close();
                    }
                    if (bufferedWriter != null) {
                        bufferedWriter.close();
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        }




    }
}
