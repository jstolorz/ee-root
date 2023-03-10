package org.bluesoft.service;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.bluesoft.model.SimpleProperty;

import java.util.UUID;

@Path("/simple")
public class SimpleRESTService {

    @GET
    @Path("/text")
    public String getHello(){
        return "Hello World";
    }

    @GET
    @Path("/json")
    @Produces(MediaType.APPLICATION_JSON)
    public SimpleProperty getPropertyJSON(){
        SimpleProperty p = new SimpleProperty(UUID.randomUUID().toString(),
                UUID.randomUUID().toString());
        return p;
    }

    @GET
    @Path("/xml")
    @Produces(MediaType.APPLICATION_XML)
    public SimpleProperty getPropertyXML(){
        SimpleProperty p = new SimpleProperty(UUID.randomUUID().toString(),
                UUID.randomUUID().toString());
        return p;
    }

}
