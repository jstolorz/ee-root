package org.bluesoft.service;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.container.AsyncResponse;
import jakarta.ws.rs.container.Suspended;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.bluesoft.ejb.DataList;
import org.bluesoft.handler.MyTimeoutHandler;
import org.bluesoft.model.SimpleProperty;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;

@Path("/service")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ParamRESTService {

    @Inject
    private DataList ejb;

    @GET
    @Path("/stage/{key}")
    public CompletionStage<SimpleProperty> getAsync(final @PathParam("key") String key){

        CompletionStage<SimpleProperty> cs = CompletableFuture
                .<SimpleProperty>supplyAsync(
                        () -> {
                            System.out.println(" execute long run task in CompletionStageResource");

                            try{
                                Thread.sleep(500);
                            }catch (InterruptedException e){
                                e.printStackTrace();
                            }
                            return ejb.get(key);
                        }
                );

        return cs;

    }

    @GET
    @Path("/{key}")
    public void asyncGet(final @Suspended AsyncResponse asyncResponse, final @PathParam("key") String key){

        asyncResponse.setTimeout(10, TimeUnit.SECONDS);
        asyncResponse.setTimeoutHandler(new MyTimeoutHandler());

        new Thread(new Runnable() {
          public void run(){
              SimpleProperty p = ejb.get(key);
              System.out.println("key is " + key);
              asyncResponse.resume(p);
          }
        }).start();

    }

    @POST
    public Response asyncPost(final @Suspended AsyncResponse asyncResponse, SimpleProperty p){

        asyncResponse.setTimeout(10,TimeUnit.SECONDS);
        asyncResponse.setTimeoutHandler(new MyTimeoutHandler());

        new Thread(new Runnable() {
            @Override
            public void run() {
                int n = ejb.addToList(p);
                asyncResponse.resume(p);
            }
        }).start();

        return Response.ok(p).build();
    }




}
