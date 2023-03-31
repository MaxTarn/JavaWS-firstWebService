package Tests;

import JavaClasses.Client;
import JavaClasses.Json;
import JavaClasses.Server;
import org.json.simple.JSONObject;
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
    void initSocketsInitCommunicationClient(){
        Client client = new Client();
        client.initSockets(6969);
        client.initCommunication();

        testClient(client, 6969);

        client.close();
    }

    @Test
    void initClient(){
        Client client = new Client();
        client.init(6969);

        testClient(client, 6969);
        client.close();
    }


    @Test
    void jsonFileIsGood(){
        Json json = new Json();
        JSONObject allPersons = json.getJsonObjFromFile(json.getJsonPath());
        String length = allPersons.get("length").toString();
        assertNotNull(length);
        assertNotNull(json.getPerson(allPersons, "1"));
        assertNotNull(json.getPerson(allPersons, length));
        assertTrue(json.isJsonPerson(json.getPerson(allPersons, "1")));
        assertTrue(json.isJsonPerson(json.getPerson(allPersons, length)));
    }

    @Test
    void alterJsonFile(){
        Json json = new Json();
        JSONObject allPersons = json.getJsonObjFromFile(json.getJsonPath());
        JSONObject firstPerson = (JSONObject) allPersons.get("1");
        String oldAge = firstPerson.get("age").toString();
        firstPerson.replace("age", "42069");

        json.alterPerson("1", firstPerson);


        allPersons = json.getJsonObjFromFile(json.getJsonPath());
        firstPerson = (JSONObject)allPersons.get("1");

        assertEquals(firstPerson.get("age").toString(), "42069");
    }











}
