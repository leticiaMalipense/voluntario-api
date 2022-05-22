package com.ifsp.api.queroservoluntario.converters;

import com.ifsp.api.queroservoluntario.entity.PhoneEntity;
import com.ifsp.api.queroservoluntario.rest.model.PhoneModel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PhoneConverter {

    public PhoneEntity toEntityUpdate(List<PhoneEntity> phonesEntity, List<PhoneModel> phonesModel) {
        PhoneEntity entity = phonesEntity.get(0);
        entity.setDdd(phonesModel.get(0).getDdd());
        entity.setNumber(phonesModel.get(0).getNumber());
        return entity;
    }

    public PhoneModel toModel(PhoneEntity phoneEntity) {
        PhoneModel model = new PhoneModel();
        model.setDdd(phoneEntity.getDdd());
        model.setNumber(phoneEntity.getNumber());
        return model;
    }
}
