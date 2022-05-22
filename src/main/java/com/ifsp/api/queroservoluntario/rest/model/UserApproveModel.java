package com.ifsp.api.queroservoluntario.rest.model;

import com.sun.istack.NotNull;

import java.io.Serializable;

public class UserApproveModel implements Serializable {

    @NotNull
    private boolean approve;

    public boolean isApprove() {
        return approve;
    }

    public void setApprove(boolean approve) {
        this.approve = approve;
    }
}
