package com.ws.aspec;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAsp {

    @Around("execution(* com.ws.controller.*.*(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
        String metodo = joinPoint.getSignature().getName();

        Long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        Long executionTime = System.currentTimeMillis() - start;

        String data = " ";

        for(Object d : joinPoint.getArgs()){

            data += " " + d;
        }

        logger.info(metodo + "() executed in: " + executionTime + " ms  => info : " + data);
        return proceed;
    }

    @AfterReturning(value = "execution(* com.ws.service.*.*(..))" , returning = "obj")
    public void afterReturning(JoinPoint joinPoint, Object obj) {
        Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
       // logger.info("data => " + obj);
    }
}
