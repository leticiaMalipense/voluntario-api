package com.ifsp.api.queroservoluntario.converters;

import com.ifsp.api.queroservoluntario.entity.CompanyEntity;
import com.ifsp.api.queroservoluntario.entity.JobEntity;
import com.ifsp.api.queroservoluntario.enums.JobType;
import com.ifsp.api.queroservoluntario.rest.model.JobModel;
import com.ifsp.api.queroservoluntario.rest.model.PhoneModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.Normalizer;

@Component
public class JobConverter {

    @Autowired
    private PhoneConverter phoneConverter;

    public JobEntity toEntity(JobModel jobModel, CompanyEntity companyEntity, boolean active) {
        JobEntity entity = new JobEntity();
        entity.setEmail(companyEntity.getEmail());
        entity.setCompany(companyEntity);
        entity.setTitle(jobModel.getTitle());
        entity.setType(jobModel.getType().name());
        entity.setDescription(jobModel.getDescription());
        entity.setDateInitial(jobModel.getDateInitial());
        entity.setDateFinal(jobModel.getDateFinal());
        entity.setStateId(companyEntity.getAddress().getState().getId());
        entity.setCity(removerAcentos(companyEntity.getAddress().getCity()));
        entity.setActive(active);

        return entity;
    }

    public JobModel toModel(JobEntity jobEntity) {
        JobModel jobModel = new JobModel();
        jobModel.setEmail(jobEntity.getEmail());
        jobModel.setId(jobEntity.getId());
        jobModel.setCompanyId(jobEntity.getCompany().getId());
        jobModel.setCompanyName(jobEntity.getCompany().getName());
        jobModel.setTitle(jobEntity.getTitle());
        jobModel.setType(JobType.findByName(jobEntity.getType()));
        jobModel.setDescription(jobEntity.getDescription());
        jobModel.setDateInitial(jobEntity.getDateInitial());
        jobModel.setDateFinal(jobEntity.getDateFinal());

        PhoneModel phoneModel = phoneConverter.toModel(jobEntity.getCompany().getPhones().get(0));
        jobModel.setPhone(phoneModel);

        return jobModel;
    }

    public JobEntity toEntityUpdate(JobEntity entity, JobModel jobModel) {
        entity.setTitle(jobModel.getTitle());
        entity.setType(jobModel.getType().name());
        entity.setDescription(jobModel.getDescription());
        entity.setDateInitial(jobModel.getDateInitial());
        entity.setDateFinal(jobModel.getDateFinal());

        return entity;
    }

    public String removerAcentos(String str) {
        return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }
}
