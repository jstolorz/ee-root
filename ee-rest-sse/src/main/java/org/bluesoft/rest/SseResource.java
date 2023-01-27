package org.bluesoft.rest;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.sse.OutboundSseEvent;
import jakarta.ws.rs.sse.Sse;
import jakarta.ws.rs.sse.SseEventSink;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Path("/events")
public class SseResource {

    private final Executor executorService = Executors.newSingleThreadExecutor();


    @GET
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public void sendEvents(@Context SseEventSink sseEventSink, @Context Sse sse){

        Random random = new Random();

        IntStream stream = IntStream.generate(() -> random.nextInt(90));
        List<Integer> lottery = stream.limit(5).boxed().collect(Collectors.toList());

        executorService.execute(() -> {
            lottery.forEach(value -> {
                try{
                    TimeUnit.SECONDS.sleep(5);
                    System.out.println("Sending the following value: " + value);
                    final OutboundSseEvent outboundSseEvent = sse.newEventBuilder()
                            .name("lottery")
                            .data(Integer.class, value).build();
                    sseEventSink.send(outboundSseEvent);


                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            });
            sseEventSink.close();
        });

    }

}
