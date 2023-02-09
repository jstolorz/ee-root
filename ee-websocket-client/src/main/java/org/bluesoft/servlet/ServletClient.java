package org.bluesoft.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.websocket.ContainerProvider;
import jakarta.websocket.Session;
import jakarta.websocket.WebSocketContainer;
import org.bluesoft.endpoint.WebSocketEndpoint;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;

@WebServlet("/connect")
class ServletClient extends HttpServlet {

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {

        final PrintWriter writer = resp.getWriter();

        WebSocketContainer container = ContainerProvider.getWebSocketContainer();

        String uri = "ws://localhost:8080/ee-websocket-basic/hello";

        try{
            Session session = container.connectToServer(WebSocketEndpoint.class, new URI(uri));

            writer.println("Message sent to WebSocket EndPoint!");
            writer.println("Check Server Logs.");

        }catch (Exception e){
                e.printStackTrace();
        }

    }

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
