package org.bluesoft.encoder;

import jakarta.websocket.EncodeException;
import jakarta.websocket.Encoder;
import jakarta.websocket.EndpointConfig;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import org.bluesoft.model.User;

import java.io.StringWriter;

public class MessageEncoder implements Encoder.Text<User> {
    @Override
    public String encode(final User user) throws EncodeException {

        JAXBContext jaxbContext = null;
        StringWriter stringWriter = null;

        try{

            jaxbContext = JAXBContext.newInstance(User.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            stringWriter = new StringWriter();
            marshaller.marshal(user, stringWriter);
            System.out.println("OutGoing XML " + stringWriter.toString());

        }catch (Exception e){
            e.printStackTrace();
        }

        return stringWriter.toString();
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
