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
    void initSockets(){
        Client client = new Client();
        client.initSockets(6969);
        assertNotEquals(client.getSocket(), null);
        assertEquals(client.getSocket().getPort(), 6969);
    }


}
