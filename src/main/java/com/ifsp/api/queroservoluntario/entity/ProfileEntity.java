package com.ifsp.api.queroservoluntario.entity;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "TB_PROFILE")
public class ProfileEntity implements GrantedAuthority, Serializable {

    @Id
    @Column(name = "ID_PROFILE")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "DESCRIPTION")
    private String description;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getAuthority() {
        return this.type;
    }
}
