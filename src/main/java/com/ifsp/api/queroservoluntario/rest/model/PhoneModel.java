package com.ifsp.api.queroservoluntario.rest.model;

import com.sun.istack.NotNull;

import java.io.Serializable;

public class PhoneModel implements Serializable {

    private Long Id;

    @NotNull
    private Long ddd;

    @NotNull
    private Long number;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Long getDdd() {
        return ddd;
    }

    public void setDdd(Long ddd) {
        this.ddd = ddd;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }
}
