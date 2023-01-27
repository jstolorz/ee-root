package org.bluesoft.client;


import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.annotation.Resource;
import jakarta.ejb.*;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.sse.SseEventSource;

import java.util.ArrayList;

@Singleton
@Startup
public class SSEClient {

    private Client client;
    private WebTarget target;
    private SseEventSource eventSource;
    private ArrayList<String> listUser;

    @Resource
    private TimerService timerService;

    @PostConstruct
    public void init(){
        client = ClientBuilder.newClient();
        target = client.target("http://localhost:8080/ee-rest-sse/rest/events");

        timerService.createSingleActionTimer(5000,new TimerConfig());
        System.out.println("SSE client timer created");
        eventSource = SseEventSource.target(target).build();
        System.out.println("SSE Event source created........");
    }

    @Timeout
    public void client() {
//
//        try{
//
//            eventSource.register((sseEvent) -> {
//                System.out.println("SEE event received ----- " + sseEvent.readData());
//            }, (e) -> e.printStackTrace());
//
//            eventSource.open();
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }

    }


    public void addUser(String username) {
        listUser.add(username);
    }

    public void removeUser(String username) {
        listUser.remove(username);
    }


    public ArrayList<String> getListUser() {
        return listUser;
    }

    @PreDestroy
    public void close(){
        eventSource.close();
        System.out.println("Closed SSE Event source..");
        client.close();
        System.out.println("Closed JAX-RS client..");
    }

}
