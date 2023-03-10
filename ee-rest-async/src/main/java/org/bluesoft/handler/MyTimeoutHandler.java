package org.bluesoft.handler;

import jakarta.ws.rs.container.AsyncResponse;
import jakarta.ws.rs.container.TimeoutHandler;
import jakarta.ws.rs.core.Response;

import java.net.HttpURLConnection;

public class MyTimeoutHandler implements TimeoutHandler {
    @Override
    public void handleTimeout(final AsyncResponse asyncResponse) {
        Response r = Response.serverError().status(HttpURLConnection.HTTP_UNAVAILABLE).build();
        asyncResponse.resume(r);
    }
}
