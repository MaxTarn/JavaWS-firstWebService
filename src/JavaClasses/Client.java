package JavaClasses;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
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
                System.out.println(ex);
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
