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

    void testClient(Client client, int portNumber){
        assertNotNull(client.getInputStreamReader());
        assertNotNull(client.getBufferedReader());
        assertNotNull(client.getOutputStreamWriter());
        assertNotNull(client.getBufferedWriter());
        assertNotNull(client.getScanner());
        assertNotNull(client.getSocket());
        assertEquals(client.getSocket().getPort(), portNumber);
    }

    @Test
    void init(){
        Client client = new Client();
        client.initSockets(6969);
        client.initCommunication();

        testClient(client, 6969);

        client.reset();

        client.init(6969);

        testClient(client, 6969);
    }


}
