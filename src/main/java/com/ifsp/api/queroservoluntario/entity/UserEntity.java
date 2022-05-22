package com.ifsp.api.queroservoluntario.entity;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "TB_USER")
@DynamicUpdate
@Inheritance(strategy = InheritanceType.JOINED)
public class UserEntity implements UserDetails, Serializable {

    @Id
    @Column(name = "ID_USER")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "DOCUMENT")
    private String document;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "CREATION_DATE", updatable = false)
    private Date creationDate;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name="TYPE", referencedColumnName = "TYPE")
    private ProfileEntity profile;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name="ID_ADDRESS", referencedColumnName = "ID_ADDRESS")
    private AddressEntity address;

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name="ID_USER", referencedColumnName = "ID_USER")
    private List<PhoneEntity> phones;

    @Column(name = "ACTIVE")
    private Boolean active;

    @PrePersist
    void prePersiste() {
        this.creationDate = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ProfileEntity getProfile() {
        return profile;
    }

    public void setProfile(ProfileEntity profile) {
        this.profile = profile;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public List<PhoneEntity> getPhones() {
        return phones;
    }

    public void setPhones(List<PhoneEntity> phones) {
        this.phones = phones;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(this.profile);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return this.name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public AddressEntity getAddress() {
        return address;
    }

    public void setAddress(AddressEntity address) {
        this.address = address;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
