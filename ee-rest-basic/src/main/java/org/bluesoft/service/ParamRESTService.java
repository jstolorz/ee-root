package org.bluesoft.service;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.bluesoft.ejb.DataList;
import org.bluesoft.model.SimpleProperty;

import java.util.List;

@Path("/param")
public class ParamRESTService {

   private final String RESPONSE_OK="<p>Property has been added ! </p>" +
            "<p><a href=\"http://localhost:8080/ee-rest-basic\">Back</a></p>";
   @Inject
   private DataList ejb;

   @GET
   @Path("/xml/{id}")
   @Produces(MediaType.APPLICATION_XML)
   public SimpleProperty getPropertyByPathParam(@DefaultValue("0") @PathParam("id")int id){
       return ejb.getList().get(id);
   }

   @GET
   @Path("/xml")
   @Produces(MediaType.APPLICATION_XML)
   public SimpleProperty getPropertyByQueryId(@DefaultValue("0") @QueryParam("id")int id){
       return ejb.getList().get(id);
   }

   @GET
   @Path("/list")
   @Produces(MediaType.APPLICATION_XML)
   public List<SimpleProperty> getProperties(){
       return ejb.getList();
   }

   @POST
   @Produces("text/html")
   public Response createProperty(@FormParam("key")String key,
                                  @FormParam("value")String value)
   {
      int n = ejb.addToList(key,value);
      return Response.ok(RESPONSE_OK).build();

   }

}
