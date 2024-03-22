package com.y1forcode.springboot.thymeleafdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class DemoLoggingAspect {

    // set up logger
    private Logger myLogger = Logger.getLogger(getClass().getName());

    // set up pointcut expression

    @Pointcut("execution(* com.y1forcode.springboot.thymeleafdemo.controller.*.*(..))")
    private void forControllerPackage(){
    }
    //do the same for service and dao
    @Pointcut("execution(* com.y1forcode.springboot.thymeleafdemo.service.*.*(..))")
    private void forServicePackage(){
    }
    @Pointcut("execution(* com.y1forcode.springboot.thymeleafdemo.dao.*.*(..))")
    private void forDaoPackage(){
    }

    @Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage() ")
    private void forAppFlow(){}


    @Before("forAppFlow()")
    public void before(JoinPoint theJoinPoint){
        //display method we are calling
        String theMethod = theJoinPoint.getSignature().toShortString();
        myLogger.info("========> in @Before: calling method: "+theMethod);
        //display the arguments to the method


        //get the arguments
        Object[] args = theJoinPoint.getArgs();

        //loop thru and display the arguments
        for (Object tempArgs : args){
            myLogger.info("======> argument: "+tempArgs);


        }
    }



    @AfterReturning(
            pointcut = "forAppFlow()",
            returning = "theResult"
    )
    public void afterReturning(JoinPoint theJoinPoint,Object theResult){
        //display method wee re  returning from
        String theMethod = theJoinPoint.getSignature().toShortString();
        myLogger.info("========> in @AfterReturning: from method: "+theMethod);
        //display data returned
        myLogger.info("======>> result: "+ theResult);
    }
}
