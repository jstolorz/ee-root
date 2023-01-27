package org.bluesoft.service;

import jakarta.ws.rs.client.*;
import jakarta.ws.rs.core.Feature;
import jakarta.ws.rs.core.MediaType;
import org.bluesoft.model.SimpleProperty;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static org.junit.Assert.assertEquals;

public class TestSyncClient {

    private final String BASE_URL ="http://localhost:8080/ee-rest-async/rest";
    private Client client;

    @Before
    public void init(){
        client = ClientBuilder.newClient();
    }

    @Test
    public void testClientAsyncPost() throws ExecutionException, InterruptedException {

        SimpleProperty simplePropertyIn = new SimpleProperty("mykey","value");

        WebTarget myResource = client.target(BASE_URL + "/service");

        Future<SimpleProperty> ret = myResource.request(MediaType.APPLICATION_JSON).async()
                .post(Entity.json(simplePropertyIn), SimpleProperty.class);

        SimpleProperty property = ret.get();
        assertEquals("value", property.getValue());

    }

    @Test
    public void testClientAsyncGet(){

        Future<SimpleProperty> future  = client
                .target(BASE_URL + "/service/{key}")
                .resolveTemplate("key","mykey")
                .request(MediaType.APPLICATION_JSON).async()
                .get(SimpleProperty.class);

        SimpleProperty propertyOut = null;

        try{

            while (!future.isDone()){
                Thread.sleep(100);
            }

            propertyOut = future.get();

        }catch (Exception e){
            e.printStackTrace();
        }

        assertEquals("value", propertyOut.getValue());
    }

    @Test
    public void testClientAsyncGetInvocationCallback(){

        client.target(BASE_URL + "/service/{key}")
                .resolveTemplate("key","secretkey")
                .request(MediaType.APPLICATION_JSON)
                .async()
                .get(new InvocationCallback<SimpleProperty>() {
                    @Override
                    public void completed(final SimpleProperty simpleProperty) {
                        System.out.println("Got a " + simpleProperty);
                    }

                    @Override
                    public void failed(final Throwable throwable) {
                        System.out.println("Problem occurred !");
                    }
                });

    }

}
