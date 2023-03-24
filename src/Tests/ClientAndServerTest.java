package Tests;

import JavaClasses.Client;
import JavaClasses.Server;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class ClientAndServerTest {
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
    void initClient(){
        Client client = new Client();
        client.initSockets(6969);
        client.initCommunication();

        testClient(client, 6969);

        client.reset();

        client.init(6969);

        testClient(client, 6969);
    }

    @Test
    void communication(){
        String messege = "potata";
        int portnumber = 6969;
        System.out.println(1);

        Server server = new Server();
        System.out.println(1.1);
        server.init(portnumber);
        System.out.println(2);

        Client client = new Client();
        client.init(portnumber);
        System.out.println(3);

        client.sendMessege(messege);
        assertEquals(server.getMessege(), messege);
        System.out.println(4);
    }


}
