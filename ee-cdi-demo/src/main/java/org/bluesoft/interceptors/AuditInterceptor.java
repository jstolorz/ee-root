package org.bluesoft.interceptors;

import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import org.bluesoft.qualifier.Auditing;

import java.io.Serializable;

@Interceptor
@Auditing
public class AuditInterceptor implements Serializable {

    @AroundInvoke
    public Object logMethodEntry(InvocationContext ctx) throws Exception{
        System.out.println("Before entering method:" + ctx.getMethod().getName());
        return ctx.proceed();
    }

}
