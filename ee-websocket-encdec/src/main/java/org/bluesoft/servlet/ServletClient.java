package org.bluesoft.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.websocket.ContainerProvider;
import jakarta.websocket.WebSocketContainer;
import org.bluesoft.endpoint.WebSocketEncodedEndpoint;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;

@WebServlet("/connect")
class ServletClient extends HttpServlet {

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        final PrintWriter writer = resp.getWriter();

         try{

             WebSocketContainer container = ContainerProvider.getWebSocketContainer();
             String uri =  "ws://localhost:8080/ee-websocket-encdec/helloencoded";
             container.connectToServer(WebSocketEncodedEndpoint.class, URI.create(uri));
             writer.println("Message sent to Encoded WebSocket!");
             writer.println("Check Server Logs.");


         }catch (Exception e){
             e.printStackTrace();
         }

    }
}
