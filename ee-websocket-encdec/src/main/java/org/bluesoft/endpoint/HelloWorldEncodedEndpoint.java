package org.bluesoft.endpoint;

import jakarta.websocket.EncodeException;
import jakarta.websocket.OnMessage;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import org.bluesoft.decoder.MessageDecoder;
import org.bluesoft.encoder.MessageEncoder;
import org.bluesoft.model.User;

import java.io.IOException;

@ServerEndpoint(value = "/helloencoded",
encoders = {MessageEncoder.class},
decoders = MessageDecoder.class)
public class HelloWorldEncodedEndpoint {


    @OnMessage
    public void onMessage(User user, Session session){
        System.out.println("Message Received from " + user.getEmail());

        user.setRegistered(true);

        try{
            session.getBasicRemote().sendObject(user);
        }catch (EncodeException ee){
            ee.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
