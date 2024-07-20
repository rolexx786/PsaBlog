package com.psaBlog.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import javax.xml.crypto.Data;
import java.util.Date;

@Aspect
@Component
public class EmployeeAspect {
    @Before(value = "excecution(* com.psaBlog.controller.EmployeeController.*(..))")
    public void beforeAdvice(JoinPoint joinPoint){
        System.out.println("Request to "+joinPoint.getSignature()+" Started at "+new Date());

    }
}
