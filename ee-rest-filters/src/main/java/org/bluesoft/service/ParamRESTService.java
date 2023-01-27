package org.bluesoft.service;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.bluesoft.ejb.DataList;
import org.bluesoft.model.SimpleProperty;

import java.util.List;

@Path("/service")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ParamRESTService {

    @Inject
    private DataList ejb;

    @GET
    @Path("/{id}")
    public SimpleProperty getPropertyById(@PathParam("id")int id){
        return ejb.getList().get(id);
    }

    @GET
    public List<SimpleProperty> getProperty(){
        return ejb.getList();
    }

    @POST
    public Response createProperty(SimpleProperty p){
        int n = ejb.addToList(p);
        return Response.status(Response.Status.OK).build();
    }

}
