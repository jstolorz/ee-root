package org.bluesoft.filter;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;

@Provider
public class LoggingFilter implements ContainerRequestFilter {
    @Override
    public void filter(final ContainerRequestContext containerRequestContext) throws IOException {
        System.out.println(containerRequestContext.getMethod()
                + " " + containerRequestContext.getUriInfo().getAbsolutePath());

        for(String key : containerRequestContext.getHeaders().keySet()){
            System.out.println("[Logging Filter] " + key + " : "
                    + containerRequestContext.getHeaders().get(key));
        }
    }
}
