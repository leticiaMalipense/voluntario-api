package com.ifsp.api.queroservoluntario.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "TB_COMPANY")
public class CompanyEntity extends UserEntity {

    @Column(name = "OCUPATION_AREA")
    private String occupationArea;

    @Column(name = "DESCRIPTION")
    private String description;

    @Override
    void prePersiste() {
        super.prePersiste();
    }

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
