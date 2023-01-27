package org.bluesoft.filter;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;

@Provider
public class CachingFilter implements ContainerResponseFilter {
    @Override
    public void filter(final ContainerRequestContext containerRequestContext, final ContainerResponseContext containerResponseContext) throws IOException {
        System.out.println("[Caching Filter] Running...");
        if(containerRequestContext.getMethod().equals("GET")){
            containerRequestContext.getHeaders().add("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0, post-check=0, pre-check=0");
            containerRequestContext.getHeaders().add("Expires", "-1");
        }

    }
}
