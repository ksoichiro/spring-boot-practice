package com.example.spring.aspect;

import com.example.spring.util.LogHandler;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Locale;
import java.util.stream.Collectors;

@Component
@Aspect
@Slf4j
public class LogAdvice {
    @Autowired
    private MessageSource messageSource;

    @Autowired
    private HttpServletRequest request;

    @Pointcut("@within(org.springframework.stereotype.Controller)")
    public void controller() {
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void requestMapping() {
    }

    @Pointcut("execution(* com.example.spring..*.*(..))")
    public void anyProjectMethodExecution() {
    }

    @Around("controller() && requestMapping() && anyProjectMethodExecution()")
    public Object log(ProceedingJoinPoint pjp) throws Throwable {
        BindingResult bindingResult = getBindingResult(pjp);
        try {
            Object retVal = pjp.proceed();
            Throwable caughtError = getCaughtError(pjp, bindingResult);
            log(getMethodName(pjp), getErrors(bindingResult), caughtError == null ? "-" : caughtError.getMessage(), caughtError);
            return retVal;
        } catch (Throwable t) {
            log(getMethodName(pjp), getErrors(bindingResult), t.getMessage(), t);
            throw t;
        }
    }

    private void log(Object... params) {
        if (params[params.length - 1] instanceof Throwable) {
            String serialized = Arrays.stream(params).limit(params.length - 1).map(p -> p == null ? "-" : p.toString()).collect(Collectors.joining(" "));
            log.warn("{}", serialized, params[params.length - 1]);
        } else {
            String serialized = Arrays.stream(params).map(p -> p == null ? "-" : p.toString()).collect(Collectors.joining(" "));
            log.info("{}", serialized);
        }
    }

    private String getMethodName(ProceedingJoinPoint pjp) {
        Signature sig = pjp.getSignature();
        return sig.getDeclaringType().getSimpleName() + "#" + sig.getName();
    }

    private Throwable getCaughtError(ProceedingJoinPoint pjp, BindingResult bindingResult) {
        Model model = getModel(pjp);
        if (model == null) {
            return null;
        }
        Object attr = request.getAttribute(LogHandler.CAUGHT_ERROR_ATTRIBUTE_NAME);
        if (attr != null && attr instanceof Throwable) {
            bindingResult.reject("message.error");
            return (Throwable) attr;
        }
        return null;
    }

    private Model getModel(ProceedingJoinPoint pjp) {
        for (Object arg : pjp.getArgs()) {
            if (arg != null && arg instanceof Model) {
                return (Model) arg;
            }
        }
        return null;
    }

    private BindingResult getBindingResult(ProceedingJoinPoint pjp) {
        for (Object arg : pjp.getArgs()) {
            if (arg != null && arg instanceof BindingResult) {
                return (BindingResult) arg;
            }
        }
        return null;
    }

    private String getErrors(BindingResult bindingResult) {
        if (bindingResult != null && bindingResult.hasErrors()) {
            return bindingResult.getAllErrors().stream().map(map -> {
                if (map instanceof FieldError) {
                    return ((FieldError) map).getField() + " " + map.getDefaultMessage();
                } else {
                    return messageSource.getMessage(map, Locale.getDefault());
                }
            }).collect(Collectors.toList()).toString();
        } else {
            return "-";
        }
    }
}
