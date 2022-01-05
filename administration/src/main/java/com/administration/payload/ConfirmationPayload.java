package com.administration.payload;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ConfirmationPayload {

    private String email;
    private String token;

}
