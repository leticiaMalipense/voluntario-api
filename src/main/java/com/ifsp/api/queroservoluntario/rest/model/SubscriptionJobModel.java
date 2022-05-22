package com.ifsp.api.queroservoluntario.rest.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ifsp.api.queroservoluntario.utils.CustomJsonDateDeserializer;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class SubscriptionJobModel implements Serializable {

    private Long jobId;

    private String title;

    private String type;

    @JsonDeserialize(using = CustomJsonDateDeserializer.class)
    private Date dateInitial;

    @JsonDeserialize(using = CustomJsonDateDeserializer.class)
    private Date dateFinal;

    private int quantity;

    private List<SubscriptionModel> subscriptions;

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

    public List<SubscriptionModel> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(List<SubscriptionModel> subscriptions) {
        this.subscriptions = subscriptions;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}


