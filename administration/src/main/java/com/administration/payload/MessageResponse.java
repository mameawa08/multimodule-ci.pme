package com.administration.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageResponse {
    private String message;
    private boolean success;

    public MessageResponse(String message) {
        this.message = message;
    }
    public MessageResponse(boolean success) {
        this.success = success;
    }
    public MessageResponse(String message, boolean success) {
        this.message = message;
        this.success = success;
    }
}
