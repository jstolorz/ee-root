package org.bluesoft.decoder;

import jakarta.websocket.DecodeException;
import jakarta.websocket.Decoder;
import jakarta.websocket.EndpointConfig;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;
import org.bluesoft.model.User;

import java.io.StringReader;

public class MessageDecoder implements Decoder.Text<User> {
    @Override
    public User decode(final String s) throws DecodeException {
        System.out.println("Incoming XML " + s);
        User person = null;
        JAXBContext jaxbContext;

        try{

            jaxbContext = JAXBContext.newInstance(User.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            StringReader reader = new StringReader(s);
            person = (User) unmarshaller.unmarshal(reader);

        }catch (Exception e){
            e.printStackTrace();
        }

        return person;
    }

    @Override
    public boolean willDecode(final String s) {
        return (s != null);
    }

    @Override
    public void init(final EndpointConfig config) {
        Text.super.init(config);
    }

    @Override
    public void destroy() {
        Text.super.destroy();
    }
}
