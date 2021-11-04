package com.administration.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePasswordBody {
    private String password;
    private String newPassword;
    private String newPasswordConfirm;
}
