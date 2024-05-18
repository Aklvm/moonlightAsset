// package com.ecom.moonlight.logging;

// import org.aspectj.lang.JoinPoint;
// import org.aspectj.lang.annotation.AfterThrowing;
// import org.aspectj.lang.annotation.Aspect;
// import org.aspectj.lang.annotation.Pointcut;
// import org.springframework.stereotype.Component;

// @Aspect
// @Component
// public class AspectImpForLog {

//     Mlogger logger ;
//     public AspectImpForLog() {
//         this.logger = Mlogger.getlogger(AspectImpForLog.class);
//     }
//     @Pointcut("execution(* *(..))")
//     public void serviceLayerMethods() {}

//      @AfterThrowing(pointcut = "serviceLayerMethods()", throwing = "exception")
//     public void handleException(JoinPoint joinPoint, Throwable exception) {
//         String methodName =joinPoint.getSignature().getName();
//         String className  =joinPoint.getTarget().getClass().getName();
//         logger.error("Exception occured in class {} inside method {} : ",className,methodName,exception);
//         // Handle the exception here
//         System.out.println("An exception occurred: " + exception.getMessage());
//     }

// }
