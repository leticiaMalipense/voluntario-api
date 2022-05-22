package com.ifsp.api.queroservoluntario.rest.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ifsp.api.queroservoluntario.utils.CustomJsonDateDeserializer;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

public class SubscriptionModel implements Serializable {

    private Long id;

    @NotNull
    private Long individualId;

    private String individualName;

    private String individualOcupation;

    @NotNull
    private Long companyId;

    @NotNull
    private Long jobId;

    private String title;

    private String type;

    @JsonDeserialize(using = CustomJsonDateDeserializer.class)
    private Date dateInitial;

    @JsonDeserialize(using = CustomJsonDateDeserializer.class)
    private Date dateFinal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIndividualId() {
        return individualId;
    }

    public void setIndividualId(Long individualId) {
        this.individualId = individualId;
    }

    public String getIndividualName() {
        return individualName;
    }

    public void setIndividualName(String individualName) {
        this.individualName = individualName;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDateInitial() {
        return dateInitial;
    }

    public void setDateInitial(Date dateInitial) {
        this.dateInitial = dateInitial;
    }

    public Date getDateFinal() {
        return dateFinal;
    }

    public void setDateFinal(Date dateFinal) {
        this.dateFinal = dateFinal;
    }

    public String getIndividualOcupation() {
        return individualOcupation;
    }

    public void setIndividualOcupation(String individualOcupation) {
        this.individualOcupation = individualOcupation;
    }
}
