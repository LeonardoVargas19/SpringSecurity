package com.cursosapi.springsecurity.config.security.handler;
//Esta clase sera la puerta de entrada de la aplicacion para poder invitar al usuario a loggearse

import com.cursosapi.springsecurity.dto.APIError;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        APIError apiError = new APIError();
        apiError.setBackendMessages(authException.getLocalizedMessage());
        apiError.setUrl(request.getRequestURI());
        apiError.setMethod(request.getMethod());
        apiError.setMessasges("No se encontraron credenciales Por favor inicie secion");
        apiError.setTimestamp(LocalDateTime.now());

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        String apiErrorasJSON = mapper.writeValueAsString(apiError);
        response.getWriter().write(apiErrorasJSON);
    }
}
