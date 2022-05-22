package com.ifsp.api.queroservoluntario.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "TB_STATE")
public class StateEntity  implements Serializable {

    @Id
    @Column(name = "ID_STATE")
    private Long Id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "ABBREVIATION")
    private String abbreviation;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }
}
