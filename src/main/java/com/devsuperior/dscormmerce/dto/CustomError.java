package com.devsuperior.dscormmerce.dto;

import java.time.Instant;

public class CustomError {
    private Instant timeStamp;
    private Integer status;
    private String error;
    private String path;


    public CustomError(Instant timeStamp, Integer status, String error, String path) {
        this.path = path;
        this.error = error;
        this.status = status;
        this.timeStamp = timeStamp;
    }

    public Instant getTimeStamp() {
        return timeStamp;
    }

    public Integer getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getPath() {
        return path;
    }
}
