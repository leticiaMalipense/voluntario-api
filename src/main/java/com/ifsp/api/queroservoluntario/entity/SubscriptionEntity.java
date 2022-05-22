package com.ifsp.api.queroservoluntario.entity;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TB_SUBSCRIPTION")
@DynamicUpdate
public class SubscriptionEntity {

    @Id
    @Column(name = "ID_SUBSCRIPTION")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_INDIVIDUAL", referencedColumnName = "ID_USER")
    private IndividualEntity individual;

    @Column(name = "ID_COMPANY")
    private Long companyId;

    @ManyToOne
    @JoinColumn(name = "ID_JOB", referencedColumnName = "ID_JOB")
    private JobEntity job;

    @Column(name = "ACTIVE")
    private Boolean active;

    @Column(name = "DATE_CREATION")
    private Date creationDate;

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

    public IndividualEntity getIndividual() {
        return individual;
    }

    public void setIndividual(IndividualEntity individual) {
        this.individual = individual;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public JobEntity getJob() {
        return job;
    }

    public void setJob(JobEntity job) {
        this.job = job;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
