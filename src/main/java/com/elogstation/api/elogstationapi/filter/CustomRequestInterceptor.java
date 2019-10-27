package com.elogstation.api.elogstationapi.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.security.Principal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Base64;

@Component
public class CustomRequestInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(CustomRequestInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        Principal principal = request.getUserPrincipal();
        if(principal != null) {
            logInfo(principal.getName(), request.getRequestURL().toString());
        }
        return true;
    }

    private void logInfo(String user, String url){
        logger.info("Audit :: User :: " + user + " :: Url :: " + url);

    }



}