package com.ifsp.api.queroservoluntario.rest.model;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class UserPasswordModel implements Serializable {

    @NotBlank
    private String newPassword;

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
