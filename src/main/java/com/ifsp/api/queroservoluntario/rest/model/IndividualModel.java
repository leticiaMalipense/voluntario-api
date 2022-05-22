package com.ifsp.api.queroservoluntario.rest.model;

import javax.validation.constraints.NotBlank;

public class IndividualModel extends UserModel {

    @NotBlank
    private String ocupation;

    public String getOcupation() {
        return ocupation;
    }

    public void setOcupation(String ocupation) {
        this.ocupation = ocupation;
    }


}
