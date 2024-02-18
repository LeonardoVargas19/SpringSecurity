package com.cursosapi.springsecurity.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class APIError implements Serializable {
    private String backendMessages;
    private String url;
    private String method;

    private String messasges;
    private LocalDateTime timestamp;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getBackendMessages() {
        return backendMessages;
    }

    public void setBackendMessages(String backendMessages) {
        this.backendMessages = backendMessages;
    }

    public String getMessasges() {
        return messasges;
    }

    public void setMessasges(String messasges) {
        this.messasges = messasges;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
