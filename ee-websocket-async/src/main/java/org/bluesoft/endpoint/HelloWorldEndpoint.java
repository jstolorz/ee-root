package org.bluesoft.endpoint;

import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/helloAS")
public class HelloWorldEndpoint {


     @OnMessage
     public String hello(String message){
        System.out.println("Received: " + message);
        return "Hello from HelloWorldEndpoint";
    }

    @OnOpen
    public void myOnOpen(Session session){
        System.out.println("WebSocket opened: " + session.getId());
    }

    @OnClose
    public void myOnClose(CloseReason reason){
        System.out.println("Closing a due to " + reason.getReasonPhrase());
    }

    @OnError
    public void error(Throwable t) {

    }

}