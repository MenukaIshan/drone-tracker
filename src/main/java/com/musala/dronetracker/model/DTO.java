package com.musala.dronetracker.model;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class DTO {
    private HttpStatus httpStatus;
    private String message;
    private Object data;

    public DTO(HttpStatus httpStatus, String message, Object data) {
        this.httpStatus = httpStatus;
        this.message = message;
        this.data = data;
    }

    public DTO() {
    }
}
