package org.msd.ebankingbackend.aspect;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Around("execution(* org.msd.ebankingbackend..*(..))")
    public Object logAroundMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        LoggingAspect.logMetric(className, methodName, "in");
        String metric = "success";
        try {
            return joinPoint.proceed();
        } catch (Exception exception) {
            metric = "failure";
            LoggingAspect.logMetric(className, methodName, exception.getLocalizedMessage());
            throw exception;
        } finally {
            LoggingAspect.logMetric(className, methodName, metric);
        }
    }


    private static void logMetric(String serviceName, String methodeName, String metric) {
        String logMetric = createLogMetricMessage(serviceName, methodeName, metric);
        logMetric(logMetric);
    }

    private static void logMetric(String logMetric) {
        LoggingAspect.log.info(logMetric);
    }

    private static String createLogMetricMessage(String serviceName, String methodeName, String metric) {
        return String.join("", "[", serviceName, "]", "[", methodeName, "]: ", metric);
    }
}
