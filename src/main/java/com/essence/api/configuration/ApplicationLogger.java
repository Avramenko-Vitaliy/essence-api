package com.essence.api.configuration;

import com.essence.api.auth.UserAuthentication;
import com.essence.api.auth.tokens.AppToken;
import com.essence.api.services.JwtService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.stream.Collectors;

@Aspect
@Component
public class ApplicationLogger {

    private static final Log LOG = LogFactory.getLog(ApplicationLogger.class);

    private Collection<String> excludedServices = Collections.singletonList(JwtService.class.getName());

    @Around("execution(* com.essence.api.services.*.*(..))")
    public Object logTimeMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        String className = joinPoint.getTarget().getClass().getName();

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object retVal = joinPoint.proceed();
        if (excludedServices.contains(className.replaceAll("\\$\\$.*", "")) || className.contains("Mockito")
                || className.contains("DtoService")) {
            return retVal;
        }
        stopWatch.stop();
        AppToken token = null;
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication instanceof UserAuthentication) {
                token = ((UserAuthentication) authentication).getDetails();
            }
        } catch (Exception e) {
            LOG.error("Can't get userinfo", e);
        }

        StringBuilder logMessage = new StringBuilder();

        if (Objects.nonNull(token)) {
            logMessage.append("user: ");
            logMessage.append(token.getUserId());
            logMessage.append(", full_name: ");
            logMessage.append(token.getFullName());
        }

        logMessage.append(joinPoint.getTarget().getClass().getName());
        logMessage.append(".");
        logMessage.append(joinPoint.getSignature().getName());
        logMessage.append("(");
        // append args
        logMessage.append(
                Arrays.stream(joinPoint.getArgs())
                        .map(String::valueOf)
                        .collect(Collectors.joining(","))
        );

        logMessage.append(")");
        logMessage.append(" execution time: ");
        logMessage.append(stopWatch.getTotalTimeMillis());
        logMessage.append(" ms");
        logMessage.append(". returned ");
        logMessage.append(retVal);
        LOG.info(logMessage.toString());
        return retVal;
    }
}
