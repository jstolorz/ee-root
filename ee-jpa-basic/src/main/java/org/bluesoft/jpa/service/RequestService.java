package org.bluesoft.jpa.service;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.bluesoft.jpa.ejb.ManagerEJB;
import org.bluesoft.jpa.entity.Request;

@Path("/request")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RequestService {

    @Inject
    private ManagerEJB ejb;


    @POST
    @Path("/{id}")
    public Response createRequest(@PathParam("id") Long id, Request request){
        ejb.createRequest(id,request);
        return Response.status(201).build();
    }





}
