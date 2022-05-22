package com.ifsp.api.queroservoluntario.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "TB_INDIVIDUAL")
public class IndividualEntity extends UserEntity {

    @Column(name = "OCUPATION")
    private String ocupation;

    public String getOcupation() {
        return ocupation;
    }

    public void setOcupation(String ocupation) {
        this.ocupation = ocupation;
    }
}
