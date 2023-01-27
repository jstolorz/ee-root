package org.bluesoft;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.bluesoft.model.SimpleProperty;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class TestClient {

    private final String BASE_URL ="http://localhost:8080/ee-rest-client/rest";
    private Client client;

    @Before
    public void init(){
        client = ClientBuilder.newClient();
    }

    @Test
    public void testPostParam(){


        SimpleProperty p1 = new SimpleProperty("mykey","value");

        WebTarget myResource = client.target(BASE_URL).path("/service");
        Response rs = myResource.request(MediaType.APPLICATION_JSON)
                .post(Entity.json(p1), Response.class);

        assertEquals(rs.getStatus(),200);


    }

    @Test
    public void testGetParam(){
        SimpleProperty property = client.target(BASE_URL).path("/service/{id}")
                .resolveTemplate("id","0")
                .request(MediaType.APPLICATION_JSON)
                .get(SimpleProperty.class);

        assertNotNull(property);

    }

    @Test
    public void testGetAllParam(){
        List<SimpleProperty> list = client.target(BASE_URL).path("/service")
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<List<SimpleProperty>>() {
                });

        assertEquals("mykey",list.get(0).getKey());
        assertEquals("value",list.get(0).getValue());
    }




}
