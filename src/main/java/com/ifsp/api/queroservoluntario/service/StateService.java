package com.ifsp.api.queroservoluntario.service;

import com.ifsp.api.queroservoluntario.entity.StateEntity;
import com.ifsp.api.queroservoluntario.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StateService {

    @Autowired
    private StateRepository stateRepository;

    @Cacheable("states")
    public List<StateEntity> findAll() {
        return stateRepository.findAll();
    }
}
