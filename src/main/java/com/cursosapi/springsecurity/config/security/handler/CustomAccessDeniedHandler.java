package com.cursosapi.springsecurity.config.security.handler;

import com.cursosapi.springsecurity.dto.APIError;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        APIError apiError = new APIError();
        apiError.setBackendMessages(accessDeniedException.getLocalizedMessage());
        apiError.setUrl(request.getRequestURI());
        apiError.setMethod(request.getMethod());
        apiError.setMessasges("Acceso denegado .No tienes los permisos necesarios para acceder a esta funcion por favor," +
                " contactar al administrador si crees que esto es un error ");

        apiError.setTimestamp(LocalDateTime.now());

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.FORBIDDEN.value());

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        String apiErrorasJSON = mapper.writeValueAsString(apiError);
        response.getWriter().write(apiErrorasJSON);
    }
}
