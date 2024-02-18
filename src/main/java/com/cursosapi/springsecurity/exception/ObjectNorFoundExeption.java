package com.cursosapi.springsecurity.exception;

public class ObjectNorFoundExeption extends RuntimeException{
    public ObjectNorFoundExeption() {
    }

    public ObjectNorFoundExeption(String message) {
        super(message);
    }

    public ObjectNorFoundExeption(String message, Throwable cause) {
        super(message, cause);
    }
}
