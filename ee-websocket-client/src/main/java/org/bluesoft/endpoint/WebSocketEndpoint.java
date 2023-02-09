package org.bluesoft.endpoint;

import jakarta.websocket.*;

@ClientEndpoint
public class WebSocketEndpoint {

    @OnOpen
    public void onOpen(Session session){

        try{
            session.getBasicRemote().sendText("Hello from Java Client!");
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @OnClose
    public void onClose(Session userSession, CloseReason reason){
        System.out.println("Session closed!");
    }

    @OnMessage
    public void onMessage(String message){
        System.out.println("Received message: " + message);
    }



}
