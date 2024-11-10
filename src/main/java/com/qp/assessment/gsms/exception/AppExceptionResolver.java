package com.qp.assessment.gsms.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AppExceptionResolver extends AbstractHandlerExceptionResolver {
	
    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        log.info("Application error in: [" + ex.getClass().getName() + "]", ex);
        return null;
    }
}
