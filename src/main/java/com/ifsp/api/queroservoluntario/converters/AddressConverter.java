package com.ifsp.api.queroservoluntario.converters;

import com.ifsp.api.queroservoluntario.entity.AddressEntity;
import com.ifsp.api.queroservoluntario.entity.StateEntity;
import com.ifsp.api.queroservoluntario.infra.exceptions.BadRequestException;
import com.ifsp.api.queroservoluntario.repository.StateRepository;
import com.ifsp.api.queroservoluntario.rest.model.AddressModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AddressConverter {

    @Autowired
    private StateRepository stateRepository;

    public AddressEntity toEntity(AddressModel addressModel) {
        AddressEntity entity = new AddressEntity();

        entity.setCep(addressModel.getCep());
        entity.setStreet(addressModel.getStreet());
        entity.setNeighborhood(addressModel.getNeighborhood());
        entity.setCity(addressModel.getCity());
        entity.setComplement(addressModel.getComplement());
        entity.setNumber(addressModel.getNumber());

        Optional<StateEntity> stateEntity = stateRepository.findById(addressModel.getIdState());
        if(!stateEntity.isPresent()) {
            throw new BadRequestException("Estado invalido");
        }
        entity.setState(stateEntity.get());

        return entity;
    }

    public AddressModel toModel(AddressEntity entity) {
        AddressModel model = new AddressModel();

        model.setId(entity.getId());
        model.setCep(entity.getCep());
        model.setStreet(entity.getStreet());
        model.setNeighborhood(entity.getNeighborhood());
        model.setCity(entity.getCity());
        model.setComplement(entity.getComplement());
        model.setIdState(entity.getState().getId());
        model.setNameState(entity.getState().getName());
        model.setNumber(entity.getNumber());

        return model;
    }

    public AddressEntity toEntityUpdate(AddressEntity addressEntity, AddressModel addressModel) {

        addressEntity.setCep(addressModel.getCep());
        addressEntity.setStreet(addressModel.getStreet());
        addressEntity.setNeighborhood(addressModel.getNeighborhood());
        addressEntity.setCity(addressModel.getCity());
        addressEntity.setComplement(addressModel.getComplement());
        addressEntity.setNumber(addressModel.getNumber());

        Optional<StateEntity> stateEntity = stateRepository.findById(addressModel.getIdState());
        if(!stateEntity.isPresent()) {
            throw new BadRequestException("Estado invalido");
        }
        addressEntity.setState(stateEntity.get());

        return addressEntity;
    }
}
