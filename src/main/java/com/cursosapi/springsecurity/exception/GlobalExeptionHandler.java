package com.cursosapi.springsecurity.exception;

import com.cursosapi.springsecurity.dto.APIError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice //controloa exepctiones
public class GlobalExeptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handlerGenericException(HttpServletRequest request, Exception exception) {
        APIError apiError = new APIError();
        apiError.setBackendMessages(exception.getLocalizedMessage());
        apiError.setUrl(request.getRequestURI());
        apiError.setMethod(request.getMethod());
        apiError.setMessasges(" ERROR INTERNO EN EL SUPER SERVIDOR");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handlerMethodArgumentNotValidException(HttpServletRequest request,
                                                                    MethodArgumentNotValidException exception) {

        APIError apiError = new APIError();
        apiError.setBackendMessages(exception.getLocalizedMessage());
        apiError.setUrl(request.getRequestURI());
        apiError.setMethod(request.getMethod());
        apiError.setMessasges("ERRO EN LA PETICION ENVIADA");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }


    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> handlerDeniedException(HttpServletRequest request, AccessDeniedException exception) {
        APIError apiError = new APIError();
        apiError.setBackendMessages(exception.getLocalizedMessage());
        apiError.setUrl(request.getRequestURI());
        apiError.setMethod(request.getMethod());
        apiError.setMessasges("Acceso denegado .No tienes los permisos necesarios para acceder a esta funcion por favor," +
                " contactar al administrador si crees que esto es un error ");
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(apiError);
    }

}
