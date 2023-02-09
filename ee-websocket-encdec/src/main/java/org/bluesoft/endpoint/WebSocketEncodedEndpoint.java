package org.bluesoft.endpoint;

import jakarta.websocket.ClientEndpoint;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import org.bluesoft.decoder.MessageDecoder;
import org.bluesoft.encoder.MessageEncoder;
import org.bluesoft.model.User;

@ClientEndpoint(
        encoders = {MessageEncoder.class},
        decoders = {MessageDecoder.class}
)
public class WebSocketEncodedEndpoint {

    @OnOpen
    public void onOpen(Session session){
        try{

            User user = new User();
            user.setName("john");
            user.setSurname("smith");
            user.setEmail("acme@gmail.com");

            session.getBasicRemote().sendObject(user);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @OnMessage
    public void onMessage(String message){
        System.out.println("Received response from server: "+ message);
    }

}
