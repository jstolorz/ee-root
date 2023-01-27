package org.bluesoft.filter;


import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.bluesoft.model.SimpleProperty;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CachingFilterSpec {
    private final String BASE_URL ="http://localhost:8080/ee-rest-filters/rest";

    @Test
    public void testParam(){
        Client client = ClientBuilder.newClient();
        client.register(new ClientFilter("user","password"));
        SimpleProperty p1 = new SimpleProperty("mykey","value");

        WebTarget myResource = client.target(BASE_URL).path("/service");
        Response rs = myResource.request(MediaType.APPLICATION_JSON)
                .post(Entity.json(p1), Response.class);

        assertEquals(rs.getStatus(),200);



    }
  
}