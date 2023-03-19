package JavaClasses;

import java.io.*;

public class Json {
    public boolean fileExists(String filePath){
        File path = new File(filePath);
        return path.exists();
    }

    public String getStringFromFile(String filePath) throws IOException {

        if(fileExists(filePath)){
            FileReader path = new FileReader(filePath);
            BufferedReader reader = new BufferedReader(path);
            StringBuilder content  = null;
            String temp;

            while(  (temp = reader.readLine())  !=   null  ){
                assert false;
                content.append(temp);
            }

            System.out.println(content);

        }
        return null;
    }
}
