package com.ifsp.api.queroservoluntario.service;

import com.ifsp.api.queroservoluntario.converters.UserConverter;
import com.ifsp.api.queroservoluntario.entity.IndividualEntity;
import com.ifsp.api.queroservoluntario.infra.exceptions.NotFoundException;
import com.ifsp.api.queroservoluntario.repository.IndividualRepository;
import com.ifsp.api.queroservoluntario.rest.model.PaginationModel;
import com.ifsp.api.queroservoluntario.rest.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class IndividualService {

    @Autowired
    private IndividualRepository repository;

    @Autowired
    private UserConverter userConverter;

    public PaginationModel findAll(int page, int size) {

        Page<IndividualEntity> individualEntities = repository.findAllWithPagination(PageRequest.of(page, size));

        if(individualEntities.getTotalElements() < 1) {
            throw new NotFoundException();
        }

        List<UserModel> model = individualEntities.stream().map(c -> userConverter.toModel(Optional.ofNullable(c), null)).collect(Collectors.toList());

        PaginationModel paginationModel = new PaginationModel();
        paginationModel.setItems(model);
        paginationModel.setTotal(individualEntities.getTotalElements());
        return paginationModel;
    }

}
