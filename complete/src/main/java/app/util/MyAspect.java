package app.util;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class MyAspect {

    @Around("@annotation(app.configuration.AspectAnnotation)")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        joinPoint.proceed();
        System.err.println("ASPECT!!!!!!!!!!!!!!!!!!");
        System.err.println("ASPECT!!!!!!!!!!!!!!!!!!");
        System.err.println("ASPECT!!!!!!!!!!!!!!!!!!");
    }

}
