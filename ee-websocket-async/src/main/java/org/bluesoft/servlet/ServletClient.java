package org.bluesoft.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.websocket.*;
import org.bluesoft.endpoint.WebSocketEndpoint;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;

@WebServlet("/connectAS")
class ServletClient extends HttpServlet {

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        final PrintWriter writer = resp.getWriter();
        WebSocketContainer container = ContainerProvider
                .getWebSocketContainer();

        String uri = "ws://localhost:8080/ee-websocket-async/helloAS";

        try{

            final Session session = container.connectToServer(
                    WebSocketEndpoint.class,
                    new URI(uri));

            session.getAsyncRemote().sendText("Hello from Async Message", new SendHandler() {
                @Override
                public void onResult(final SendResult sendResult) {
                   if(sendResult.isOK()){
                       System.out.println("Successfully completed!");
                   }
                }
            });

            writer.println("Asynchronous message sent to WebSocket endpoint!");
            writer.println("Check Server Logs.");

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
