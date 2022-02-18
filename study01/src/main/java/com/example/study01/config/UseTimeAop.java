package com.example.study01.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UseTimeAop {

    @Around("execution(public * com.example.study01.controller..*(..))")
    public Object methodRunTime(ProceedingJoinPoint joinPoint) throws Throwable {
        // 측정 시작 시간
        long startTime = System.currentTimeMillis();

        try {
            Object object = joinPoint.proceed();
            return object;
        } finally {
            //측정 종료 시간
            long endTime = System.currentTimeMillis();

            // 수행시간
            long runTime = endTime - startTime;
            System.out.println("메소드 수행시간 = " + runTime);
        }
    }
}
