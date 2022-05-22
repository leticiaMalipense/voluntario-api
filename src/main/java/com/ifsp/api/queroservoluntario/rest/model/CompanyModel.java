package com.ifsp.api.queroservoluntario.rest.model;

import javax.validation.constraints.NotBlank;

public class CompanyModel extends UserModel {

    @NotBlank
    private String occupationArea;

    @NotBlank
    private String description;

    public String getOccupationArea() {
        return occupationArea;
    }

    public void setOccupationArea(String occupationArea) {
        this.occupationArea = occupationArea;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
