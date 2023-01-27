package org.bluesoft.interceptor;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.ext.*;

import java.io.*;
import java.util.stream.Collectors;

@Provider
public class MyInterceptor implements ReaderInterceptor, WriterInterceptor {
    @Override
    public Object aroundReadFrom(final ReaderInterceptorContext readerInterceptorContext) throws IOException, WebApplicationException {

        System.out.println("Called Reader Interceptor");
        InputStream stream = readerInterceptorContext.getInputStream();

        String body = new BufferedReader(new InputStreamReader(stream))
                .lines().collect(Collectors.joining("\n"));

        System.out.println("Body: " + body);

        readerInterceptorContext.setInputStream(
                new ByteArrayInputStream((body + "\nExtra Body.\n").getBytes()));
        Object resuult = readerInterceptorContext.proceed();

        return resuult;
    }

    @Override
    public void aroundWriteTo(final WriterInterceptorContext writerInterceptorContext) throws IOException, WebApplicationException {
        System.out.println("Called WriterInterceptor");
        OutputStream os = writerInterceptorContext.getOutputStream();
        os.write("Extra output\n".getBytes());
        writerInterceptorContext.proceed();
    }
}
