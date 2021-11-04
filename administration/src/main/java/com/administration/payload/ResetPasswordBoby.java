package com.administration.payload;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResetPasswordBoby {

    private String token;
    private String newPassword;
    private String newPasswordConfirm;
}
