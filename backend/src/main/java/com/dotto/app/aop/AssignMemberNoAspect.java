package com.dotto.app.aop;

import com.dotto.app.config.security.guard.AuthHelper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;

@Aspect
@Component
@Slf4j
public class AssignMemberNoAspect {

    @Before("@annotation(com.dotto.app.aop.AssignMemberNo)")
    public void assignMemberNo(JoinPoint joinPoint){
        Arrays.stream(joinPoint.getArgs())
                .forEach(arg -> getMethod(arg.getClass(), "setMemberNo")
                        .ifPresent(setMemberNo -> invokeMethod(arg, setMemberNo, AuthHelper.extractMemberNo())));
    }

    private Optional<Method> getMethod(Class<?>clazz, String methodName){
        try {
            return Optional.of(clazz.getMethod(methodName,Long.class));
        }catch (NoSuchMethodException e){
            return Optional.empty();
        }
    }

    private void invokeMethod(Object obj, Method method, Object... args){
        try {
            method.invoke(obj, args);
        }catch (ReflectiveOperationException e){
            throw new RuntimeException(e);
        }
    }
}
