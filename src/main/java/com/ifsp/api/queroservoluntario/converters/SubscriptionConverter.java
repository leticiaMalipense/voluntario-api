package com.ifsp.api.queroservoluntario.converters;

import com.ifsp.api.queroservoluntario.entity.IndividualEntity;
import com.ifsp.api.queroservoluntario.entity.JobEntity;
import com.ifsp.api.queroservoluntario.entity.SubscriptionEntity;
import com.ifsp.api.queroservoluntario.entity.UserEntity;
import com.ifsp.api.queroservoluntario.rest.model.SubscriptionModel;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionConverter {
    public SubscriptionModel toModel(SubscriptionEntity entity) {
        SubscriptionModel model = new SubscriptionModel();
        model.setId(entity.getId());
        model.setCompanyId(entity.getCompanyId());
        model.setIndividualId(entity.getIndividual().getId());
        model.setIndividualName(entity.getIndividual().getName());
        model.setIndividualOcupation(entity.getIndividual().getOcupation());
        model.setJobId(entity.getJob().getId());
        model.setType(entity.getJob().getType());

        model.setTitle(entity.getJob().getTitle());
        model.setDateInitial(entity.getJob().getDateInitial());
        model.setDateFinal(entity.getJob().getDateFinal());

        return model;
    }

    public SubscriptionEntity toEntity(SubscriptionModel model, JobEntity job, UserEntity user, Boolean active) {
        SubscriptionEntity entity = new SubscriptionEntity();
        entity.setCompanyId(model.getCompanyId());
        entity.setIndividual((IndividualEntity) user);
        entity.setJob(job);
        entity.setActive(active);

        return entity;
    }
}
