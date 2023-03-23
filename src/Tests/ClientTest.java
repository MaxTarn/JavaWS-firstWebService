package Tests;

import JavaClasses.Client;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class ClientTest {
    @BeforeEach
    void beforeEach(){
        System.out.println("before test");
    }

    @AfterEach
    void afterEach(){
        System.out.println("after test");
    }

    @Test
    void initSocket(){
        int[] goodPortNumbers = {6969,8080,32000};
        int[] badPortNumbers = {1,1000, -1,999999999};

        for (int i = 0; i < goodPortNumbers.length; i++) {
            Client client = new Client();
            client.initSockets(goodPortNumbers[i]);
            assertEquals(client.getSocket().getPort(), i);
        }
    }


}
