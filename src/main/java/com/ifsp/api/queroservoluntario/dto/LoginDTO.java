package com.ifsp.api.queroservoluntario.dto;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class LoginDTO implements Serializable {

    @NotBlank
    private String user;
    @NotBlank
    private String pass;

    LoginDTO() {

    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
